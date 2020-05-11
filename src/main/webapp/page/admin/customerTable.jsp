<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="mail-box">
	<table class="table-bordered table table-hover table-condensed text-center table-mail">
		<tbody>
			<tr class="unread">
				<td>姓名</td>
				<td>登录名</td>
				<td>性别</td>
				<td>电话</td>
				<td>电子邮箱</td>
				<td>年龄</td>
				<td>修改</td>
                <td>删除</td>
			</tr>
			<c:forEach items="${tabledata}" var="list">
			<tr class="read">
				<td>${list['tname'] }</td>
				<td>${list['name'] }</td>
				<td>${list['sex'] }</td>
				<td>${list['phone'] }</td>
				<td>${list['email'] }</td>
				<td>${list['age'] }</td>
				<td><button type="button" class="btn btn-primary" data-toggle="modal" data-target="#userModal" onclick="echoUserModel(' ${list['id'] } ')">修改</button></td>
                <td><button type="button" class="btn btn-danger" onclick="deleteUser(' ${list['id'] } ')">删除</button></td>
			</tr>
			</c:forEach>
		</tbody>
	</table>
	<nav aria-label="Page navigation">
		<ul class="pagination">
			<li>
				<a onclick="customerTable(${prePage})" aria-label="Previous">
					<span aria-hidden="true">&laquo;</span>
				</a>
			</li>

			<c:forEach var="i" begin="1" end="${pages}" step="1">
				<li><a onclick="customerTable(${i})">${i}</a></li>
			</c:forEach>

			<li>
				<a onclick="customerTable(${nextPage})" aria-label="Next">
					<span aria-hidden="true">&raquo;</span>
				</a>
			</li>
		</ul>
	</nav>
</div>
