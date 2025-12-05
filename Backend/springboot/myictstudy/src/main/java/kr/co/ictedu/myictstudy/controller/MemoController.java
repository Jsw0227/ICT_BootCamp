package kr.co.ictedu.myictstudy.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import kr.co.ictedu.myictstudy.vo.MemoVO;



@RestController
//postman 경로
//http://192.168.0.195/myictstudy/memo/add => post
//대분류 
@RequestMapping("/memo")
public class MemoController {

	//POST방식으로 데이터를 받아보기 1
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
	//memo/list 
	//json형식으로 형식으로 출력을 해준다. @RestController 설정값이다.
	//@RequestMapping(value = "/list", method = RequestMethod.POST) =>@PostMapping 
	//@RequestMapping(value = "/list", method = RequestMethod.POST)
	//jsonArray로 반환 
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
	//jsonObject으로 반환 
	//@RequestParam("파라미터이름") int 매개변수
	@GetMapping("/detail")
	public MemoVO memoDetail(@RequestParam("num") int num) {
		System.out.println("detail =>"+num);
		int i=num;
		MemoVO vo = new MemoVO();
		vo.setNum(i);
		vo.setWriter("테스형"+i);
		vo.setConts("반가워요"+i);
		vo.setMreip("192.168.0.11");
		vo.setMdate(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
		return vo;
	}
	//삭제 처리 =>@DeleteMapping , @PostMapping
	//비동기식으로 method : 'delete; -> 비동기식 RestFulAPI 통신 
	@DeleteMapping("/delete")
	public ModelAndView memoDelete(@RequestParam("num") int num) {
		System.out.println("삭제 처리 :"+num);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("redirect:list");
		return mav;
	}
	

	
	
	
}
