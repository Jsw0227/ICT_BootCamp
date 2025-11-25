package model;

import java.io.IOException;

import controller.ActionFoward;
import dao.BoardDaoImple;
import dao.BoardDaoInter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vo.BoardVO;

//cmd=bdtail
//bdtail=model.BoardDetailAction

public class BoardDetailAction implements Action{

	@Override
	public ActionFoward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		int num = Integer.parseInt(request.getParameter("num"));
		BoardDaoInter dao = BoardDaoImple.getdao();
		BoardVO vo = dao.detailBoard(num);
		request.setAttribute("vo", vo);
		
		return new ActionFoward("boardDetail",false);
	}

}
