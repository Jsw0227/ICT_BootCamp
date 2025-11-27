package kr.co.ictedu.mvc.dao;

import java.util.List;

import kr.co.ictedu.mvc.dto.FBoardDTO;

public interface FBoardDaoInter {
	//<select id="list" resultType="fvo">
	List<FBoardDTO> ListFBoard();
	
	//<insert id="add" parameterType="fvo">
	void addFBoard(FBoardDTO vo);
	
	void updateHit(int num);
	
	//<select id="detail" paramete<select id="detail" parameterType="int" resultType="fvo">rType="int" resultType="fvo">
	FBoardDTO detailFboard(int num);
}
