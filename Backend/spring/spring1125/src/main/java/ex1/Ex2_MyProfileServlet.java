package ex1;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
@WebServlet("/ex2_myprofile")
public class Ex2_MyProfileServlet extends HttpServlet{
	private ApplicationContext ctx;
	
	@Override
	public void init() throws ServletException {
		ctx = new GenericXmlApplicationContext("ex1/ex2.xml");
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Ex2_MyProfile ref = ctx.getBean("ex2",Ex2_MyProfile.class);
		req.setAttribute("myProfile", ref.printProfile());
		req.getRequestDispatcher("ex2_MyProfile.jsp").forward(req, resp);
	
	}
}
