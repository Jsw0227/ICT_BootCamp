<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@include file="../header.jsp"%>

<div class="container">

	<div class="row">
		<table class="table table-success table-striped">
			<thead>
				<tr>
					<th>번호</th>
					<th>제목</th>
					<th>작성자</th>
					<th>조회수</th>
					<th>날짜</th>
				</tr>
			</thead>
			<tbody>
			<c:forEach var="e" items="${flist }">
				<tr>
					<td>${e.num}</td>
					<td><a href="fboardHit?num=${e.num}" class="link=secondary">${e.subject }</a></td>
					<td>${e.writer}</td>
					<td>${e.hit}</td>
					<td>${e.fdate}</td>
				</tr>
			
			
			</c:forEach>
			</tbody>
			<tfoot>
				<tr>
					<th colspan="5">
						<div class="d-flex justify-content-center mt-3">
							<button type="button" class="btn btn-primary"
								onclick="location='fboardForm'">Write</button>
						</div>

					</th>
				</tr>
			</tfoot>
		</table>

	</div>

</div>


<%@include file="../footer.jsp"%>