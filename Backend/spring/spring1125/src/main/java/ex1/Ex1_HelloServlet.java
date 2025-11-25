package ex1;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
@WebServlet("/ex1_Hello")
public class Ex1_HelloServlet extends HttpServlet{
	//ApplicationContext 스프링 컨테이너를 선언
	private ApplicationContext ctx;
	@Override
	public void init() throws ServletException {
		//스프링 컨테이너가 beans의 빈을 싱글톤으로 등록해서 미리 생성한다.
		ctx = new GenericXmlApplicationContext("ex1/ex1.xml");
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//빈의 id를 불러와서 사용한다.
		Ex1_Hello ref = ctx.getBean("ex1",Ex1_Hello.class);
		String msg = ref.printHello();
		req.setAttribute("msg", msg);
		req.getRequestDispatcher("ex1_hello.jsp").forward(req, resp);
	}
	
	
}
