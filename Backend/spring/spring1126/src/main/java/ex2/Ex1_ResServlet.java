package ex2;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
@WebServlet("/ex1_ResServlet")
public class Ex1_ResServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;
	private ApplicationContext ctx;
	@Override
	public void init() throws ServletException {
		ctx = new GenericXmlApplicationContext("ex2/ex1_res.xml");
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Ex1_Resource res = ctx.getBean("ex1_res", Ex1_Resource.class);
		Ex1_MyResource myRes = res.getRes();
		String result = myRes.resource1();//æ»≥Á«œººø‰1
		req.setAttribute("res", result); //msg = æ»≥Á«œººø‰1
		req.getRequestDispatcher("ex1_auto.jsp").forward(req, resp);
	}

}

