<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!doctype html>
<html lang="en">

<head>
<title>管理者登入</title>

<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<link rel="stylesheet" href="../css/adminlogin.css">
</head>

<body>


	<!-- 第一層 -->
	<div class="container-fluid">
		<div class="row content2">
			<div class="col-sm-3 sidenav"></div>

			<div class="col-sm-6 sidenav">
				<div class="card shadow-lg border-0 rounded-lg mt-5">
					<div class="card-header">
						<H3>管理者登入頁面</H3>
					</div>
					<div class="card-body">
						<form action="../mls" method="post">
							<div class="form-floating mb-3">
								<input type="email" class="form-control" name="ma_email"
									required="required" placeholder="請輸入管理者信箱"><label
									for="exampLeInputEmail1"></label>
							</div>

							<div class="form-floating mb-3">
								<input type="password" class="form-control" name="ma_password"
									required="required" placeholder="請輸入管理者密碼"><label
									for="exampLeInputPassword1"></label>
							</div>
	
							<div
								class="d-flex align-items-center justify-content-between mt-4 mb-0">
								<a class="small" href="../admin/password.jsp">忘記密碼？</a> <a
									class="small" href="registeradmin.jsp">註冊帳號</a> <a
									href="<%=request.getContextPath()%>/indexServlet">返回首頁</a>

							</div>

							<div>
								<button type="reset" class="btn btn-primary reset" id="reset">重填</button>
								<button type="submit" class="btn btn-primary Login" id="enter">登入</button>
								<br>
								<br>

							</div>

						</form>
					</div>
				</div>
				<div class="col-sm-3 sidenav"></div>
			</div>
		</div>
	</div>
	<!-- 第三層 -->
		<%@include file="footer.jsp"%>
	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</body>

</html>