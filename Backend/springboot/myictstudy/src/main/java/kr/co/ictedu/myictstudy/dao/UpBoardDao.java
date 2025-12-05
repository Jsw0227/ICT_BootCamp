package kr.co.ictedu.myictstudy.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import kr.co.ictedu.myictstudy.vo.UpBoardVO;

@Mapper
public interface UpBoardDao {
	void add(UpBoardVO vo);
	//<select id="list" resultType="upvo" parameterType="map">
	//#{begin} AND #{end} => Map으로 변경
	List<UpBoardVO> list(Map<String, String> map);
	void hit(int num);
	UpBoardVO detail(int num);
	void delete(int num);
	int totalCount();
}



















