package ex3;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
//ProxyFactoryBean
@Aspect
@Component
public class AopAdvice { //공통 관심사, DaoImple에 곳곳에 필요하다
	//Around Advice - joinpoint 객체가 반드시 메서드의 인자로 선언해야한다.*****
	@Around("execution(* ex3.DaoImple.firstStatementTest(..))")
	
	public String checkTime(ProceedingJoinPoint pip) throws Throwable{
		long start = System.currentTimeMillis();
		// target 객체의 메서드를 호출 - 여기서는 first()호출,
		// firstStatementTest() 호출 될 때 적용 될 공통 코드들
		String res = (String) pip.proceed();
		long end = System.currentTimeMillis();
		System.out.println("수행 된 속도" + (end-start) + "초");
		return res;
	}
}
