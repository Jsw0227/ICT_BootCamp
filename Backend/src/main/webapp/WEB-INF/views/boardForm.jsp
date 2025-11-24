<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div style="width: 400px; margin: 20px auto">
		<form action="main.ict" method="post">
			<input type ="hidden" name="cmd" value="boardAdd">
			<input type="hidden" name="reip" value="<%=request.getRemoteAddr() %>">
			<p>subject : <input type="text" name="subject"> </p>
			<p>writer : <input type="text" name="writer"> </p>
			<p>content : <textarea rows="10" cols="20" name="contents"></textarea> </p>
			<input type="submit" value="send">
		</form>
	</div>
</body>
</html>