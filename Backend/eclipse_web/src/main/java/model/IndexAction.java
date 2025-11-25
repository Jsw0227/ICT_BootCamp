package model;

import controller.ActionFoward;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class IndexAction implements Action{

	@Override
	public ActionFoward execute(HttpServletRequest request, HttpServletResponse response) {
		//Controller에 의해서 View로 forward로 보낼 값 설정!
		request.setAttribute("msg", "안녕하세요!");
		//모델이 수행 후 Controller 에게 보고
		//어떤 ViewName, 이동방식( forward,redirect)
		return new ActionFoward("index",false);
	}

	
}
