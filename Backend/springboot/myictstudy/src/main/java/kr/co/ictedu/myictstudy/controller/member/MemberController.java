package kr.co.ictedu.myictstudy.controller.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kr.co.ictedu.myictstudy.service.MemberService;
import kr.co.ictedu.myictstudy.vo.MemberVO;

@RestController
@RequestMapping("/member")
public class MemberController {
	
	@Autowired
	private MemberService memberService;
	
	@PostMapping("/signup")
	public ResponseEntity<?> memberjoin(MemberVO memberDTO){
		memberService.create(memberDTO);
		return ResponseEntity.ok().build();
	}
	
	@GetMapping("/idCheck")
	public int idCheck(@RequestParam("id") String id) {
		return memberService.checkID(id);
	}
}
