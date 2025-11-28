package kr.co.ictedu.mvc.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.ictedu.mvc.dto.FBoardDTO;

//RePository는 DAO의 데이터 처리를 위한 특별한 빈이기 때문에 어노테이션을 써야 빈으로 등록된다.
@Repository 
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

	@Override
	public void deleteFboard(int num) {
		ss.delete("fb.delete",num);
	}

	@Override
	public void updateFboard(int num) {
		ss.update("fb.update", num);
		
	}

}
