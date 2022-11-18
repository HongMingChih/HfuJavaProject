package tw.com.hfu.controller.web;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;

import tw.com.hfu.dao.web.BaseDao;
import tw.com.hfu.dao.web.impl.RestaurantDaoImpl;
import tw.com.hfu.entity.Restaurant;

/**
 * Servlet implementation class IndexServlet
 */
@WebServlet("/indexServlet")
public class IndexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/*
	 * 入口Sever: tomcat start -->  /indexServlet --> /index.jsp
	 */
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		// 取出實作方法搜尋全部restaurant資料
		BaseDao<Restaurant> dao = new RestaurantDaoImpl();
		ArrayList<Restaurant> restaurant = dao.queryAll();

		request.setAttribute("restaurant", restaurant);
		//存入資料後轉發至首頁
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
