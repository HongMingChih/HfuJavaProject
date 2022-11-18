<%@page import="tw.com.hfu.entity.Restaurant"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%-- <%@ include file="headerstore.jsp"%> --%>
<!doctype html>
<html lang="en">
<head>
<meta charset="UTF-8" />
<title>餐廳 管理</title>
<meta charset="utf-8">
<link rel="stylesheet" href="css/storequery.css" />
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css" />

</head>
<body>
	<div class="container">
		<%@include file="successadmin.jsp"%>

		<section class="main">


			<section class="attendance">
				<div class="row users">
				<div>
					<form action="<%=request.getContextPath()%>/restaurantServlet"
						method="POST">

						<div class="card" style="display: flex; width: 100%;">
							<a href="<%=request.getContextPath()%>/memberAdminAddServlet">新增</a>

						</div>
						<div class="card" style="display: flex; width: 100%;">
							<a href="<%=request.getContextPath()%>/MemberAdminActionServlet">刷新</a>
						</div>
						<div class="card" style="display: flex; width: 100%;">
							<div class="ccc">
								<input class="form-control me-2" type="search"
									placeholder="            店家搜尋" aria-label="店家搜尋" name="sName">
								<button class="btn btn-danger" type="submit">查詢</button>
								<input type="hidden" name="status" value="queryadmin">
							</div>

							<!-- 	<a href="javascript:;" onClick="javascript:history.back(-1);">返回上一页</a> -->
						</div>

					</form>
					
					<form action="<%=request.getContextPath()%>/RestaurantMenuServlet"
						method="POST" >	
						<div class="card" style="display: flex; width: 100%;  ">
						<h3 style="font-family:inherit; padding-left: 0%;padding-right: 5em;">菜系查詢</h3>
						<select style="font-size:20px;  " name="item">
							<option value="#">請選則類別</option>
							<option value="中式料理">中式料理</option>
							<option value="日式料理">日式料理</option>
							<option value="法式料理">法式料理</option>
							<option value="美式料理">美式料理</option>
							<option value="創新料理">創新料理</option>
						</select> <button style="width: 10em;" class="btn btn-danger" type="submit">確認查詢</button>
						</div>
						
					</form>
					</div>
				</div>
				<h1>餐廳列表</h1>
				<table class="table">


					<thead>
						<tr>
							<th scope="col">#</th>
							<th scope="col">店家</th>
							<th scope="col">說明</th>
							<th scope="col">圖片</th>
							<th scope="col">類型</th>
							<th scope="col">地址</th>
							<th scope="col">電話</th>
							<th scope="col">更新</th>
							<th scope="col">刪除</th>

						</tr>
					</thead>

					<tbody>
						<%
						ArrayList<Restaurant> restaurant = (ArrayList) request.getAttribute("restaurant");
						for (int i = 0; i < restaurant.size(); i++) {
						%>
						<tr>
							<th scope="row"><%=restaurant.get(i).getId()%></th>
							<td><%=restaurant.get(i).getName()%></td>
							<td id="Desc" title="<%=restaurant.get(i).getDescription()%>"><%=restaurant.get(i).getDescription()%></td>
							<td><img src="<%=restaurant.get(i).getPicture()%>"></td>
							<td><%=restaurant.get(i).getCategory()%></td>
							<td><%=restaurant.get(i).getAddress()%></td>
							<td><%=restaurant.get(i).getPhone()%></td>
							<td><a
								href="<%=request.getContextPath()%>/admin/storeupdate.jsp?rId=
					<%=restaurant.get(i).getId()%>&rName=<%=restaurant.get(i).getName()%>&rDescription=
					<%=restaurant.get(i).getDescription()%>&rPicture=<%=restaurant.get(i).getPicture()%>&rCategory=
					<%=restaurant.get(i).getCategory()%>&rAddress=<%=restaurant.get(i).getAddress()%>&rPhone=
					<%=restaurant.get(i).getPhone()%>">更新
							</a></td>
							<td><a
								href="<%=request.getContextPath()%>/restaurantServlet?rId=
					<%=restaurant.get(i).getId()%>&status=delete">刪除</a></td>
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












