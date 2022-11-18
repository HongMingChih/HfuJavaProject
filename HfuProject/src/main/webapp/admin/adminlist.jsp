<%@page import="tw.com.hfu.entity.MemberAdmin"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8" />
<title>管理者列表</title>
<link rel="stylesheet" href="css/adminlist.css" />
<!-- Font Awesome Cdn Link -->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css" />
</head>
<body>


	<div class="container">
	<%@include file="successadmin.jsp"%>


		<section class="main">

			<div class="users">
				<div class="card">
					<img
						src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR8HUzm-jtwLx8QB6bjIFysPjCu4jQVLiold5aaLL_SeOOvsuvaClfpQ60_2k2QMhTUzAU&usqp=CAU">
					<h4>組長</h4>

					<div class="per">
						<table>
							<tr>
								<td><span>85%</span></td>
								<td><span>87%</span></td>
							</tr>
							<tr>
								<td>Month</td>
								<td>Year</td>
							</tr>
						</table>
					</div>
					<button>Profile</button>
				</div>
				<div class="card">
					<img
						src="https://www.yisou.art/uploads/fc98326c32809bbcb61c1f925e1590e8.jpg">
					<h4>組員A</h4>

					<div class="per">
						<table>
							<tr>
								<td><span>82%</span></td>
								<td><span>85%</span></td>
							</tr>
							<tr>
								<td>Month</td>
								<td>Year</td>
							</tr>
						</table>
					</div>
					<button>Profile</button>
				</div>
				<div class="card">
					<img
						src="https://www.yisou.art/uploads/bd0ea92c8f9ef4016b18ed158a8bbb6c.jpg">
					<h4>組員B</h4>

					<div class="per">
						<table>
							<tr>
								<td><span>94%</span></td>
								<td><span>92%</span></td>
							</tr>
							<tr>
								<td>Month</td>
								<td>Year</td>
							</tr>
						</table>
					</div>
					<button>Profile</button>
				</div>
				<div class="card">
					<img
						src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSTJZ-9OYuc6WaQgiubgNxuuQizQQZyrdTZAQ&usqp=CAU">
					<h4>組員C</h4>

					<div class="per">
						<table>
							<tr>
								<td><span>85%</span></td>
								<td><span>82%</span></td>
							</tr>
							<tr>
								<td>Month</td>
								<td>Year</td>
							</tr>
						</table>
					</div>
					<button>Profile</button>
				</div>
			</div>

			<section class="attendance">
				<div class="row users">
					<form action="<%=request.getContextPath()%>/MemberAdminListServlet"
						method="POST">
						
						<div class="card" style="display: flex; width: 100%;">
							<a href="<%=request.getContextPath()%>/MemberAdminListServlet">全部顯示</a>

						</div>
						<div class="card" style="display: flex; width: 100%;">
							<!-- style="width: 500" -->
							<div class="ccc">
								<input class="form-control me-2" type="search"
									placeholder="         管理成員搜尋" aria-label="管理成員搜尋" name="ma_Name">
								<button class="btn btn-danger" type="submit">查詢</button>
								<input type="hidden" name="status" value="queryadminlist">
							</div>
							<!-- 	<a href="javascript:;" onClick="javascript:history.back(-1);">返回上一页</a> -->
						</div>
					</form>
				</div>
				<h1>管理者列表</h1>
				<table class="table">
					<!--style="table-layout:fixed;"  -->
					<!-- 	<div>
							<a href="member/memberadd">新增會員</a>
						</div> -->

					<thead>
						<tr>
							<th scope="col">#</th>
							<th scope="col">頭像</th>
							<th scope="col">名字</th>
							<th scope="col">帳號</th>
							
							
						

						</tr>
					</thead>

					<tbody>

						<%
						ArrayList<MemberAdmin> memberadmin = (ArrayList) request.getAttribute("memberadmin");
						for (int i = 0; i < memberadmin.size(); i++) {
						%>
						<tr>
							<th scope="row"><%=memberadmin.get(i).getMa_id()%></th>
							<td><img src="<%=memberadmin.get(i).getMa_picture()%>"></td>
						
							<td><%=memberadmin.get(i).getMa_name()%></td>
							<td><%=memberadmin.get(i).getMa_email()%></td>
							
							
					
						</tr>
						<%
						}
						%>
					</tbody>

				</table>

			</section>
		</section>

	</div>
<!-- 	<footer class="container-fluid text-center">
		<p>華梵 &copy; webhfu 2022 第八組</p>
	</footer> -->
	<%@include file="footer.jsp"%>


</body>




</html>
