<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
p{
margin: 8px 0;
font-weight: bold;
color:#333;
padding: 5px;
border-bottom: 1px solid #ddd;
}

</style>
</head>
<body>
<div style="width: 400px; margin: 20px auto;">
<p>num : ${vo.num}</p>
<p>subject : ${vo.subject}</p>
<p>writer : ${vo.writer}</p>
<p>contents : ${vo.contents}</p>
<button onclick="location=myict.ict?cmd=boardList">리스트</button>

<form action="myict.ict" method="post" onsubmit="return boardDel()">
<input type="hidden" name="cmd" value="bdel">
<input type="hidden" name="num" value="${vo.num}">
<input type="submit" value="삭제">
</form>

<form action="myict.ict" onsubmit="return boardUp()" method="post">
<input type="hidden" name="cmd" value="bupform">
<input type="hidden" name="num" value="${vo.num}">
<input type="submit" value="수정">

</form>

</div>
<script>
function boardDel() {
	return confirm("정말로 삭제할까요?")	
}

function boardUp(){
	return confirm("정말로 수정할까요?")
}


</script>
</body>
</html>