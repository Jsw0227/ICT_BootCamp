package ex3;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class AopMain {
public static void main(String[] args) {
	ApplicationContext ctx = 
			new GenericXmlApplicationContext("ex3/ex1_aop.xml");

	DaoInter dao = ctx.getBean("dao", DaoInter.class);
	String msg = dao.firstStatementTest(1);
	System.out.println("msg:"+msg);
}
}
