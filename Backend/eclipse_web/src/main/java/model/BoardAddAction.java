package model;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.BeanUtils;

import controller.ActionFoward;
import dao.BoardDaoImple;
import dao.BoardDaoInter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vo.BoardVO;
//파라미터로부터 입력값을 받아서 Dao의 addBoard로 넘겨주면 끝
public class BoardAddAction implements Action{
@Override
public ActionFoward execute(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
	//1.Dao를 생성한 후에 void addBoard(BoardVO vo)를 호출해본다.
	BoardDaoInter dao = BoardDaoImple.getdao();
	//2. BoardVo 생성해서 값을 받아서 넘겨주자
	BoardVO vo = new BoardVO();
//	vo.setSubject(request.getParameter("subject"));
//	vo.setContents(request.getParameter("contents"));
//	vo.setWriter(request.getParameter("writer"));
//	vo.setReip(request.getParameter("reip"));
	try {
		BeanUtils.populate(vo, request.getParameterMap());
		System.out.println("subject : "+ vo.getSubject());
	} catch (IllegalAccessException | InvocationTargetException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();	
	}
	
	dao.addBoard(vo);
	
	return new ActionFoward("main.ict?cmd=boardList",true);
}
}
