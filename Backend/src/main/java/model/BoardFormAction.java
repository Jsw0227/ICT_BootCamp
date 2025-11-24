package model;

import java.io.IOException;

import controller.ActionFoward;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class BoardFormAction implements Action{
@Override
public ActionFoward execute(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
	
	return new ActionFoward("boardForm",false);
}
}
