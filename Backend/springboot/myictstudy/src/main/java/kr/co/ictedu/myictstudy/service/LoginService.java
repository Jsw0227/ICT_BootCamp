package kr.co.ictedu.myictstudy.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.ictedu.myictstudy.dao.LoginDao;
import kr.co.ictedu.myictstudy.vo.MemberVO;
@Service
public class LoginService {
	@Autowired
	private LoginDao loginDao;
	
	public Map<String, Object> loginCheck(MemberVO vo) {
		return loginDao.loginCheck(vo);
	}
	
}
