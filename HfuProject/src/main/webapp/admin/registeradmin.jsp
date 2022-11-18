<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
	<!DOCTYPE html>
	<html lang="en">

	<head>
		<meta charset="utf-8" />
		<meta http-equiv="X-UA-Compatible" content="IE=edge" />
		<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
		<meta name="description" content="" />
		<meta name="author" content="" />
		<title>管理者註冊</title>
		<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
		<link href="../css/register.css" rel="stylesheet" />
		<script src="https://use.fontawesome.com/releases/v6.1.0/js/all.js"></script>
	</head>

	<body class="bg-primary" style="height: 100%; margin: 0;">
		<div id="layoutAuthentication" style="min-height: 100%;">
			<div id="layoutAuthentication_content" style="padding-bottom: 180px;">
				<main>
					<div class="container">
						<div class="row justify-content-center">
							<div class="col-lg-7">
								<div class="card shadow-lg border-0 rounded-lg mt-5">
									<div class="card-header">
										<h3 class="text-center font-weight-light my-4">註冊為管理者</h3>
									</div>
									<div class="card-body">
										<form action="../mrs" method="POST">
											<div class="row mb-3"></div>
											
											<div class="form-floating mb-3">
												<input class="form-control" id="inputName" type="text" name="ma_name"
													required="required" placeholder="稱呼" /> <label
													for="exampleInputName1">創建管理者名稱</label>
											</div>
											
											<div class="form-floating mb-3">
												<input class="form-control" id="inputEmail" type="email" name="ma_email"
													required="required" placeholder="name@gmail.com" /> <label
													for="exampleInputEmail1">創建信箱</label>
											</div>
											
											<div class="row mb-3">
												<div class="col-md-6">
													<div class="form-floating mb-3 mb-md-0">
														<input class="form-control" id="inputPassword" type="password"
															name="ma_password" placeholder="請輸入密碼..."
															required="required" /> <label
															for="exampleInputPassword1">創建密碼</label>
													</div>
												</div>
																						
												<br>
												<div class="form-floating mb-3">
												<input class="form-control" id="picture" type="text" name="ma_picture" placeholder="Image URL" /> 
													<label for="picture">頭像</label>
												</div>
											</div>
											<div>
												<button type="reset" class="btn btn-primary btn-block">重填</button>
												<button type="submit" class="btn btn-primary btn-block">註冊</button>
											</div>

										</form>
									</div>
									<div class="card-footer text-center py-3">
										<div class="small">
											<a href="loginadmin.jsp">返回登入頁面</a>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</main>
			</div>
		</div>
			<%@include file="footer.jsp"%>
		<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
		<script src="js/scripts.js"></script>

	</body>

	</html>