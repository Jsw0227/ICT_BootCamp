<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div style="width: 450px; margin: auto">
		<table id="customers">
			<tr>
				<th>번호</th>
				<th>제목</th>
				<th>작성자</th>
				<th>작성날짜</th>
			</tr>
			<c:forEach var="e" items="${blist}">
				<tr>
					<td>${e.num}</td>
					<td><a href="myict.ict?cmd=bdtail&num=${e.num}">${e.subject}</a> </td>
					<td>${e.writer}</td>
					<td>${e.bdate}</td>
				</tr>
				<tr>
				<th colspan="4">
				<button onclick="location='myict.ict?cmd=boardForm'">입력</button>
				</th>
				</tr>
				
				
			</c:forEach>


		</table>
	</div>
</body>
</html>