<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
#customers {
	font-family: Arial, Helvetica, sans-serif;
	border-collapse: collapse;
	width: 100%;
}

#customers td, #customers th {
	border: 1px solid #ddd;
	padding: 8px;
}

#customers tr:nth-child(even) {
	background-color: #f2f2f2;
}

#customers tr:hover {
	background-color: #ddd;
}

#customers th {
	padding-top: 12px;
	padding-bottom: 12px;
	background-color: orange;
	color: white;
}
</style>
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
					<td><a href="myict.ict?cmd=bdtail&num=${e.num}">${e.subject}</a>
					</td>
					<td>${e.writer}</td>
					<td>${e.bdate}</td>
				</tr>
			</c:forEach>
			<tr>
				<th colspan="4">
					<button onclick="location='myict.ict?cmd=boardForm'">입력</button>
				</th>
			</tr>


		</table>
	</div>
</body>
</html>