<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!doctype html>
<html lang="en">

<head>
<title>商家新增</title>

<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<link rel="stylesheet" href="css/storeadd.css" />
<!-- Font Awesome Cdn Link -->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css" />

</head>

<body>
	<!--第一層 導覽列-->
	<div class="container">

		<%@include file="successadmin.jsp"%>


		<!-- 第二層 -->
		<section class="main">

			<div class="row users">
				<div class="col-3 sidenav"></div>

				<div class="col-8 card">
					<h4>商家新增</h4>

					<form action="<%=request.getContextPath()%>/restaurantServlet"
						method="POST">
						
						<div class="mb-3">
							<label for="exampleFormControlInput1" class="form-label">名稱</label>
							<input type="text" class="form-control"
								id="exampleFormControlInput1" placeholder="名稱" name="rName">
						</div>
						
						<div class="mb-3">
							<label for="exampleFormControlInput1" class="form-label">類型</label>
							<input type="text" class="form-control"
								id="exampleFormControlInput1" placeholder="類型" name="rCategory">
						</div>
						
						<div class="mb-3">
							<label for="exampleFormControlInput1" class="form-label">說明</label>
							<input type="text" class="form-control"
								id="exampleFormControlInput1" placeholder="說明" name="rDescription">
						</div>
						
						<div class="mb-3">
							<label for="exampleFromControlInput1" class="form-label">地址</label>
							<input type="text" class="form-control"
								id="exampleFromControlInput1" placeholder="地址" name="rAddress">
						</div>
						
						<div class="mb-3">
							<label for="exampleFromControlInput1" class="form-lable">電話</label>
							<input type="text" class="form-control"
								id="exampleFromControlInput1" placeholder="電話" name="rPhone">
						</div>
						
						<div class="mb-3">
							<label for="exampleFormControlInput1" class="form-label">圖片</label>
							<input type="text" class="form-control"
								id="exampleFormControlInput1" placeholder="圖片" name="rPicture">
						</div>

						<button type="submit" class="btn btn-danger">新增</button>
						<input type="hidden" name="status" value="add">
					</form>
					
                       <a style=" margin:0 auto;"  href="javascript:;" onClick="javascript:history.back(-1);">返回上一頁</a>
                         </div>
				
					     
		<div class="col-3 sidenav"></div>

				</div>
		

		</section>
		<!-- 第三層 -->
	</div>
	<%@include file="footer.jsp"%>


	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</body>

</html>