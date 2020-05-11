<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div id="tabledata">
	<div class="project-list">
		<table class="table-bordered table table-hover table-condensed text-center">
			<tbody>
				<tr class="unread">
					<td>姓名</td>
					<td>性别</td>
					<td>简介</td>
					<td>科室</td>
					<td>职位</td>
					<td>预约</td>
				</tr>
				<c:forEach items="${tabledata}" var="list">
					<tr>
						<td class="project-title">
							<div>${list['tname'] }</div>
						</td>
						<td class="project-title">
							<div>${list['sex'] }</div>
						</td>
						<td class="project-title">
							<div>${list['summary'] }</div>
						</td>
						<td class="project-title">
							<div>${list['department'] }</div>
						</td>
						<td class="project-title">
							<div>${list['position'] }</div>
						</td>
						<td >
							<button class="btn-primary btn-block"  onclick="yuyue(${list['jnumber'] },${list['uid'] })">预约</button>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<nav aria-label="Page navigation">
			<ul class="pagination">
				<li>
					<a onclick="yuyueguahao(${prePage})" aria-label="Previous">
						<span aria-hidden="true">&laquo;</span>
					</a>
				</li>

				<c:forEach var="i" begin="1" end="${pages}" step="1">
					<li><a onclick="yuyueguahao(${i})">${i}</a></li>
				</c:forEach>

				<li>
					<a onclick="yuyueguahao(${nextPage})" aria-label="Next">
						<span aria-hidden="true">&raquo;</span>
					</a>
				</li>
			</ul>
		</nav>
	</div>
</div>
