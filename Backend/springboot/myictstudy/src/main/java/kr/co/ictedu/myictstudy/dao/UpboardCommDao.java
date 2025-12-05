package kr.co.ictedu.myictstudy.dao;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import kr.co.ictedu.myictstudy.vo.UpBoardCommVO;
@Mapper
public interface UpboardCommDao {
	//addComment , listComment
	void addComment(UpBoardCommVO comment);
	List<UpBoardCommVO> listComment(int num);
}




