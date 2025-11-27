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
public class Ex1_AutoWiredServlet extends HttpServlet{

	private ApplicationContext ctx;
	@Override
	public void init() throws ServletException {
		ctx = new GenericXmlApplicationContext("ex1/ex1_auto.xml");
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Ex1_AutoWired ref = ctx.getBean("auto1",Ex1_AutoWired.class);
		String res = ref.getMessage();
		req.setAttribute("res", res);
		req.getRequestDispatcher("ex1_auto.jsp").forward(req, resp);
	}
}














