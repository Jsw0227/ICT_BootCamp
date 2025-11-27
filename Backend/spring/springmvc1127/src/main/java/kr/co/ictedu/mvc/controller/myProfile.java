package kr.co.ictedu.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import kr.co.ictedu.mvc.vo.MyProfileVO;

@Controller
public class myProfile {

	@GetMapping(value= {"/myProfile"})
	public String main(Model m) {
		MyProfileVO vo = new MyProfileVO();
		vo.setAddr("인천");
		vo.setAge(20);
		vo.setName("테스형");
		m.addAttribute("vo",vo);
		return "main/myProfile";
	}
	
}
