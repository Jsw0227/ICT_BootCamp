package kr.co.ictedu.myictstudy.controller.gallery;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import kr.co.ictedu.myictstudy.vo.PageVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.http.HttpServletRequest;
import kr.co.ictedu.myictstudy.service.GalleryService;
import kr.co.ictedu.myictstudy.vo.GalleryImageVO;
import kr.co.ictedu.myictstudy.vo.GalleryVO;

@RestController
@RequestMapping("/gallery")
public class GalleryController {

	@Autowired
    private final PageVO pageVO;

	@Autowired
	private GalleryService galleryservice;
	
	@Value("${spring.servlet.multipart.location}")
	private String uploadDir;

    GalleryController(PageVO pageVO) {
        this.pageVO = pageVO;
    }
	
	@PostMapping("/add")
	public ResponseEntity<?> addGallery(@ModelAttribute GalleryVO galleryVO, @RequestParam("images") MultipartFile[] images, HttpServletRequest request){
		galleryVO.setReip(request.getRemoteAddr());
		// 이미지들을 저장할 List객체
		List<GalleryImageVO> imageList = new ArrayList<>();
		
		// MultipartFile[]로 한 번에 받은 이미지를 반복문으로 하나씩 MultipartFile로 빼서 업로드 된 정보를 가져오자.
		try {
			for (MultipartFile file : images) {
				if(!file.isEmpty()) {
					String originalFilename = file.getOriginalFilename();
					File f = new File(uploadDir + "/gallery/",originalFilename);
					file.transferTo(f);
					GalleryImageVO imageVO = new GalleryImageVO();
					imageVO.setImagename(originalFilename);
					imageList.add(imageVO);
				}
			}
			galleryVO.setGetimglist(imageList);
			//galleryService를 사용해서 데이터베이스에 정보를 저장
			galleryservice.transcationProcess(galleryVO, imageList);
			System.out.println("정상적인 처리");
			return ResponseEntity.ok("갤러리 등록 성공");
		} catch (Exception e) {
			System.out.println("오류 발생");
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("업로드 실패");
		}
		
		
	}
	
	@RequestMapping("/galleryList")
	public Map<String, Object> galleryList(@RequestParam Map<String,String> paramMap, HttpServletRequest request){
		System.out.println("Method =>" + request.getMethod());
		String cPage = paramMap.get("cPage");
		System.out.println("cPage" + cPage);
		System.out.println("searchType : "+ paramMap.get("searchType"));
		System.out.println("searchValue : "+ paramMap.get("searchValue"));
		System.out.println("--------------------");
		
		int totalCnt = galleryservice.totalCount(paramMap);
		pageVO.setTotalRecord(totalCnt);
		System.out.println("TotalCount : "+ pageVO.getTotalRecord());
		System.out.println("------------------------------");
		
		
		int totalPage = (int) Math.ceil(totalCnt / (double)pageVO.getNumPerPage());
		pageVO.setTotalPage(totalPage);
		System.out.println("TotalPage : "+ pageVO.getTotalPage());
		
		int totalBlock = (int) Math.ceil(totalPage / (double) pageVO.getPagePerBlock());
		pageVO.setTotalBlock(totalBlock);
		System.out.println("TotalBlock:" + pageVO.getTotalBlock());
		System.out.println("********************************");
		
		if(cPage != null) {
			pageVO.setNowPage(Integer.parseInt(cPage));
		}else {
			pageVO.setNowPage(1);
		}
		System.out.println("cPage:" + pageVO.getNowPage());
		System.out.println("********************************");
		
		pageVO.setBeginPerPage((pageVO.getNowPage() - 1) * pageVO.getNumPerPage() + 1);
		pageVO.setEndPerPage(pageVO.getBeginPerPage() + pageVO.getNumPerPage() - 1);
	    System.out.println("5. beginPerPage = " + pageVO.getBeginPerPage());
	    System.out.println("5. endPerPage = " + pageVO.getEndPerPage());
	    System.out.println("********************************");
		
		
		Map<String, Object> response = new HashMap<>();
		Map<String, String> map = new HashMap<>(paramMap);
		
		map.put("begin", String.valueOf(pageVO.getBeginPerPage()));
		map.put("end", String.valueOf(pageVO.getEndPerPage()));
		List<Map<String,Object>> list = galleryservice.list(map);
	
		int startPage = (int) ((pageVO.getNowPage() - 1) / pageVO.getPagePerBlock()) * pageVO.getPagePerBlock() + 1;
		int endPage = startPage + pageVO.getPagePerBlock() - 1;
		if (endPage > pageVO.getTotalPage()) {
			endPage = pageVO.getTotalPage();
		}
		System.out.println("6. startPage = " + startPage);
		System.out.println("6. endPage = " + endPage);
		response.put("data", list); // 페이징 처리가 완료된 리스트를 저장한 데이터
		response.put("totalItems", pageVO.getTotalRecord()); // 전체 게시물의 수 count
		response.put("totalPages", pageVO.getTotalPage()); // 전체 페이지
		response.put("currentPage", pageVO.getNowPage()); // 현재 페이지
		response.put("startPage", startPage); // 시작
		response.put("endPage", endPage); // 끝
		return response;
		
		
	}
	@GetMapping("/gdetail")
	public Map<String, Object> detail(@RequestParam("num") int num){
		return galleryservice.detail(num);
	}
		
	
}
