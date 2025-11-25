	package model;

import java.io.IOException;

import controller.ActionFoward;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class MyWorkAction implements Action{

	@Override
	public ActionFoward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setAttribute("jobName", "오늘은 월요일 입니다. MyBatis해요!");
		return new ActionFoward("job",false);
	}

}
