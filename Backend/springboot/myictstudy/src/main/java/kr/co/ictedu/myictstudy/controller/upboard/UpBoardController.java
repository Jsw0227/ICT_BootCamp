package kr.co.ictedu.myictstudy.controller.upboard;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.http.HttpServletRequest;
import kr.co.ictedu.myictstudy.controller.RestMainController;
import kr.co.ictedu.myictstudy.service.UpBoardCommService;
import kr.co.ictedu.myictstudy.service.UpBoardService;
import kr.co.ictedu.myictstudy.vo.PageVO;
import kr.co.ictedu.myictstudy.vo.UpBoardCommVO;
import kr.co.ictedu.myictstudy.vo.UpBoardVO;

@RestController
@RequestMapping("/upboard")
public class UpBoardController {

	@Autowired
	private UpBoardService upBoardService;
	@Autowired
	private PageVO pageVO;
	
	@Autowired
	private UpBoardCommService upBoardCommService;
	
	// @Value : application.properties의 key값으로 설정값을 가져와서 변수에 저장한다.
	// Properties prop = new Properties();
	// prop.load(new FileReader(application.properties))
	// prop.getProperty(key):value
	@Value("${spring.servlet.multipart.location}")
	private String filePath;

	// http://192.168.0.195/myictstudy/upboard/getPath
	@GetMapping("/getPath")
	public String getPathTest() {
		System.out.println("Path:" + filePath);
		return filePath;
	}

	// 파일업로드는 Post방식이다. ***
	// <form method="post" action="upboardAdd" enctype="multipart/form-data">
	// <input type="file" name="mfile"> </form>
	// http://192.168.0.195/myictstudy/upboard/upboardAdd
	@PostMapping("/upboardAdd")
	public ResponseEntity<?> upboardAdd(UpBoardVO vo, HttpServletRequest request) {
		if (!request.getRemoteAddr().equals("192.168.0.195")) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("업로드 금지");
		}
		// VO에 클라이언트의 아이피를 저장
		vo.setReip(request.getRemoteAddr());
		System.out.println("writer:" + vo.getWriter());
		System.out.println("title:" + vo.getTitle());
		System.out.println("content:" + vo.getContent());
		System.out.println("Reip:" + request.getRemoteAddr());
		// temp에 저장이 된상태
		// System.out.println("mfile:"+vo.getMfile().getOriginalFilename());
		System.out.println("============================");
		// 파일업로드 객체를 반환
		MultipartFile mf = vo.getMfile();
		String oriFn = mf.getOriginalFilename();
		System.out.println("파일 이름 :" + oriFn);
		// -----------------------------------------
		StringBuilder path = new StringBuilder();
		path.append(filePath).append("\\");
		path.append(oriFn);
		System.out.println("FullPath : " + path);
		// -----------------------------------------
		File f = new File(path.toString());
		// f에 저장된 파일객체를 사용해서 파일의 내용을 읽어와서 한 바이트씩 f에서 잡은 경로로 작성
		// 개념 InputStream read() -> while -> BufferedOutputStream write(f)
		// transferTo() : MultipartFile 를 사용해서 파일을 복제한다.
		try {
			mf.transferTo(f);// imgfile
			// 업로드 된 파일의 이름을 vo에 저장한다.
			vo.setImgn(oriFn);
			// Service를 통해서 Mapper로 vo 주소 값을 보낸다.
			upBoardService.add(vo);
			return ResponseEntity.ok().body("업로드 성공!");
		} catch (IllegalStateException | IOException e) {
			e.printStackTrace();
		}
		// 실패 했을 경우 응답 처리
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("업로드 실패");

	}
	// http://192.168.0.195/myictstudy/upboard/uplist

//	@GetMapping("/uplist")
//	public List<UpBoardVO> upBoardList(){
//		
//		//페이지 처리를 위한 테스트 
//		Map<String, String> map = new HashMap<>();
//		map.put("begin", String.valueOf(11));
//		map.put("end", String.valueOf(20));
//		return  upBoardService.list(map);
//	}
	// 현재 페이지 번호를 받아와서 연산을 수행
	// http://192.168.0.195/myictstudy/upboard/upList?cPage=1
	// List<UpBoardVO> 를 사용하지 않고 Map<String, Object> 사용하는 이유
	// 데이터 뿐만이 아니라 페이지 처리를 위한 부가 정보도 함께 보낼때 편해서 사용
	//http://192.168.0.195/myictstudy/upboard/upList?cPage=1&searchType=3&searchValue=목요
	@RequestMapping("/upList")
	public Map<String, Object> upBoardList(@RequestParam Map<String, String> paramMap, HttpServletRequest request) {
		System.out.println("Method =>" + request.getMethod());
		// Map의 키값이 파라미터 => cPage
		// 현재 페이지값
		String cPage = paramMap.get("cPage");
		System.out.println("cPage : " + cPage);
		System.out.println("searchType:" + paramMap.get("searchType"));
		System.out.println("searchValue:" + paramMap.get("searchValue"));
		System.out.println("**************************");
		// 1.총 게시물 수 => PageVO에 해당 property에 setter호출해서 값을 저장 해둔다.
		int totalCnt = upBoardService.totalCount(paramMap);
		pageVO.setTotalRecord(totalCnt);
		System.out.println("TotalCount:" + pageVO.getTotalRecord());
		System.out.println("********************************");
		// 2. 총페이지 수 구하기 => totalCnt 총게시물수 / numPerPage; 한 페이지당 보여질 게시물 수
		// 11개의 데이터 -> 11/10.0 => 1.1 => 2
		int totalPage = (int) Math.ceil(totalCnt / (double) pageVO.getNumPerPage());
		pageVO.setTotalPage(totalPage);
		System.out.println("TotalPage :" + pageVO.getTotalPage());
		System.out.println("********************************");
		// 3. 총블록수 저장
		// 전체페이지 / pagePerBlock
		// [1][2][3][4][5]| [6][7][8][][]
		int totalBlock = (int) Math.ceil(totalPage / (double) pageVO.getPagePerBlock());
		pageVO.setTotalBlock(totalBlock);
		System.out.println("TotalBlock:" + pageVO.getTotalBlock());
		System.out.println("********************************");
		// 4.현재 페이지 설정
		if (cPage != null) {
			pageVO.setNowPage(Integer.parseInt(cPage));
		} else {
			pageVO.setNowPage(1);
		}
		System.out.println("cPage:" + pageVO.getNowPage());
		System.out.println("********************************");
		// 5. 현재 페이지의 시작 게시물과 끝 게시물 번호를 계산 해서 pageVO에 저장한다.
		//시작페이지 공식 현재페이지값 - 1 * 페이지당보여줄수 + 1
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
		
	    
	    
	    //6. result Map
	    // json으로 메서드가 반환할 타입이고 그 데이터를 저장할 맵 ---------- 
		Map<String, Object> response = new HashMap<>();
		// 기존의 paramMap에 새로운 데이터를 추가한다. (시작과, 끝 번호를 추가)
	    // mapper에서 사용할 값들을 포함한다.
		Map<String, String> map = new HashMap<>(paramMap);
		map.put("begin", String.valueOf(pageVO.getBeginPerPage()));
	 	map.put("end", String.valueOf(pageVO.getEndPerPage()));
	   //  페이징처리 결과 데이터가 저장이 되어서 반환 
	 	List<UpBoardVO> list = upBoardService.list(map);
	 	System.out.println("List Size =>"+list.size());
	 	//-------------------------------------------------------
	 	// 7. 페이지 블록을 구현 
	 	int startPage = (int)((pageVO.getNowPage() - 1) / pageVO.getPagePerBlock()) 
	 			* pageVO.getPagePerBlock() + 1;
	 	int endPage = startPage + pageVO.getPagePerBlock() - 1;
	 	//블록 초기화 전체 페이지값보다 크다면 전체 페이지값을 마지막 블록페이지 값으로 저장 
	 	if(endPage > pageVO.getTotalPage()) {
	 		endPage = pageVO.getTotalPage();
	 	}
	    System.out.println("7. startPage = " + startPage);
	    System.out.println("7. endPage = " + endPage);
	 	response.put("data", list);  // 페이징 처리가 완료된 리스트를 저장한 데이터
	 	//----------------------------------------------------------
		response.put("totalItems", pageVO.getTotalRecord()); // 전체 게시물의 수 count
		response.put("totalPages", pageVO.getTotalPage()); // 전체 페이지
		response.put("currentPage", pageVO.getNowPage()); // 현재 페이지
		response.put("startPage", startPage); // 블록의 시작
		response.put("endPage", endPage); // 블록의 끝 
		return response;
	}

	// http://192.168.0.195/myictstudy/upboard/updetail?num=1
	@GetMapping("/updetail")
	public UpBoardVO detail(@RequestParam("num") int num) {
		return upBoardService.detail(num);
	}
	
//댓글 입력 -> post => upcommAdd
//Json으로 입력 받아서 vo에 저장
//	public ResponseEntity<?> upBoardComm(______________){
//		System.out.println("vo:"+vo.getUcode());
//		System.out.println("vo:"+vo.getUwriter());
//		System.out.println("vo:"+vo.getUcontent());
//		System.out.println("vo:"+vo.getReip());
//		// 서비스에 addComment(vo)
//		return ResponseEntity.ok().body(1);
//	}
//	//댓글 출력 -> upcommList
//	public _______________ listBoardComm(__________){
//		// 서비스에 listComment()호출해서 반환 
//		
//		return __________________
//	}	
	//http://192.168.0.195/myictstudy/upboard/upcommAdd
//    {
//        
//        "ucode": 66, ->최신 업리스트의 글의 번호로 지정하기 
//        "uwriter": "테스형",
//        "ucontent": "오늘은 월요일 이에요",
//        "reip": "192.168.0.11"
//    }
	@PostMapping("/upcommAdd")
	public ResponseEntity<?> upBoardComm(@RequestBody UpBoardCommVO vo){
		System.out.println("vo:"+vo.getUcode());
		System.out.println("vo:"+vo.getUwriter());
		System.out.println("vo:"+vo.getUcontent());
		System.out.println("vo:"+vo.getReip());
		upBoardCommService.addComment(vo);
		return ResponseEntity.ok().body(1);
	}
	//http://192.168.0.195/myictstudy/upboard/upcommList -> param으로 num값 66
	@GetMapping("/upcommList")
	public List<UpBoardCommVO> listBoardComm(@RequestParam("num") int num){
		return upBoardCommService.listComment(num);
	}
		
}
