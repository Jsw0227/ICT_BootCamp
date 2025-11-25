<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="main.ict" method="post">
		<input type="hidden" name="cmd" value="memoAdd"> 
		작성자 : <input type="text" name="writer"> 
			memo : <input type="text" name="mcont"> 
			<input type="submit" value="memo">
	</form>
</body>
</html>