<%@page import="tw.com.hfu.entity.Member"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8" />
<title>會員列表</title>
<link rel="stylesheet" href="css/memberadmin.css" />
<!-- Font Awesome Cdn Link -->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css" />
</head>
<body>


	<div class="container">
		<!-- left side nav bar -->
		<%@include file="successmember.jsp"%>

		<section class="main">
			<section class="attendance">
				<div class="row users">
					<form action="<%=request.getContextPath()%>/MemberWebServlet"
						method="POST">
						<div class="card" style="display: flex; width: 100%;">
							<a href="<%=request.getContextPath()%>/MemberWebAddServlet">新增</a>

						</div>
						<div class="card" style="display: flex; width: 100%;">
							<a href="<%=request.getContextPath()%>/MemberWebListServlet">刷新</a>

						</div>
						<div class="card" style="display: flex; width: 100%;">
							<!-- style="width: 500" -->
							<div class="ccc">
								<input class="form-control me-2" type="search"
									placeholder="            會員搜尋" aria-label="會員搜尋" name="sName">
								<button class="btn btn-danger" type="submit">查詢</button>
								<input type="hidden" name="status" value="querymember">
							</div>
							<!-- 	<a href="javascript:;" onClick="javascript:history.back(-1);">返回上一页</a> -->
						</div>
					</form>
				</div>
				<h1>會員列表</h1>
				<table class="table"><!--style="table-layout:fixed;"  -->
					<!-- 	<div>
							<a href="member/memberadd">新增會員</a>
						</div> -->

					<thead>
						<tr>
							<th scope="col">#</th>
							<th scope="col">頭像</th>
							<th scope="col">名字</th>
							<th scope="col">帳號</th>
							<th scope="col">密碼</th>
							<th scope="col">信箱</th>
							<th scope="col">電話</th>
							<th scope="col">更新</th>
							<th scope="col">刪除</th>

						</tr>
					</thead>

					<tbody>

						<%
						ArrayList<Member> member = (ArrayList) request.getAttribute("memberList");
						for (int i = 0; i < member.size(); i++) {
						%>
						<tr>
							<th scope="row"><%=member.get(i).getId()%></th>
							<td><img src="<%=member.get(i).getPicture()%>"></td>
							<!-- style="width: 100" -->
							<td><%=member.get(i).getName()%></td>
							<td><%=member.get(i).getAccount()%></td>
							<td><%=member.get(i).getPassword()%></td>
							<td><%=member.get(i).getEmail()%></td>
							<td><%=member.get(i).getPhone()%></td>

							<td><a
								href="<%=request.getContextPath()%>/member/memberupdate.jsp?mId=
								<%=member.get(i).getId()%>&mPicture=<%=member.get(i).getPicture()%>&mName=
								<%=member.get(i).getName()%>&mAccount=<%=member.get(i).getAccount()%>&mPassword=
								<%=member.get(i).getPassword()%>&mEmail=<%=member.get(i).getEmail()%>&mPhone=
								<%=member.get(i).getPhone()%>">更新
								</a>
							</td>
							<td>
								<a
								href="<%=request.getContextPath()%>/MemberWebServlet?mId=
					<%=member.get(i).getId()%>&status=delete">刪除
								</a>
							</td>
						</tr>
						<%
						}
						%>
					</tbody>

				</table>

			</section>
		</section>

	</div>
<%@include file="footer.jsp"%>


</body>




</html>
