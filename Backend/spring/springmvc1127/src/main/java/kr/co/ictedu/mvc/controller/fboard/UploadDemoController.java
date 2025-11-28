package kr.co.ictedu.mvc.controller.fboard;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import kr.co.ictedu.mvc.dao.UpBoardDatoInter;
import kr.co.ictedu.mvc.dto.UpBoardDTO;

@Controller
public class UploadDemoController {
	@Autowired
	private UpBoardDatoInter upBoardDaoInter;
	@GetMapping("/upform")
	public String upform() {
		return "updemo/upForm";
	}

	// <form action="uploadpro" method="post"
	@PostMapping("/uploadpro")
	public String uploadFile(Model m, UpBoardDTO vo, HttpServletRequest request) {
		// 파라미터 테스트
		System.out.println("Title :" + vo.getTitle());
		System.out.println("content :" + vo.getContent());
		// MultipartFile 객체에서 이름 확인 - 파일업로드 처리
		MultipartFile mf = vo.getMfile();
		String oriFn = mf.getOriginalFilename();//파일의 이름
		System.out.println("oriFn:" + oriFn + ",length" + oriFn.length());
		
		if(oriFn.length() > 0) { //파일이 업로드가 되었을 때 
			//이미지 경로 설정 하기 
			String img_path = "resources\\imgfile";
			//Context상의 이미지 경로 얻기 
			String r_path = request.getSession().getServletContext().getRealPath("/");
			System.out.println("r_path:" + r_path);
			// 이미지가 저장될 경로 만들기
			StringBuffer path = new StringBuffer();
			path.append(r_path).append(img_path).append("\\");
			path.append(oriFn);
			System.out.println("FullPath :" + path);
			File f = new File(path.toString());
			if(!f.exists()) { //경로가 존재하지 않으면
				f.mkdirs(); // 경로를 만들겠다.
			}
			// 임시 메모리에 담긴 즉 업로드한 파일의 값 -> File클래스의 경로로 복사
			try {
				mf.transferTo(f);
			} catch (IllegalStateException | IOException e) {
				e.printStackTrace();
			}
			//Dao로 전달 할 때 이미지이름을 데이터 베이스에 저장하기 위한 설정 
			vo.setImgn(oriFn);
			long size = mf.getSize();
			String contentType = mf.getContentType();
			System.out.println("파일 크기 :" + size);
			System.out.println("파일의 type :" + contentType);
		}
		//데이터 베이스에 저장하기 
		upBoardDaoInter.upboardAdd(vo);
		return "redirect:upList";
	}
	@RequestMapping("/upList")
	public String upBoardList(Model model) {
		List<UpBoardDTO> list = upBoardDaoInter.upboardList();
		model.addAttribute("list", list);
		return "updemo/uplist";
	}
}
