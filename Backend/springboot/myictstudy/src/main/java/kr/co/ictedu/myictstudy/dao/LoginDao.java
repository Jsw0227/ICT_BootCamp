package kr.co.ictedu.myictstudy.dao;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import kr.co.ictedu.myictstudy.vo.MemberVO;
@Mapper
public interface LoginDao {

	@Select("select name, COUNT(*) cnt from member where \r\n"
			+ "userid=#{userid} and password=#{password} GROUP BY NAME")
	Map<String, Object> loginCheck(MemberVO vo);
}



