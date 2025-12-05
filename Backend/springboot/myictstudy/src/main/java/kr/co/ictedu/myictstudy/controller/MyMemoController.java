package kr.co.ictedu.myictstudy.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import kr.co.ictedu.myictstudy.service.MemoService;
import kr.co.ictedu.myictstudy.vo.MemoVO;

@RestController
@RequestMapping("/mymemo")
public class MyMemoController {
	// Service <- VO -> Dao <- VO -> mapper
	@Autowired
	private MemoService memoService;

	@RequestMapping("/list")
	public List<MemoVO> memoList() {
		return memoService.list();// 검색된 내용을 List로 반환
	}

	@PostMapping("/add")
	public ModelAndView addMemo(@RequestBody MemoVO vo) {
		System.out.println("writer :" + vo.getWriter());
		System.out.println("conts:" + vo.getConts());
		System.out.println("mreip:" + vo.getMreip());
		memoService.add(vo);// 데이터베이스에 저장
		return new ModelAndView("redirect:list");// ViewName전달
	}
	@GetMapping("/detail")
	public MemoVO memoDetail(@RequestParam("num") int num) {
		System.out.println("detail =>" + num);
		return memoService.detail(num);
	}
	@DeleteMapping("/delete")
	public ModelAndView memoDelete(@RequestParam("num") int num) {
		System.out.println("삭제 처리 :" + num);
		memoService.del(num);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("redirect:list");
		return mav;
	}
}
