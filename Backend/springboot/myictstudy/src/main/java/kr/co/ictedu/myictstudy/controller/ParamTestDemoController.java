package kr.co.ictedu.myictstudy.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import kr.co.ictedu.myictstudy.vo.MemoVO;

@RestController
//모든 요청에서 demo라고 왔을 때 현재 컨트럴러가 응답
@RequestMapping("/demo")
public class ParamTestDemoController {

	//POST방식으로 데이터를 받아보기 1
	//기존의 FormData형식 
	@PostMapping("/add")
	public ModelAndView addMemo(MemoVO vo) {
		System.out.println(vo.getWriter());
		System.out.println(vo.getConts());
		System.out.println(vo.getMreip());
		ModelAndView mav = new ModelAndView();
		mav.addObject("vo", vo);
		//입력 처리후 ModelAndView 객체를 사용해서 리다이렉트로 이동하는 방법 , 스프링 MVC와 동일하다.
		mav.setViewName("redirect:list");
		return mav;
	}

	//POST방식으로 데이터를 받아보기 2
	//@RequestBody => post방식으로 데이터가 json형식으로 전송되어 올때 DTO에 저장하기 위한
    //방법 *****
	/* react에서 데이터를 전송하는 형태
	 *{
        "num": 2,
        "writer": "테스형",
        "conts": "오늘은 월요일!!!!!!",
        "mreip": "192.168.0.11",
        "mdate": "2025-12-01"
    }
	 * */
	@PostMapping("/addJson")
	public ModelAndView addMemoJson(@RequestBody MemoVO vo) {
		System.out.println("AddJSon");
		System.out.println("Num :"+vo.getNum());
		System.out.println("Writer:"+vo.getWriter());
		System.out.println("Conts :"+vo.getConts());
		System.out.println("Reip :"+vo.getMreip());
		System.out.println("Date :"+vo.getMdate());
		ModelAndView mav = new ModelAndView();
		mav.addObject("vo", vo);
		//입력 처리후 ModelAndView 객체를 사용해서 리다이렉트로 이동하는 방법 , 스프링 MVC와 동일하다.
		mav.setViewName("redirect:list");
		return mav;
	}
	
	
	
	@RequestMapping("/list") 
	public List<MemoVO> memoList(Model m){
		List<MemoVO> list = new ArrayList<>();
		
		for(int i=0; i<3; i++) {
			MemoVO vo = new MemoVO();
			vo.setNum(i+1);
			vo.setWriter("테스형"+i);
			vo.setConts("반가워요"+i);
			vo.setMreip("192.168.0.11");
			vo.setMdate(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
			list.add(vo);
		}
		return list;
	}
	//@RequestParam(value="ParamName",defaultValue="기본값")
	//http://192.168.0.195/myictstudy/demo/uname?user=test
	//defaultValue테스트
	//http://192.168.0.195/myictstudy/demo/uname?user=
	//http://192.168.0.195/myictstudy/demo/uname
	@GetMapping("/uname")
	public String getMemo(@RequestParam(value = "user",
			defaultValue = "테스형"
			) String writer) {
		//String writer = request.getParameter("user");
		System.out.println("writer:"+writer);
		return "작성자:"+ writer;
	}
	//@PathVariable : URL 경로 자체에 포함된 값을 추출해서 컨트롤러 메서드의 파라미터로 
	//전달해주는 어노테이션
	//@Mapping("/path/{pval}") => 매개변수 (@PathVariable("pval") 매개변수) 
	//http://192.168.0.195/myictstudy/demo/mybooks/11
	@GetMapping("/mybooks/{num2}")
	public String getMemo(@PathVariable("num2") int num) {
		System.out.println("PathVariable num: " + num);
		return "Memo 번호: " + num;
	}
	//http://192.168.0.195/myictstudy/demo/mybooks/테스형/반가워요
	@PostMapping("/mybooks/{writer}/{conts}")
	public String postMemo(
			@PathVariable("writer") String writer,
			@PathVariable("conts") String conts
			) {
		System.out.println("작성자: " + writer);
        System.out.println("내용: " + conts);
		return "작성 완료";
	}
	//기본이 ,required = true 이다.
	//http://192.168.0.195/myictstudy/demo/yourbooks/10
	//http://192.168.0.195/myictstudy/demo/yourbooks/10/작성자
	@GetMapping({"/yourbooks/{num}", "/yourbooks/{num}/{writer}"})
	public String getMemo(
	        @PathVariable(value="num") int num ,
	        @PathVariable(value = "writer", required = false)  String writer) {
		 if (writer == null) {
		        writer = "테스형"; 
		    }
		System.out.println("번호: " + num + ", 작성자: " + writer);
		return "번호: " + num + ", 작성자: " + writer;
	}
	//어떤 차이가 있을까요?
	//http://192.168.0.195/myictstudy/demo/yourbooks2/11
	//http://192.168.0.195/myictstudy/demo/yourbooks2/11?writer=엑스맨
	@GetMapping("/yourbooks2/{num}")
	public String getMemo2(
	        @PathVariable("num") int num,
	        @RequestParam(value = "writer", defaultValue = "테스형") String writer) {
	    return "번호: " + num + ", 작성자: " + writer;
	}
	//Header : 브라우저 정보등을 Header를 사용해서 받을 수 있음
	//웹브라우저에서 get방식으로 테스트 해보기
	//매개변수 어노테이션 : @RequestHeader("해더값")
	//Chrome => http://192.168.0.195/myictstudy/demo/agent
	//Edg 
	@GetMapping("/agent")
	public String userAgent(@RequestHeader("User-Agent") String userAgent) {
		System.out.println("userAgent:"+userAgent);
		String browser = "알수 없음";
		//String 클래스의 contains("매칭문자열") 메서드 => 매칭문자열이 포함되면 true
		if(userAgent.contains("Edg/")) {
			browser ="마이크로소프트 엣지 브라우저";
		}else if(userAgent.contains("Chrome/")) {
			browser ="Chrome 브라우저";
		}else if(userAgent.contains("Firefox/")) {
			browser ="Firefox 브라우저";
		}else {
			browser ="설치되지 않은 브라우저";
		}
		return "브라우저 Agent 정보"+browser;
	}
	//main 프로젝트때 보안 설정 
	//@RequestHeader를 사용해서 Authorization 인증 해더값을 받아오기 
	//POSTMAN : Bearer Token 임의의 토큰을 보내보자.
	@GetMapping("/auth")
	public String auth(@RequestHeader("Authorization") String authHeader) {
		return "인증된 토큰 값 :"+authHeader;
	}
}





















