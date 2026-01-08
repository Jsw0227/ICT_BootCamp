package kr.co.ictedu.myictstudy.controller.gallery;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
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
import kr.co.ictedu.myictstudy.vo.GalleryImagesVO;
import kr.co.ictedu.myictstudy.vo.GalleryVO;
import kr.co.ictedu.myictstudy.vo.PageVO;

@RestController
@RequestMapping("/gallery")
public class GalleryController {
	@Autowired
	private GalleryService galleryService;
	
	@Value("${spring.servlet.multipart.location}")
	private String uploadDir;
	
	
	@Autowired
	private PageVO pageVO;
	
	//post방식으로 /gallery/add 전송할 때 
	@PostMapping("/add")
	public ResponseEntity<?> addGallery(@ModelAttribute GalleryVO galleryVO,
			@RequestParam("images") MultipartFile[] images,
			HttpServletRequest request) {
		galleryVO.setReip(request.getRemoteAddr()); 
		//이미지들을 저장할 List객체 
		List<GalleryImagesVO> imageList = new ArrayList<>();
		//MultipartFile[]로 한번에 받은 이미지를 반복문으로 하나씩 MultipartFile
		//로 빼서 업로드된 정보를 가져 오자.
		try {
			for(MultipartFile file: images) {
				if (!file.isEmpty()) { //이미지가 존재한다면 
					String originalFilename = file.getOriginalFilename();
					File f = new File(uploadDir + "/gallery/", originalFilename);
					file.transferTo(f);//이미지를 복사 
					GalleryImagesVO imageVO = new GalleryImagesVO();
					imageVO.setImagename(originalFilename);//이미지 이름을 저장
					imageList.add(imageVO);
				}
			}
			// GalleryVO에 이미지 리스트 설정
			galleryVO.setGetimglist(imageList);
			//galleryService를 사용해서 데이터베이스에 정보를 저장 
			galleryService.transcationProcess(galleryVO, imageList);
			System.out.println("정상적인 처리");
			return ResponseEntity.ok("갤러리 등록 성공");
		} catch (Exception e) {
			
			System.out.println("오류가 났음 ");
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("업로드 실패");
		}
	}
	//http://192.168.0.195/myictstudy/gallery/galleryList
	@RequestMapping("/galleryList")
	public Map<String, Object> galleryList(@RequestParam Map<String, String> paramMap, 
			HttpServletRequest request) {
		System.out.println("Method =>" + request.getMethod());
		// Map의 키값이 파라미터
		// 현재 페이지값
		String cPage = paramMap.get("cPage");
		System.out.println("cPage:" + cPage);
		System.out.println("searchType:" + paramMap.get("searchType"));
		System.out.println("searchValue:" + paramMap.get("searchValue"));
		System.out.println("********************************");
		// 1. 총 게시물 수
		int totalCnt = galleryService.totalCount(paramMap);
		pageVO.setTotalRecord(totalCnt);
		System.out.println("TotalCount:" + pageVO.getTotalRecord());
		System.out.println("********************************");

		// 2. 총페이지 수 구하기 => totalCnt 총게시물수 / numPerPage; 한 페이지당 보여질 게시물 수
		// 11개의 데이터 -> 11/10.0 => 1.1 => 2
		int totalPage = (int) Math.ceil(totalCnt / (double) pageVO.getNumPerPage());
		pageVO.setTotalPage(totalPage);
		System.out.println("TotalPage :" + pageVO.getTotalPage());
		System.out.println("********************************");
		// 3. 총블록수 저장
		// 전체페이지 / pagePerBlock
		// [1][2][3][4][5]| [6][7][8][][]
		int totalBlock = (int) Math.ceil(totalPage / (double) pageVO.getPagePerBlock());
		pageVO.setTotalBlock(totalBlock);
		System.out.println("TotalBlock:" + pageVO.getTotalBlock());
		System.out.println("********************************");
		// 4.현재 페이지 설정
		if (cPage != null) {
			pageVO.setNowPage(Integer.parseInt(cPage));
		} else {
			pageVO.setNowPage(1);
		}
		System.out.println("cPage:" + pageVO.getNowPage());
		System.out.println("********************************");
		// 5. 현재 페이지의 시작 게시물과 끝 게시물 번호를 계산 해서 pageVO에 저장한다.
		// 공식에 값을 대입해서 결과를 예측 해보자.
		// cPage가 1일때 = 1 - 1 * 10 + 1
		// 시작페이지가 0이면 안되기때문에 +1 을 더함 => 1
		// cPage가 2일때 = 2 - 1 * 10 + 1 => 11
		// 단이 5 단이고 범위가 3이다.
		// 4 5 6
		// for(int j=4; j<7; j++)
		pageVO.setBeginPerPage((pageVO.getNowPage() - 1) * pageVO.getNumPerPage() + 1);
		pageVO.setEndPerPage(pageVO.getBeginPerPage() + pageVO.getNumPerPage() - 1);
		System.out.println("5. beginPerPage = " + pageVO.getBeginPerPage());
		System.out.println("5. endPerPage = " + pageVO.getEndPerPage());
		System.out.println("********************************");
//				Map<String, String> map = new HashMap<>();
//				map.put("begin", String.valueOf(1));
//				map.put("end", String.valueOf(10));
		// return upBoardService.list(map);
		// 6.
		// json으로 메서드가 반환할 타입이고 그 데이터를 저장할 맵 ----------
		Map<String, Object> response = new HashMap<>();
		// 기존의 paramMap에 새로운 데이터를 추가한다. (시작과, 끝 번호를 추가)
		// mapper에서 사용할 값들을 포함한다.
		Map<String, String> map = new HashMap<>(paramMap);
		map.put("begin", String.valueOf(pageVO.getBeginPerPage()));
		map.put("end", String.valueOf(pageVO.getEndPerPage()));
		// 페이징처리 결과 데이터가 저장이 되어서 반환
		// public List<Map<String, Object>> list(Map<String, Object> map) {
		List<Map<String, Object>> list = galleryService.list(map);
		// 7. 페이지 블록을 구현
		int startPage = (int) ((pageVO.getNowPage() - 1) / pageVO.getPagePerBlock()) * pageVO.getPagePerBlock() + 1;
		int endPage = startPage + pageVO.getPagePerBlock() - 1;
		// 블록 초기화 전체 페이지값보다 크다면 전체 페이지값을 마지막 블록페이지 값으로 저장
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
	
//	@GetMapping("/gdetail")
//	public GalleryVO detail(@RequestParam("num") int num) {
//		return galleryService.detail(num);
//	}
	@GetMapping("/gdetail")
    public Map<String, Object> detail(@RequestParam("num") int num) {
        return galleryService.detail(num);
    }

}









