package ex2;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

@WebServlet("/myRefServlet")
public class MyRefServlet extends HttpServlet{
	private ApplicationContext ctx;
	
	@Override
	public void init() throws ServletException {
		ctx = new GenericXmlApplicationContext("ex2/ex2_di.xml");
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		MakeSource ref = ctx.getBean("makeSource",MakeSource.class);
		String res = ref.result();
		req.setAttribute("res", res);
		req.getRequestDispatcher("ex2_di.jsp").forward(req, resp);
		
	}
	
}
