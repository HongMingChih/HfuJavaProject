<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="header.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>更新</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
	rel="stylesheet">
</head>
<body>
	<br>
	<br>
	<div class="container">
		<div class="row">
			<div class="col-3"></div>
			<div class="col-6">
				<h3>更新產品</h3>
				<form action="<%=request.getContextPath()%>/restaurantServlet"
					method="POST">
					<div class="mb-3">
						<label for="exampleFormControInput1" class="form-label">名稱
						</label><input type="text" class="form-control"
							id="exampleFormControInput1" placeholder="名稱" name="rName"
							value="<%=request.getParameter("rName")%>">
					</div>
					<div class="mb-3">
						<label for="exampleFormControInput1" class="form-label">類型
						</label><input type="text" class="form-control"
							id="exampleFormControInput1" placeholder="類型" name="rCategory"
							value="<%=request.getParameter("rCategory")%>">
					</div>
					<div class="mb-3">
						<label for="exampleFormControInput1" class="form-label">地址
						</label><input type="text" class="form-control"
							id="exampleFormControInput1" placeholder="地址" name="rAddress"
							value="<%=request.getParameter("rAddress")%>">
					</div>
					<div class="mb-3">
						<label for="exampleFormControInput1" class="form-label">圖片
						</label><input type="text" class="form-control"
							id="exampleFormControInput1" placeholder="圖片" name="rImage"
							value="<%=request.getParameter("rImage")%>">
					</div>
					<div class="mb-3">
						<label for="exampleFormControInput1" class="form-label">說明
						</label><input type="text" class="form-control"
							id="exampleFormControInput1" placeholder="說明" name="rDesc"
							value="<%=request.getParameter("rDesc")%>">
					</div>
					<div class="mb-3">
						<label for="exampleFormControInput1" class="form-label">電話
						</label><input type="text" class="form-control"
							id="exampleFormControInput1" placeholder="電話" name="rPhone"
							value="<%=request.getParameter("rPhone")%>">
					</div>
					
					<div>
						<button type="submit" class="btn btn-danger">更新</button>
						<input type="hidden" name="status" value="update"> <input
							type="hidden" name="rId" value="<%=request.getParameter("rId")%>">
					</div>

				</form>
			</div>
			<div class=""></div>
		</div>

	</div>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>