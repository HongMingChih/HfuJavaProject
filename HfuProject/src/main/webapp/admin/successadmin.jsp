<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<nav>
			<ul>
				<li>
					<a href="#" class="logo"> <img src="<%=session.getAttribute("ma_picture")%>" onerror="this.src='../images/p1.jpg'" > <!-- onerror="this.src='../images/p1.jpg'" -->
						<span class="nav-item">管理者 <%=session.getAttribute("ma_name")%></span>
					</a>
				</li>
				
				<li><a
					href="<%=request.getContextPath()%>/memberAdminIndexServlet"> <i
						class="fas fa-warehouse"></i> <span class="nav-item">返回首頁</span>
				</a></li>

				<li><a href="#"> <i class="fas fa-menorah"></i> <span
						class="nav-item">公告</span>
				</a></li>
				<li><a
					href="<%=request.getContextPath()%>/MemberWebListServlet"> <i
						class="fas fa-users"></i> <span class="nav-item">會員管理</span>
				</a></li>
				<li><a
					href="<%=request.getContextPath()%>/MemberAdminActionServlet">
						<i class="fas fa-wine-glass-alt"></i><span class="nav-item">餐廳管理</span>
				</a></li>
				<li><a href="admin/charts.jsp"> <i class="fas fa-chart-bar"></i> <span
						class="nav-item">統計表</span>
				</a></li>
				<li><a
					href="<%=request.getContextPath()%>/memberAdminProfileServlet">
						<i class="fas fa-user-lock"></i><span class="nav-item">帳戶資訊</span>
				</a></li>
				<li><a href="<%=request.getContextPath()%>/MemberAdminListServlet"> <i
						class="fas fa-tools"></i><span class="nav-item">管理者成員</span>
				</a></li>
				<li><a href="#"> <i class="fas fa-cog"></i> <span
						class="nav-item">設定</span>
				</a></li>

				<li><a
					href="<%=request.getContextPath()%>/memberAdminLogoutServlet"
					class="logout"> <i class="fas fa-sign-out-alt"></i> <span
						class="nav-item">登出</span>

				</a></li>


			</ul>
		</nav>
