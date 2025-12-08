package kr.co.ictedu.myictstudy.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;
import kr.co.ictedu.myictstudy.service.MemoService;
import kr.co.ictedu.myictstudy.vo.MemoVO;
import kr.co.ictedu.myictstudy.vo.PageVO;

@RestController
@RequestMapping("/mymemo")
public class MyMemoController {
	// Service <- VO -> Dao <- VO -> mapper
	@Autowired
	private MemoService memoService;
	
	@Autowired
	private PageVO pageVO;
	
	@RequestMapping("/list")
	public Map<String,Object> memoList(@RequestParam Map<String,String> paramMap, HttpServletRequest request){
		String cPage = paramMap.get("cPage");
		int totalCnt = memoService.totalCount();
		int totalPage = (int) Math.ceil(totalCnt / (double)pageVO.getNumPerPage());
		pageVO.setTotalPage(totalPage);
		int totalBlock = (int) Math.ceil(totalPage / (double) pageVO.getPagePerBlock());
		pageVO.setTotalBlock(totalBlock);
		
		if(cPage != null) {
			pageVO.setNowPage(Integer.parseInt(cPage));
		}else {
			pageVO.setNowPage(1);
		}
		
		pageVO.setBeginPerPage((pageVO.getNowPage()-1) * pageVO.getNumPerPage() +1);
		pageVO.setEndPerPage(pageVO.getBeginPerPage() + pageVO.getNumPerPage() -1);
		Map<String,Object> response = new HashMap<>();
		Map<String,String> map = new HashMap<>(paramMap);
		map.put("begin",String.valueOf(pageVO.getBeginPerPage()));
		map.put("end", String.valueOf(pageVO.getEndPerPage()));
		int startPage = (int)((pageVO.getNowPage()-1) / pageVO.getPagePerBlock()) * pageVO.getPagePerBlock() +1;
		int endPage = startPage + pageVO.getPagePerBlock() -1;
		if(endPage > pageVO.getTotalPage()) {
			endPage = pageVO.getTotalPage();
		}
		response.put("totalItems", pageVO.getTotalRecord());
		response.put("totalPages", pageVO.getTotalPage());
		response.put("currentPage", pageVO.getNowPage());
		response.put("startPage", startPage);
		response.put("endPage", endPage);
		return response;
		
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
