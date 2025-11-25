package model;

import java.io.IOException;

import controller.ActionFoward;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
// 모델이 수행하고 나서 돌려주는 객체
public interface Action{
	ActionFoward execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
}
