package kr.co.ictedu.mvc.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.ictedu.mvc.dto.UpBoardDTO;

@Repository
public class UpBoardDao implements UpBoardDatoInter{
	@Autowired
	private SqlSessionTemplate ss;
	
	@Override
	public void upboardAdd(UpBoardDTO vo) {
		ss.insert("upboard.add",vo);
	}

	@Override
	public List<UpBoardDTO> upboardList() {
		return ss.selectList("upboard.list");
	}

	@Override
	public int getTotal() {
		return ss.selectOne("upboard.totalCount");
	}

}
