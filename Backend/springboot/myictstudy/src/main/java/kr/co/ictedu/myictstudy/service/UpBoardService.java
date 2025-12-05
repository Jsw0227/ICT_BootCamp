package kr.co.ictedu.myictstudy.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.ictedu.myictstudy.dao.UpBoardDao;
import kr.co.ictedu.myictstudy.vo.UpBoardVO;

@Service
public class UpBoardService {
	@Autowired
	private UpBoardDao upBoardDao;
	public void add(UpBoardVO vo) {
		upBoardDao.add(vo);
	}
	public List<UpBoardVO> list(Map<String, String> map) {
		return upBoardDao.list(map);
	}
	void hit(int num) {
		upBoardDao.hit(num);
	}
	public UpBoardVO detail(int num) {
		hit(num);//조회수를 증가 한 후 상세보기를 호출해서 반환 받음 
		return upBoardDao.detail(num);
	}
	public int totalCount() {
		return upBoardDao.totalCount();
	}
}




