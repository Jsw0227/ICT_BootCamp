package kr.co.ictedu.mvc.controller.fboard;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import kr.co.ictedu.mvc.dao.FBoardDaoInter;
import kr.co.ictedu.mvc.dto.FBoardDTO;

@Controller
public class FboardController {
	
	@Autowired
	private FBoardDaoInter fBoardDaoInter;
	
	@GetMapping("/fboardForm")
	public String fboardForm() {
		return "/fboard/write";
	}
	
	//<form method="post" action="fboardInsert" autocomplete="off">
	//매개변수로 DTO를 선언만 해도 Form의 파라미터를 매핑해줌(FboardDTO vo)
	@PostMapping("/fboardInsert")
	public String fboardInsert(FBoardDTO vo) {
		System.out.println("subject "+ vo.getSubject());
		System.out.println("writer "+ vo.getWriter());
		fBoardDaoInter.addFBoard(vo);
	
	return "redirect:fboardList";
	}
	
	
	
	@GetMapping("/fboardList")
	public String fboardList(Model m) {
		List<FBoardDTO> flist = fBoardDaoInter.ListFBoard();
		m.addAttribute("flist",flist);
		return "/fboard/list";
	}
	
	//<a href="fboardHit?num=${e.num }" class="link=secondary">
	//fboardHit
	@GetMapping("/fboardHit")
	public String fboardHit(int num) {
		fBoardDaoInter.updateHit(num);
		return "redirect:fboardDetail?num="+num;
	}
	
	@GetMapping("/fboardDetail")
	public String fboardDetail(int num, Model model) {
		FBoardDTO v = fBoardDaoInter.detailFboard(num);
		model.addAttribute("v",v);
		return "/fboard/info";
	}
	
	@GetMapping("/fboardDelete")
	public String fboardDelete(int num) {
		fBoardDaoInter.deleteFboard(num);
		return "redirect:fboardList";
	}
	
	
	@GetMapping("/fboardUpdate")
	public String fboardUpdate(int num) {
		fBoardDaoInter.updateFboard(num);
		return "fboard/update";
	}
	
	
	
}
