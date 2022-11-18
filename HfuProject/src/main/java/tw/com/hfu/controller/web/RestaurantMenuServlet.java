package tw.com.hfu.controller.web;

import java.io.IOException;
import java.io.PrintWriter;
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
 * Servlet implementation class RestaurantMenuServlet
 */
@WebServlet("/RestaurantMenuServlet")
public class RestaurantMenuServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		BaseDao<Restaurant> dao = new RestaurantDaoImpl();
		String item = request.getParameter("item");
		
		if (item!=null) {
			ArrayList<Restaurant> restaurant = dao.querymenu(item);
			request.setAttribute("restaurant", restaurant);
			request.getRequestDispatcher("admin/storequery.jsp").forward(request, response);
		}else {
			PrintWriter out = response.getWriter();
			out.println("<script language='javascript'>");
			out.println("history.go(-1)");
			out.println("</script>");
			out.close();
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);

	}

}
