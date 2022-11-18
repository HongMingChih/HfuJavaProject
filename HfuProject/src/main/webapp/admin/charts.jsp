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
<link rel="stylesheet" href="../css/charts.css" />
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
								<div class="ccc">
									<input class="form-control me-2" type="search"
										placeholder="            店家搜尋" aria-label="店家搜尋" name="sName">
									<button class="btn btn-danger" type="submit">查詢</button>
									<input type="hidden" name="status" value="queryadmin">
								</div>

								<!-- 	<a href="javascript:;" onClick="javascript:history.back(-1);">返回上一页</a> -->
							</div>

						</form>


					</div>
				</div>
				
			
					
					<div class="">
						
						<img style="width: 50em;display:block; margin:auto;" alt="" src="../images/member.png">
						<img style="width: 50em;display:block; margin:auto;" alt="" src="../images/restaurant.png">
						<img style="width: 50em;display:block; margin:auto;" alt="" src="../images/restaurant2.png">
					</div>



			

			</section>
		</section>

	</div>
	<%@include file="footer.jsp"%>


</body>




</html>












