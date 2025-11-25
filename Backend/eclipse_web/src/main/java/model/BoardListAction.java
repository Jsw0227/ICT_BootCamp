package model;

import java.io.IOException;
import java.util.List;

import controller.ActionFoward;
import dao.BoardDaoImple;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vo.BoardVO;

public class BoardListAction implements Action{
@Override
public ActionFoward execute(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
	
	List<BoardVO> list = BoardDaoImple.getdao().listBoard();
	
	request.setAttribute("blist", list);
	return new ActionFoward("boardList",false);
}
}
