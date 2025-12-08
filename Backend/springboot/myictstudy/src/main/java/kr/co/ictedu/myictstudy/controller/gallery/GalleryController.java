package kr.co.ictedu.myictstudy.controller.gallery;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
	private GalleryService galleryservice;
	
	@Value("${spring.servlet.multipart.location}")
	private String uploadDir;
	
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
	
	
}
