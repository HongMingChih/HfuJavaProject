<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!doctype html>
<html lang="en">

<head>
<title>忘記密碼</title>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<link rel="stylesheet" href="../css/password.css">
<script type="text/javascript" src="bootstrap/js/bootstrap.min.js"></script>
</head>

<body>
	<div id="layoutAuthentication" style="min-height: 100%;">
		<div id="layoutAuthentication_content" style="padding-bottom: 300px;">
			<main>
				<div class="container">
					<div class="row justify-content-center">
						<div class="col-lg-5">
							<div class="card shadow-lg border-0 rounded-lg mt-5">
								<div class="card-header">
									<h3 class="text-center font-weight-light my-4">找回密碼</h3>
								</div>
								<div class="card-body">
									<div class="small mb-3 text-muted">輸入您的電子郵件地址，我們將向您發送一個鏈接以重置您的密碼。</div>
									<form action="<%=request.getContextPath()%>/RetrievePasswordServlet" method="POST">
										<div class="form-floating mb-3">
											<input class="form-control" id="inputEmail" type="email"
												name="email" placeholder="name@example.com" /> <label
												for="inputEmail">電子郵件地址</label>
										</div>

										<div
											class="d-flex align-items-center justify-content-between mt-4 mb-0">
											 <a class="btn btn-primary" href="../admin/loginadmin.jsp">返回登入</a> 
											
											<button type="submit" class="btn btn-success" id="button">找回密碼</button>
										</div>
										
									</form>
								</div>
								<div class="card-footer text-center py-3">
									<div class="small">
										<a href="../admin/registeradmin.jsp">需要一個帳戶？ 註冊！</a>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</main>
		</div>
			<%@include file="footer.jsp"%>
	</div>


	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</body>

</html>