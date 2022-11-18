package tw.com.hfu.controller.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import tw.com.hfu.dao.web.impl.MemberAdminDaoImpl;
import tw.com.hfu.dao.web.impl.RestaurantDaoImpl;
import tw.com.hfu.entity.MemberAdmin;
import tw.com.hfu.entity.Restaurant;
import tw.com.hfu.service.web.MemberAdminWebService;
import tw.com.hfu.service.web.RestaurantWebService;

/**
 * Servlet implementation class MemberLoginServlet
 */
@WebServlet("/mls")
/*
 * 管理者登入servlet
 */
public class MemberAdminLoginServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
				
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		System.out.println("Admin Login!!! post method");
		
		/* ------ service ------ */ 
		MemberAdminWebService maService = new MemberAdminWebService(new MemberAdminDaoImpl());
		RestaurantWebService rService = new RestaurantWebService(new RestaurantDaoImpl());
		//get login page's input
		String ma_email = request.getParameter("ma_email");
		String ma_password = request.getParameter("ma_password");
		
		//比對loginAdmin 方法中(ma_email, ma_password) 是否存在 true false
		MemberAdmin admin = maService.loginAdmin(ma_email, ma_password);
		
		// if admin == null: admin does not exist, dispatch to error page(fail.jsp)
		if (admin == null) {
			request.getRequestDispatcher("fail.jsp").forward(request, response);
			return;
		}
		
		// give session and set admin info to session scope	
		HttpSession session = request.getSession(true);
		
		session.setAttribute("ma_email", admin.getMa_email());
		session.setAttribute("ma_password",admin.getMa_password());
		session.setAttribute("ma_name", admin.getMa_name());
		session.setAttribute("ma_picture", admin.getMa_picture());
		System.out.println(admin);
		System.out.println("adminpic = " + admin.getMa_picture());
		
		// get all restaurantList
		List<Restaurant> restaurantList = rService.getAllRestaurant();	
		session.setAttribute("restaurantList", restaurantList);
	
		System.out.println("restaurantList = " + restaurantList);

		request.getRequestDispatcher("/admin/adminindex.jsp").forward(request, response);				
	}
}
