<%@page import="tw.com.hfu.entity.MemberAdmin"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>個人資料</title>


<link rel="stylesheet" href="css/profile.css" />

<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css" />

</head>
<body>
	<div class="container">
		<%@include file="successadmin.jsp"%>

		<section class="main">


			<section class="attendance">
			
			<div class="row users">
				
				<div class="card">
					<div class="card-header">
						<h1>個人資料</h1>
					</div>
				</div>
			
			</div>

				<div class="card-body">
				
					<table class="table">


						<thead>
							<tr>
							<!-- 	<th scope="col">頭像</th> -->
								<th scope="col">名稱</th>
								<th scope="col">信箱</th>
							<!-- 	<th scope="col">密碼</th> -->


							</tr>
						</thead>

						<tbody>
							<tr>
							<%-- <td width="100">頭像
									<div class="card-text" id="nys1"><%=session.getAttribute("ma_picture")%></div>
								</td> --%>
								<td width="100">名稱
									<div class="card-text" id="nys1"><%=session.getAttribute("ma_name")%></div>
								</td>
								<td width="100">信箱
									<div class="card-text" id="nys1"><%=session.getAttribute("ma_email")%></div>
								</td>
							<%-- 	<td width="100">密碼
									<div class="card-text" id="nys1"><%=session.getAttribute("ma_password")%></div>
								</td> --%>
						</tbody>

					</table>
				</div>

			</section>
		</section>

	</div>
	<%@include file="footer.jsp"%>


</body>




</html>












