package model;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.BeanUtils;

import controller.ActionFoward;
import dao.MemoImple;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vo.MemoVO;

public class MemoAction implements Action{

	@Override
	public ActionFoward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		MemoVO vo = new MemoVO();
		
		try {
			BeanUtils.populate(vo, request.getParameterMap());
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		MemoImple.getdao().addMemo(vo);
		
		return new ActionFoward("memoAction",false);
	}

}
