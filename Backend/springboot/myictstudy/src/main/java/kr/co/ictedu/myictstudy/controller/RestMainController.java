package kr.co.ictedu.myictstudy.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
//HandlerMapping에 해당하는 메서드의 반환형이 String
//@Controller 가 아니라 @RestController 라면
//뷰의 이름 대신에 내용이 브라우저에 전달 된다. *****
@RestController
public class RestMainController {

	@GetMapping("/hello")
	public String getHello() {
		return "안녕하세요 나의 첫 스프링 부트 입니다.";
	}
	
}
