package controller;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Action;

@WebServlet("*.ict")
public class ControllServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String cmd = request.getParameter("cmd");
		if(cmd != null) {
			ActionFactory factory = ActionFactory.getInstance();
			Action action = factory.getAction(cmd);
			ActionFoward af = action.execute(request, response);
			
			System.out.println(af.getUrl());
			if(af.isMethods()){
				response.sendRedirect(af.getUrl());
			}else { // cmd라는 파라미터가 없으면 동작한다.
				String viewName = "WEB-INF/views/" + af.getUrl() + ".jsp"; // 포워딩으로 끌어 올 수는 있지만 jsp로 요청하면 보안이 되기 때문에 오류가 발생한다.
				request.getRequestDispatcher(viewName).forward(request, response);
				}
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			out.print("<h2 style=\"color:red\"> 올바른 요청이 아닙니다</h2>");
		}

	}

}
