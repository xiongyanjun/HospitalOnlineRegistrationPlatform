<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div id="tabledata">
	<div class="project-list">
		<button type="button" class="btn btn-sm btn-primary"
			onclick="yuyueIng()">预约中</button>
		<button type="button" class="btn btn-sm btn-primary"
			onclick="yuyueSuc()">预约成功</button>
		<button type="button" class="btn btn-sm btn-primary"
			onclick="yuyueFai()">预约失败</button>
		<table class="table-bordered table table-hover table-condensed text-center">
			<tbody>
				<tr class="unread">
					<td class="project-title">
						<div>预约号</div>
					</td>
					<td class="project-title">
						<div>预约医生</div>
					</td>
					<td class="project-title">
						<div>预约日期</div>
					</td>
					<td class="project-title">
						<div>预约时间</div>
					</td>
					<td class="project-title">
						<div>预约结果</div>
					</td>
					<td class="project-title">
						<div>取消预约</div>
					</td>
				</tr>
				<c:forEach items="${tabledata}" var="list">
					<tr>
						<td class="project-title">
							<div>${list['yyh'] }</div>
						</td>
						<td class="project-title">
							<div>${list['dname'] }</div>
						</td>
						<td class="project-title">
							<div>${list['ydate'] }</div>
						</td>
						<td class="project-title">
							<div>${list['ytime'] }</div>
						</td>
						<td class="project-title">
							<div>${list['isok'] }</div>
						</td>
						<td class="project-title">
							<button class="btn btn-danger" onclick="deleteYy('${list['yyh'] }')" >取消预约</button>
						</td>
					</tr>
				</c:forEach>

			</tbody>
		</table>
		<nav aria-label="Page navigation">
			<ul class="pagination">
				<li>
					<a onclick="yuyueguanli(${prePage})" aria-label="Previous">
						<span aria-hidden="true">&laquo;</span>
					</a>
				</li>

				<c:forEach var="i" begin="1" end="${pages}" step="1">
					<li><a onclick="yuyueguanli(${i})">${i}</a></li>
				</c:forEach>

				<li>
					<a onclick="yuyueguanli(${nextPage})" aria-label="Next">
						<span aria-hidden="true">&raquo;</span>
					</a>
				</li>
			</ul>
		</nav>
	</div>
</div>
