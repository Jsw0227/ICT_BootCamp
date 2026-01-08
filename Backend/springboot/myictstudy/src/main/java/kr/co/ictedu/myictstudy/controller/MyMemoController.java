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
	
	
//	@RequestMapping("/list")
//	public List<MemoVO> memoList() {
//		return memoService.list();// 검색된 내용을 List로 반환
//	}
	@RequestMapping("/list")
	public Map<String, Object> memoList(@RequestParam Map<String,String> paramMap,
			HttpServletRequest request) {	
		System.out.println("Method =>"+request.getMethod());
		//Map의 키값이 파라미터 
		// 현재 페이지값 
		String cPage = paramMap.get("cPage");
		System.out.println("cPage:" + cPage);
		System.out.println("********************************");
		// 1. 총 게시물 수
		int totalCnt = memoService.totalCount();
		pageVO.setTotalRecord(totalCnt);
		System.out.println("TotalCount:"+pageVO.getTotalRecord());
		System.out.println("********************************");
		// 2. 총페이지 수 구하기 => totalCnt 총게시물수 / numPerPage; 한 페이지당 보여질 게시물 수
		// 11개의 데이터 -> 11/10.0 => 1.1 => 2
		int totalPage = (int) Math.ceil(totalCnt/ (double) pageVO.getNumPerPage());
		pageVO.setTotalPage(totalPage);
		System.out.println("TotalPage :"+pageVO.getTotalPage());
		System.out.println("********************************");
		// 3. 총블록수 저장 
		// 전체페이지 / pagePerBlock 
		// [1][2][3][4][5]| [6][7][8][][]
		int totalBlock =  (int) Math.ceil(totalPage /(double) pageVO.getPagePerBlock());
		pageVO.setTotalBlock(totalBlock);	
		System.out.println("TotalBlock:"+pageVO.getTotalBlock());
		System.out.println("********************************");
		// 4.현재 페이지 설정
		if(cPage != null) {
			pageVO.setNowPage(Integer.parseInt(cPage));
		}else {
			pageVO.setNowPage(1);
		}
		System.out.println("cPage:"+pageVO.getNowPage());

		System.out.println("********************************");
		//5. 현재 페이지의 시작 게시물과 끝 게시물 번호를 계산 해서 pageVO에 저장한다.
		//공식에 값을 대입해서 결과를 예측 해보자.
		//cPage가 1일때 = 1 - 1 * 10 + 1 
		//시작페이지가 0이면 안되기때문에 +1 을 더함 => 1
		//cPage가 2일때 = 2 - 1 * 10 + 1 => 11
	    //단이 5 단이고 범위가 3이다.
	    // 4 5 6
	    //for(int j=4; j<7; j++)
		pageVO.setBeginPerPage((pageVO.getNowPage() - 1) * pageVO.getNumPerPage() + 1);
		pageVO.setEndPerPage(pageVO.getBeginPerPage() + pageVO.getNumPerPage() - 1);
	    System.out.println("5. beginPerPage = " + pageVO.getBeginPerPage());
	    System.out.println("5. endPerPage = " + pageVO.getEndPerPage());
	    System.out.println("********************************");
//		Map<String, String> map = new HashMap<>();
//		map.put("begin", String.valueOf(1));
//		map.put("end", String.valueOf(10));
		//return upBoardService.list(map);
	    //6.
	    // json으로 메서드가 반환할 타입이고 그 데이터를 저장할 맵 ---------- 
	 	Map<String, Object> response = new HashMap<>();
	    // 기존의 paramMap에 새로운 데이터를 추가한다. (시작과, 끝 번호를 추가)
	 	// mapper에서 사용할 값들을 포함한다.
	 	Map<String, String> map = new HashMap<>(paramMap);
	 	map.put("begin", String.valueOf(pageVO.getBeginPerPage()));
	 	map.put("end", String.valueOf(pageVO.getEndPerPage()));
	//  페이징처리 결과 데이터가 저장이 되어서 반환 
		 	List<MemoVO> list = memoService.list(map);
		 	System.out.println("List Size =>"+list.size());
		 	
		 	// 7. 페이지 블록을 구현 
		 	int startPage = (int)((pageVO.getNowPage() - 1) / pageVO.getPagePerBlock()) 
		 			* pageVO.getPagePerBlock() + 1;
		 	int endPage = startPage + pageVO.getPagePerBlock() - 1;
		 	//블록 초기화 전체 페이지값보다 크다면 전체 페이지값을 마지막 블록페이지 값으로 저장 
		 	if(endPage > pageVO.getTotalPage()) {
		 		endPage = pageVO.getTotalPage();
		 	}
		    System.out.println("6. startPage = " + startPage);
		    System.out.println("6. endPage = " + endPage);
		    response.put("data", list);  // 페이징 처리가 완료된 리스트를 저장한 데이터
		    response.put("totalItems", pageVO.getTotalRecord()); // 전체 게시물의 수 count
		    response.put("totalPages", pageVO.getTotalPage()); // 전체 페이지
		    response.put("currentPage", pageVO.getNowPage()); // 현재 페이지
		    response.put("startPage", startPage); // 시작
		    response.put("endPage", endPage); // 끝 
			return response;	
	}
	@PostMapping("/add")
	public ModelAndView addMemo(@RequestBody MemoVO vo,HttpServletRequest request) {
		vo.setMreip(request.getRemoteAddr().toString());
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
