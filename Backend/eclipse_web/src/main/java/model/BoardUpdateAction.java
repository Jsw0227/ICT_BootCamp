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

//cmd=bup
//bup=model.BoardUpdateAction
public class BoardUpdateAction implements Action{

	@Override
	public ActionFoward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		
		BoardVO vo = new BoardVO();
		try {
			BeanUtils.populate(vo, request.getParameterMap());
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		BoardDaoInter dao = BoardDaoImple.getdao();
		dao.upBoard(vo);
		return new ActionFoward("myict.ict?cmd=bdtail&num="+vo.getNum(),true);
	}

}
