<%@page import="ex3.DaoInter"%>
<%@page import="org.springframework.context.support.GenericXmlApplicationContext"%>
<%@page import="org.springframework.context.ApplicationContext"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>test.jsp</title>
</head>
<body>
<%
ApplicationContext ctx = 
new GenericXmlApplicationContext("ex3/ex1_aop.xml");

DaoInter dao = ctx.getBean("dao", DaoInter.class);
// String msg = dao.firstStatementTest(2);
// System.out.println("msg:"+msg);
String res = dao.second();
//dao.myBefore();
%>

<%-- 소요시간4030  msg:Check 완료***** --%>
<!-- 소요시간2278msg:Check 완료***** -->
</body>
</html>









