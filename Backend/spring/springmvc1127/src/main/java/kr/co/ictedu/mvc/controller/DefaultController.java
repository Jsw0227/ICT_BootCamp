package kr.co.ictedu.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller // 모델이라고 선언해주는 빈 -> Controller와 통신하는 최전방의 model이다. Controller XXXX
public class DefaultController {

	@GetMapping(value= {"/main",""})
	public String main(Model m) {
		m.addAttribute("msg","나의 첫번째 스프링 MVC");
		return "main/index";
	}
}
