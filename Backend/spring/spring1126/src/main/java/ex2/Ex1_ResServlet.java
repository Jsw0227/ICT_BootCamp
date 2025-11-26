package ex2;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import ex1.Ex1_AutoWired;
@WebServlet("/ex1_ResServlet")
public class Ex1_ResServlet extends HttpServlet{
	private ApplicationContext ctx;
	private static final long serialVersionUID = 1L;

	@Override
	public void init() throws ServletException {
		ctx = new GenericXmlApplicationContext("ex2/ex1_res.xml");
		//GenericXmlApplicationContext 실제 컨테이너 객체
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Ex1_Resource res = ctx.getBean("ex1_res",Ex1_Resource.class);
		Ex1_MyResource myRes = res.getRes();
		String result = myRes.resource1();
		req.setAttribute("res", result);
		req.getRequestDispatcher("ex1_auto.jsp").forward(req, resp);
	}
}
