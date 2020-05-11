<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="mail-box">
	<table class="table-bordered table table-hover table-condensed text-center table-mail">
		<tbody>
			<tr class="unread">
				<td>科室</td>
				<td>姓名</td>
				<td>性别</td>
				<td>职位</td>
				<td>简介</td>
			</tr>
			<c:forEach items="${tabledata}" var="list">
			<tr class="read">
				<td>${list['department'] }</td>
				<td>${list['tname'] }</td>
				<td>${list['sex'] }</td>
				<td>${list['position'] }</td>
				<td>${list['summary'] }</td>
			</tr>
			</c:forEach>
		</tbody>
	</table>
	<nav aria-label="Page navigation">
		<ul class="pagination">
			<li>
				<a onclick="cshowPage(${prePage})" aria-label="Previous">
					<span aria-hidden="true">&laquo;</span>
				</a>
			</li>

			<c:forEach var="i" begin="1" end="${pages}" step="1">
				<li><a onclick="cshowPage(${i})">${i}</a></li>
			</c:forEach>

			<li>
				<a onclick="cshowPage(${nextPage})" aria-label="Next">
					<span aria-hidden="true">&raquo;</span>
				</a>
			</li>
		</ul>
	</nav>
</div>
