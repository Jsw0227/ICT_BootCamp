package ex1;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
@WebServlet("/autoWiredServlet")
public class Ex2_AutoWiredServlet extends HttpServlet{
	
	private ApplicationContext ctx; //spring container : 의존성 관련객체가 모두 저장
	@Override //상속관계에서 부모의 메서드를 자식이 고쳐쓰기 위해서 구현하는 방식;재정의 어노테이션
	public void init() throws ServletException { //init : servlet에서 한번 실행
		ctx = new GenericXmlApplicationContext("ex1/ex1_auto.xml"); //ctx : 변수, GenericXmlApplicationContext : 변수형, 생성, 실제 컨테이너 객체
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Ex1_AutoWired ref = ctx.getBean("auto1",Ex1_AutoWired.class);
		String res = ref.getMessage();
		req.setAttribute("res", res);
		req.getRequestDispatcher("ex1_auto.jsp").forward(req, resp);
	}
}
