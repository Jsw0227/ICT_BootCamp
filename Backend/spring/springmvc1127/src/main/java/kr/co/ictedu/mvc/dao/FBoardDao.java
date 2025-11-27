package kr.co.ictedu.mvc.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import kr.co.ictedu.mvc.dto.FBoardDTO;

public class FBoardDao implements FBoardDaoInter {

	@Autowired
	private SqlSessionTemplate ss;

	@Override
	public List<FBoardDTO> ListFBoard() {
		return ss.selectList("fb.list");
	}

	@Override
	public void addFBoard(FBoardDTO vo) {
		ss.insert("fb.add", vo);
	}

	@Override
	public void updateHit(int num) {
		ss.update("fb.hit", num);
	}

	@Override
	public FBoardDTO detailFboard(int num) {
		return ss.selectOne("fb.detail", num);
	}

}
