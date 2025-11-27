package kr.co.ictedu.mvc.controller.fboard;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FboardController {
	
	@GetMapping("/fboardForm")
	public String fboardForm() {
		return "/fboard/write";
	}
}
