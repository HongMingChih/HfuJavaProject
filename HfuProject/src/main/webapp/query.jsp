<%@page import="tw.com.hfu.entity.Restaurant"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="header.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>好貪吃</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
	rel="stylesheet">
<link rel="stylesheet" href="css/style.css" />
<script src="https://use.fontawesome.com/releases/v6.1.0/js/all.js"></script>

</head>
<body>


	<div class="container">

		<div class="row">

			
		</div>


		<div class="col-8">

			<h3>好貪吃</h3>
			<div class="col-2">
				<form action="<%=request.getContextPath()%>/restaurantServlet"
					method="POST">
					<div class="col-2">
						<button type="submit" class="btn btn-danger">搜尋餐廳</button>
						<input type="hidden" name="status" value="queryall">
					</div>


				</form>
			</div>
			<div class="col-2"></div>
			<div class="row res">
				<table class="table">
					<thead>
						<tr>
							<th scope="col">#</th>
							<th scope="col">餐廳</th>
							<th scope="col">說明</th>
							<th scope="col">圖片</th>
							<th scope="col">類型</th>
							<th scope="col">地址</th>
							<th scope="col">電話</th>

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
							<td><%=restaurant.get(i).getDescription()%></td>
							<td><img style="width: 300"
								src="<%=restaurant.get(i).getPicture()%>"></td>
							<td><%=restaurant.get(i).getCategory()%></td>
							<td><%=restaurant.get(i).getAddress()%></td>
							<td><%=restaurant.get(i).getPhone()%></td>

							<%
							}
							%>
						
					</tbody>

				</table>
			</div>
		</div>
	</div>

	<div id="layoutAuthentication_footer">
		<footer class="py-4 bg-light mt-auto">
			<div class="container-fluid px-4">
				<div class="d-flex align-items-center justify-content-between small"">
					<div class="text-muted">華梵 &copy; webhfu 2022</div>
					<div>
						<a href="#">Privacy Policy</a> &middot; <a href="#">Terms
							&amp; Conditions</a>
					</div>
				</div>
			</div>
		</footer>
	</div>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>