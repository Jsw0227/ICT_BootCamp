<%@page import="ex3.DaoInter"%>
<%@page import="org.springframework.context.support.GenericXmlApplicationContext"%>
<%@page import="org.springframework.context.ApplicationContext"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
<%

ApplicationContext ctx = new GenericXmlApplicationContext("ex3/ex1_aop.xml");

DaoInter dao = ctx.getBean("dao",DaoInter.class);

String msg = dao.firstStatementTest(2);
System.out.println("msg:"+msg);
%>
</body>
</html>