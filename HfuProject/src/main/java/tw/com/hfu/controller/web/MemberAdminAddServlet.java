package tw.com.hfu.controller.web;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tw.com.hfu.dao.web.BaseDao;
import tw.com.hfu.dao.web.impl.RestaurantDaoImpl;
import tw.com.hfu.entity.Restaurant;

/**
 * Servlet implementation class MemberAdminAddServlet
 */
@WebServlet("/memberAdminAddServlet")
public class MemberAdminAddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		BaseDao<Restaurant> dao = new RestaurantDaoImpl();
		ArrayList<Restaurant> restaurant = dao.queryAll();

		request.setAttribute("restaurant", restaurant);
		//存入資料後轉發至首頁
	
		request.getRequestDispatcher("admin/add.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
