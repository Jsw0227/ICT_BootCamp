<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@include file="../header.jsp"%>
<!--  리스트 UI 시작  -->
<div class="container">
	<div class="row">

		<div class="card" style="width: 80%;">
			<div class="card-header">FreeBoard Detail</div>
			<div class="card-body">
				<h5 class="card-title">제목 : ${v.subject}</h5>
				<h6 class="card-subtitle mb-2 text-muted">작성자 : ${v.writer}</h6>
				<p class="card-text">내용 : ${v.content}</p>
				<p class="card-text">아이피 : ${v.reip}</p>
				<p class="card-text">날짜 : ${v.fdate}</p>
			</div>
		</div>

		<div class="container text-center" role="group">
			<button class="btn btn-primary"
				onclick="location='fboardDelete?num=${v.num}'">삭제</button>
			&nbsp;
			<button class="btn btn-primary"
				onclick="location='fboardUpForm?num=${v.num}'">수정</button>
			&nbsp;
			<button class="btn btn-danger" type="button"
				onclick="location='fboardList'">리스트</button>
		</div>

	</div>
</div>
<!--  리스트 UI 끝 -->
<%@include file="../footer.jsp"%>