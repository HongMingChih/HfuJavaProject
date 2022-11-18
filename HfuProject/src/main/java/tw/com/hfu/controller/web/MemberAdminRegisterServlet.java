package tw.com.hfu.controller.web;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tw.com.hfu.dao.web.BaseDao;
import tw.com.hfu.dao.web.impl.MemberAdminDaoImpl;
import tw.com.hfu.entity.MemberAdmin;

@WebServlet("/mrs")
public class MemberAdminRegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
/*
 * 註冊Servlet
 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		//取得資料至ma 
		MemberAdmin ma = new MemberAdmin();
		ma.setMa_name(request.getParameter("ma_name"));
		ma.setMa_email(request.getParameter("ma_email"));
		ma.setMa_password(request.getParameter("ma_password"));
		ma.setMa_picture(request.getParameter("ma_picture"));
				
		BaseDao<MemberAdmin> dao = new MemberAdminDaoImpl();
		//實作方法addM
		int rs = dao.addM(ma);
		if (rs != 1) {
			//如果成功重定向至登入頁面
			response.sendRedirect("admin/loginadmin.jsp"); // 重定向
			
		}else {
			//失敗跳回入口首頁
			response.sendRedirect("indexServlet");
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
