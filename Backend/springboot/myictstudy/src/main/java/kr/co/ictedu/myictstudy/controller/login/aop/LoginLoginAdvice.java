package kr.co.ictedu.myictstudy.controller.login.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import kr.co.ictedu.myictstudy.dao.MyLogDao;
import kr.co.ictedu.myictstudy.vo.MemberVO;
import kr.co.ictedu.myictstudy.vo.MyLoginLoggerVO;

//@Component
//@Aspect
public class LoginLoginAdvice {
// login 정보 저장 
// 세션획득 (성공)  -> 로그를 저장 
	
//logout 
//세션 삭제전에 -> 로그를 저장 
	@Autowired
	private MyLogDao myLogDao;
	private void createLoggin(String methodName, Object[] fd, 
			ProceedingJoinPoint jp,
			String status) {
		MyLoginLoggerVO lvo = new MyLoginLoggerVO();
		//첫번째 매개변수 등의 규칙을 정해놓고 사용하니까 이렇게 간단하게 배열의 인덱스로 구분이 가능하다.
		if (fd[0] instanceof HttpSession && fd[1] instanceof HttpServletRequest) {
			// 캐스팅
			HttpSession session = (HttpSession) fd[0];
			HttpServletRequest request = (HttpServletRequest) fd[1];
			// 세션으로 부터 로그인 된 정보를 받아 온다. *****
			MemberVO vo = (MemberVO) session.getAttribute("loginMember");
			if (vo != null) { // 로그인 된 정보가 있다는 것 
				lvo.setIdn(vo.getUserid());
				lvo.setStatus(status);// 로그인/ 로그아웃
				lvo.setReip(request.getRemoteAddr());
				String userAgent = request.getHeader("User-Agent");
				String parsedAgent = UserAgentUtils.parseAgent(userAgent);
				lvo.setUagent(parsedAgent);
				System.out.println("로그인 기록: " + lvo);
				System.out.println("IDN :" + lvo.getIdn());
				System.out.println("Agent :" + lvo.getUagent());
				System.out.println("reip:" + lvo.getReip());
				System.out.println("status:" + lvo.getStatus());
				System.out.println("-------------------------------");
				myLogDao.addLoginLogging(lvo);
			}
		}
	}
	@Around("execution(* kr.co.ictedu.myictstudy.controller.login.LoginController.doLog*(..))")
	public String  loginLogger(ProceedingJoinPoint jp) {
		// 해당 타겟의 메서드 doLog로 시작하는 메서드의 매개변수들을 배열로 받아온다.
		Object[] fd = jp.getArgs();
		String  rpath = null;
		//  doLog로 시작하는 메서드의 정확한 이름 반환
		String methodName = jp.getSignature().getName();
		try {
			if (methodName.equals("doLogin")) {
				rpath =  (String) jp.proceed(); //doLogin()메서드를 호출
				createLoggin(methodName, fd, jp, "login"); // 로그를 저장하기 위한 메서드를 호출
			} else if (methodName.equals("doLogout")) {
				createLoggin(methodName, fd, jp, "logout"); // 로그를 저장하기 위한 메서드를 호출
				rpath = (String) jp.proceed(); //doLogout()메서드를 호출 - 세션이 사라짐 
			}
		} catch (Throwable e) {
			e.printStackTrace();
		}
		System.out.println("return:"+rpath);
		return rpath;
	}
}








