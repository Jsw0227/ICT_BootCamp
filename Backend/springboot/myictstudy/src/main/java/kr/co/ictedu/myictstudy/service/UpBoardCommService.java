package kr.co.ictedu.myictstudy.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.ictedu.myictstudy.dao.UpboardCommDao;
import kr.co.ictedu.myictstudy.vo.UpBoardCommVO;
//서비스는 Dao를 불러와서 해당 작업을 한다.
@Service
public class UpBoardCommService {
	@Autowired
	private UpboardCommDao upboardCommDao;
	
	public void addComment(UpBoardCommVO vo) {
		upboardCommDao.addComment(vo);
	}
	public List<UpBoardCommVO> listComment(int num){
		return upboardCommDao.listComment(num);
	}
}




