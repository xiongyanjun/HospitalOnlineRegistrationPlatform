<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div id="tabledata">
	<div class="project-list">

		<table class="table-bordered table table-hover table-condensed text-center">
			<tbody>
				<tr class="unread">
					<td class="project-title">
						<div>新闻编号</div>
					</td>
					<td class="project-title">
						<div>新闻标题</div>
					</td>
					<td class="project-title">
						<div>发布人</div>
					</td>
					<td class="project-title">
						<div>发布时间</div>
					</td>
					<td class="project-title">
						<div>是否删除</div>
					</td>
				</tr>
				<c:forEach items="${tabledata}" var="list">
					<tr>
							<td class="project-title">
								<div>${list['newsId'] }</div>
							</td>
							<td class="project-title">
								<div>${list['title'] }</div>
							</td>
							<td class="project-title">
								<div style="margin-left: -10px;">${list['publisher'] }</div>
							</td>
							<td class="project-title">
								<div style="margin-left: -10px;">${list['time'] }</div>
							</td>
							<td class="project-title">
								<button type="button" class="btn btn-danger btn-sm"
										onclick="deleteNews(${list['newsId'] })">删除此新闻</button>
							</td>
					</tr>
				</c:forEach>

			</tbody>
		</table>
		<nav aria-label="Page navigation">
			<ul class="pagination">
				<li>
					<a onclick="newsInfo(${prePage})" aria-label="Previous">
						<span aria-hidden="true">&laquo;</span>
					</a>
				</li>

				<c:forEach var="i" begin="1" end="${pages}" step="1">
					<li><a onclick="newsInfo(${i})">${i}</a></li>
				</c:forEach>

				<li>
					<a onclick="newsInfo(${nextPage})" aria-label="Next">
						<span aria-hidden="true">&raquo;</span>
					</a>
				</li>
			</ul>
		</nav>
	</div>
</div>
