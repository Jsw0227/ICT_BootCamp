package ex1;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

@WebServlet("/ex2_ResourceServlet")
public class Ex2_ResourceServlet extends HttpServlet{
	private ApplicationContext ctx;
	@Override
	public void init() throws ServletException {
		ctx = new GenericXmlApplicationContext("ex1/ex2_auto.xml");
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Ex2_Resource ref = ctx.getBean("ex2_res", Ex2_Resource.class);
		
		String msg = ref.getRes().resource1();
		req.setAttribute("res", msg);
		req.getRequestDispatcher("ex1_auto.jsp").forward(req, resp);
	}
}






