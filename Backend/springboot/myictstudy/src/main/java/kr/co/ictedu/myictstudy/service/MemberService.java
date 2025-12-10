package kr.co.ictedu.myictstudy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.ictedu.myictstudy.dao.MemberDao;
import kr.co.ictedu.myictstudy.vo.MemberVO;

@Service
public class MemberService {
	
	@Autowired
	private MemberDao memberDao;
	
	public void create(MemberVO vo) {
		memberDao.insertMember(vo);
	}
	
	public int checkEmailDuplicate(String email) {
		return memberDao.countByEmail(email);
	}
	
	public int checkID(String id) {
		return memberDao.checkID(id);
	}
	
}
