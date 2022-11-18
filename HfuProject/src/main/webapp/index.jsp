<%@page import="tw.com.hfu.entity.Restaurant"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!doctype html>
<html lang="en">

<head>
<title>好貪吃首頁</title>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<link rel="stylesheet" href="css/index.css">
</head>

<body>
	<!--第一層 導覽列-->
	<nav class="navbar navbar-expand-lg navbar-light bg-light">
		<div class="container-fluid">
			<img style="width: 100px;" src="images/eat.png">
			<a class="navbar-brand" href="#">好貪吃</a>
			<button class="navbar-toggler" type="button"
				data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
				aria-controls="navbarSupportedContent" aria-expanded="false"
				aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarSupportedContent">
				<ul class="navbar-nav me-auto mb-2 mb-lg-0">
					<li class="nav-item"><a class="nav-link active"
						aria-current="page"
						href="<%=request.getContextPath()%>/indexServlet">首頁</a></li>

					<li class="nav-item dropdown"><a
						class="nav-link dropdown-toggle" href="#" id="navbarDropdown"
						role="button" data-toggle="dropdown" aria-haspopup="true"
						aria-expanded="false"> 登入 </a>
						<div class="dropdown-menu" aria-labelledby="navbarDropdown">

							<a class="dropdown-item" href="admin/loginadmin.jsp">登入</a>
							<div class="dropdown-divider"></div>
						</div></li>
				</ul>

			</div>
		</div>
	</nav>


	<!--第二層 三欄位內容-->

	<div class="container-fluid">

		<div class="row content">

			<!--左邊欄位-->
			<!--class裡面裝名字，以便給css使用，下面的sidenav就是-->
			<div class="col sm-2 sidenav">
				<br> <br>
				<form class="d-flex" role="search"
					action="<%=request.getContextPath()%>/restaurantServlet"
					method="POST">
					<input class="form-control me-2" type="search" placeholder="產品搜尋"
						aria-label="產品搜尋" name="sName">
					<button class="btn btn-outline-success" type="submit">Search</button>
					<input type="hidden" name="status" value="query">
					
				</form>
				<br>	<br>	<br>	<br>
				<form class="d-flex" role="search"
					action="<%=request.getContextPath()%>/restaurantServlet"
					method="POST">
					<div>
						<button type="submit" class="btn btn-danger">快速查詢全部餐廳</button>
						<input type="hidden" name="status" value="queryall">
					</div>
				</form>

				​
				
				<!-- 	<a href="javascript:;" onClick="javascript:history.back(-1);">返回上一页</a> -->
			</div>






			<!--中間欄位-->
			<div class="col-sm-8 main-content">

				<h1>歡迎光臨  "好貪吃"</h1>
				<div class="row res">

					<%
					ArrayList<Restaurant> restaurant = (ArrayList) request.getAttribute("restaurant");
					for (int i = 0; i < restaurant.size(); i++) {
					%>
					<div class="card-body">
						<div class="card">
							<img src="<%=restaurant.get(i).getPicture()%>" class="card-img-top"
								alt="...">

							<div class="card-body2">
								<div class="card title" id="nys"
									title="<%=restaurant.get(i).getName()%>">
									<h5><%=restaurant.get(i).getName()%></h5>
								</div>
								<div class="card-text" id="nys1"
									title="<%=restaurant.get(i).getDescription()%>"><%=restaurant.get(i).getDescription()%></div>

							</div>

						</div>
						<br> <br> <br> <br>

					</div>

					<%
					}
					%>
				</div>

			</div>
			<!--右邊欄位-->
			<div class="col sm-2 sidenav">
				<div class="mt-4 info">

					<button type="button" class="btn btn-outline-secondary"
						data-toggle="modal" data-target="#exampleModalLong">公司介紹
					</button>

					<div class="modal fade" id="exampleModalLong" tabindex="-1"
						role="dialog" aria-labelledby="exampleModalLongTitle"
						aria-hidden="true">
						<div class="modal-dialog" role="document">
							<div class="modal-content">
								<div class="modal-header">
									<h5 class="modal-title" id="exampleModalLongTitle">公司介紹</h5>
									<button type="button" class="close" data-dismiss="modal"
										aria-label="Close">
										<span aria-hidden="true">&times;</span>
									</button>
								</div>
								<div class="modal-body">介紹介紹介紹介紹</div>
								<div class="modal-footer">
									<button type="button" class="btn btn-secondary"
										data-dismiss="modal">Close</button>

								</div>
							</div>
						</div>
					</div>
				</div>


				<div class="mt-4 info">

					<button type="button" class="btn btn-outline-secondary"
						data-toggle="modal" data-target="#exampleModalLong">
						管理者須知</button>

					<div class="modal fade" id="exampleModalLong" tabindex="-1"
						role="dialog" aria-labelledby="exampleModalLongTitle"
						aria-hidden="true">
						<div class="modal-dialog" role="document">
							<div class="modal-content">
								<div class="modal-header">
									<h5 class="modal-title" id="exampleModalLongTitle">瀏覽須知</h5>
									<button type="button" class="close" data-dismiss="modal"
										aria-label="Close">
										<span aria-hidden="true">&times;</span>
									</button>
								</div>
								<div class="modal-body">須知須知須知</div>
								<div class="modal-footer">
									<button type="button" class="btn btn-secondary"
										data-dismiss="modal">Close</button>

								</div>
							</div>
						</div>
					</div>
				</div>


				<div class="mt-4 info">

					<button type="button" class="btn btn-outline-secondary"
						data-toggle="modal" data-target="#exampleModalLong">常見問題
					</button>


					<div class="modal fade" id="exampleModalLong" tabindex="-1"
						role="dialog" aria-labelledby="exampleModalLongTitle"
						aria-hidden="true">
						<div class="modal-dialog" role="document">
							<div class="modal-content">
								<div class="modal-header">
									<h5 class="modal-title" id="exampleModalLongTitle">常見問題</h5>
									<button type="button" class="close" data-dismiss="modal"
										aria-label="Close">
										<span aria-hidden="true">&times;</span>
									</button>
								</div>
								<div class="modal-body">QAQAQAQA</div>
								<div class="modal-footer">
									<button type="button" class="btn btn-secondary"
										data-dismiss="modal">Close</button>

								</div>
							</div>
						</div>
					</div>
				</div>

				<div class="mt-4 info">

					<button type="button" class="btn btn-outline-secondary"
						data-toggle="modal" data-target="#exampleModalLong">聯絡資訊
					</button>


					<div class="modal fade" id="exampleModalLong" tabindex="-1"
						role="dialog" aria-labelledby="exampleModalLongTitle"
						aria-hidden="true">
						<div class="modal-dialog" role="document">
							<div class="modal-content">
								<div class="modal-header">
									<h5 class="modal-title" id="exampleModalLongTitle">聯絡資訊</h5>
									<button type="button" class="close" data-dismiss="modal"
										aria-label="Close">
										<span aria-hidden="true">&times;</span>
									</button>
								</div>
								<div class="modal-body">電話地址窗口</div>
								<div class="modal-footer">
									<button type="button" class="btn btn-secondary"
										data-dismiss="modal">Close</button>

								</div>
							</div>
						</div>
					</div>
				</div>


			</div>



		</div>

	</div>
	<%@include file="footer.jsp"%>

	<!-- Optional JavaScript -->
	<!-- jQuery first, then Popper.js, then Bootstrap JS -->
	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</body>

</html>