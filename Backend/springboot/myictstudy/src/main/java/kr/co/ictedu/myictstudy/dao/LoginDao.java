package kr.co.ictedu.myictstudy.dao;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import kr.co.ictedu.myictstudy.vo.MemberVO;

@Mapper
public interface LoginDao {
	
	@Select("SELECT NAME, COUNT(*) cnt FROM MEMBER WHERE userid=#{userid} AND password=#{password} GROUP BY NAME")
	Map<String, Object> loginCheck(MemberVO vo);
}
