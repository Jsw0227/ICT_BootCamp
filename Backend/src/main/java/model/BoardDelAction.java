package model;

import java.io.IOException;

import controller.ActionFoward;
import dao.BoardDaoImple;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
//cmd=bdel
//bdel=model.BoardDelAction
public class BoardDelAction implements Action{

	@Override
	public ActionFoward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int num = Integer.parseInt(request.getParameter("num"));
		BoardDaoImple.getdao().delBoard(num);
		return new ActionFoward("main.ict?cmd=boardList",true);
	}

}
