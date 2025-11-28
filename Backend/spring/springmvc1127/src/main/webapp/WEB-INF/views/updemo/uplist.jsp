<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@include file="../header.jsp"%>
<c:set var="rPath" value="${pageContext.request.contextPath }/resources" />

<article>
	<header style="color: white">Fileupload Demo</header>
	<ul class="list-unstyled text-center mt-5">
		<li class="border-top my-3 fs-4 fw-bold">FileList</li>
	</ul>

	<div class="container">
		<table class="table table-bordered" id="upboardTable">

			<thead>

				<tr>
					<th>번호</th>
					<th>이미지</th>
					<th>제목</th>
					<th>작성자</th>
					<th>조회수</th>
					<th>작성날짜</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="e" items="${list }">
					<tr>
						<td>${e.num }</td>
						<td>
						<a href="boardDetail?num=${e.num }"> 
						<img src="${rPath }/imgfile/${e.imgn}"
								style="width: 80px; border: dotted 1px; cursor: pointer;">
						</a>
						</td>
						<td>${e.title }<span style="color: red">[${e.cnt }]</span></td>
						<td>${e.writer }</td>
						<td>${e.hit }</td>
						<td>${e.bdate}</td>
					</tr>
				</c:forEach>
			</tbody>
			<tfoot>
				<tr>
					<td colspan="6" style="text-align: right;">
						<button type="button" class="btn btn-outline-secondary"
							onclick="location='upform'">글작성</button>
					</td>
				</tr>
			</tfoot>
		</table>
	</div>
</article>

<%@include file="../footer.jsp"%>