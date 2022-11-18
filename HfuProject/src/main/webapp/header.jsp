<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<!-- 第一層 導覽列-->
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
	<div class="container-fluid">
		<a class="navbar-brand"
			href="<%=request.getContextPath()%>/indexServlet">你的餐廳</a>
		<button class="navbar-toggler" type="button" data-bs-toggle="collapse"
			data-bs-target="#navbarSupportedContent"
			aria-controls="navbarSupportedContent" aria-expanded="false"
			aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="navbarSupportedContent">
		 <button class="btn btn-link btn-sm order-1 order-lg-0 me-4 me-lg-0" id="sidebarToggle" href="#!" ><i
                class="fas fa-bars"></i></button>
			<ul class="navbar-nav me-auto mb-2 mb-lg-0">
				
				<!-- 
				<li class="nav-item"><a class="nav-link" href="#">??</a></li> -->
				
				<!-- <li class="nav-item dropdown"><a
					class="nav-link dropdown-toggle" href="#" id="navbarDropdown"
					role="button" data-bs-toggle="dropdown" aria-expanded="false">
						產品 </a>
					<ul class="dropdown-menu" aria-labelledby="navbarDropdown">
						<li><a class="dropdown-item" href="#">空</a></li>
						<li><a class="dropdown-item" href="#">Another action</a></li>
						<li>
							<hr class="dropdown-divider">
						</li>
						<li><a class="dropdown-item" href="#">Something else here</a></li>
					</ul></li> -->

			</ul>
			<form class="d-flex" role="search"
				action="<%=request.getContextPath()%>/restaurantServlet"
				method="POST">
				<input class="form-control me-2" type="search" placeholder="產品搜尋"
					aria-label="產品搜尋" name="sName">
				<button class="btn btn-outline-success" type="submit">Search</button>
				<input type="hidden" name="status" value="query">

			</form>
		</div>

	</div>
	<!-- Navbar-->
        <ul class="navbar-nav ms-auto ms-md-0 me-3 me-lg-4">
            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" id="navbarDropdown" href="#" role="button" data-bs-toggle="dropdown"
                    aria-expanded="false"><i class="fas fa-user fa-fw"></i></a>
                <ul class="dropdown-menu dropdown-menu-end" aria-labelledby="navbarDropdown">
                    <li><a class="dropdown-item" href="#!">Settings</a></li>
                    <li><a class="dropdown-item" href="admin/loginadmin.jsp">登入</a></li>
                    <li>
                        <hr class="dropdown-divider" />
                    </li>
                    <li><a class="dropdown-item" href="#">自定</a></li>
                </ul>
            </li>
        </ul>
</nav>