package kr.co.ictedu.myictstudy.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import kr.co.ictedu.myictstudy.vo.MemberVO;

@Mapper
public interface MemberDao {
	void insertMember(MemberVO vo);
	//select count(*) from member where email = #{email}
	
	int countByEmail(String email);
	@Select("SELECT COUNT(*) cnt FROM member WHERE userid = #{id}")
	
	int checkId(String id);
}
