package ex3;
//Advice의 종류
//BeforeAdvice : 타겟 객체의 메서드가 수행되기 [전]에 실행 시켜야 될 공통관심사항
//AfterAdvice : 타켓 객체의 메서드가 수행 된 [후]에 실행 시켜야 될 공통관심사항
//After-throwing Advice : 예외값을 받아서 사용 될 때
//After-returning Advice : 리턴값을 받고 싶을 때
//Around Advice는 절대로 JoinPoint를 생략 할 수 없다 => ProceedingJoingPoint pjp
//반드시 타겟 객체의 호출 시점이 있어야 하는 Advice이다.
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
//ProxyFactoryBean
@Aspect
@Component
public class AopAdvice {
	//Around Advice - joinpoint객체가 반드시 메서드의 인자로 선언 해야 한다. *****
	@Around("execution(* ex3.DaoImple.firstStatementTest(..))")
	public String checkTime(ProceedingJoinPoint pjp) throws Throwable {
		long start = System.currentTimeMillis();
		// target 객체의 메서드를 호출 - 여기서는 first()호출,
		// firstStatementTest() 호출 될때 적용될 공통 코드들 ...
		String res = (String) pjp.proceed();
		long end = System.currentTimeMillis();
		System.out.println("수행 된 속도 :"+(end-start)+"초");
		return res;
	}
	//String result=> 매개변수로 반환받을 변수를 선언한다.
	//returning = "" 매개변수로 반환받을 변수 이름을 작성한다.
	@AfterReturning(pointcut = "execution(* ex3.DaoImple.second(..))"
			,returning = "result")
	public void afterReturnAdvice(String result) {
		System.out.println("비지니스 로직이 수행 된 이후의 반환값을 받는 Advice");
		System.out.println("반환값 :"+result);
	}
	@AfterThrowing(pointcut = "execution(* ex3.DaoImple.third(..))",
			throwing = "ex")
	private void aftertheowingAdvice(Exception ex) {
		System.out.println("예외 메세지 :"+ex.getMessage());
	}
	
	@Before("execution(* ex3.DaoImple.myBefore(..))")
			public void beforeAdvice() {
				System.out.println("Ict Password! BeforeAdvice적용!");
			}
}












