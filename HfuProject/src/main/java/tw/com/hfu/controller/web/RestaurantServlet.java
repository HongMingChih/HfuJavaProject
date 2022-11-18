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

@WebServlet("/restaurantServlet")
public class RestaurantServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	private String rname = null;
	private String rcate = null;
	
	/*
	 * 餐廳頁面操作Servlet
	 */

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		BaseDao<Restaurant> dao = new RestaurantDaoImpl();
		String status = request.getParameter("status");
	
		if (status.equals("add")) {

			Restaurant restaurant = new Restaurant();
			restaurant.setName(request.getParameter("rName"));
			restaurant.setDescription(request.getParameter("rDescription"));
			restaurant.setPicture(request.getParameter("rPicture"));
			restaurant.setCategory(request.getParameter("rCategory"));
			restaurant.setAddress(request.getParameter("rAddress"));
			restaurant.setPhone(request.getParameter("rPhone"));
			dao.add(restaurant);
			
			// 跳轉回新增頁面
			request.getRequestDispatcher("MemberAdminActionServlet").forward(request, response);
			
		} else if (status.equals("query")) {
			rname = request.getParameter("sName");
			if (rname.length() != 0) {
				ArrayList<Restaurant> restaurant = dao.query(rname);
				request.setAttribute("restaurant", restaurant);
				request.getRequestDispatcher("query.jsp").forward(request, response);
//			request.getRequestDispatcher("storequery.jsp").forward(request, response);
			} else if (rname.length() == 0) {
//				response.sendRedirect("admin/storequery.jsp");
//				request.getHeader("referer").
//				response.setContentType("text/html;charset=UTF-8");
				PrintWriter out = response.getWriter();
//				out.println("<HTML> <BODY>");
				out.println("<script language='javascript'>");
//				out.println("parent.biao.location.replace('admin/storequery.jsp')");//填寫需跳轉頁面，或者返回上一頁用("history.go(-1)");
				out.println("history.go(-1)");
				out.println("</script>");
//				out.println("</body> </html>");
//				out.flush();
				out.close();
			}

		} else if (status.equals("queryadminmenu")) {
			rcate = request.getParameter("rCate");
			if (rcate.length() != 0) {
				ArrayList<Restaurant> restaurant = dao.querymenu(rcate);
				request.setAttribute("restaurant", restaurant);
				request.getRequestDispatcher("admin/storequery.jsp").forward(request, response);
			} else if (rcate.length() == 0) {
				PrintWriter out = response.getWriter();
				out.println("<script language='javascript'>");
				out.println("history.go(-1)");
				out.println("</script>");
				out.close();
			}
		} else if (status.equals("queryalladmin")) {

			ArrayList<Restaurant> restaurant = dao.queryAll();
			request.setAttribute("restaurant", restaurant);
//			request.getRequestDispatcher("admin/query.jsp").forward(request, response);
			request.getRequestDispatcher("admin/storequery.jsp").forward(request, response);

		} else if (status.equals("queryadmin")) {
			rname = request.getParameter("sName");
			if (rname.length() != 0) {
				ArrayList<Restaurant> restaurant = dao.query(rname);
				request.setAttribute("restaurant", restaurant);
//			request.getRequestDispatcher("admin/query.jsp").forward(request, response);
				request.getRequestDispatcher("admin/storequery.jsp").forward(request, response);
			} else if (rname.length() == 0) {
//				response.sendRedirect("admin/storequery.jsp");
//				request.getHeader("referer").
//				response.setContentType("text/html;charset=UTF-8");
				PrintWriter out = response.getWriter();
//				out.println("<HTML> <BODY>");
				out.println("<script language='javascript'>");
//				out.println("parent.biao.location.replace('admin/storequery.jsp')");//填寫需跳轉頁面，或者返回上一頁用("history.go(-1)");
				out.println("history.go(-1)");
				out.println("</script>");
//				out.println("</body> </html>");
//				out.flush();
				out.close();
			}

		} else if (status.equals("delete")) {
			// 取得view輸入
			int id = Integer.parseInt(request.getParameter("rId"));
			dao.delete(id);
			ArrayList<Restaurant> restaurant = dao.query(rname);

			request.setAttribute("restaurant", restaurant);
//			request.getRequestDispatcher("admin/query.jsp").forward(request, response);
//			request.getRequestDispatcher("admin/storequery.jsp").forward(request, response);
			request.getRequestDispatcher("MemberAdminActionServlet").forward(request, response);
			
		} else if (status.equals("update")) {
			
			Restaurant restaurant = new Restaurant();
			restaurant.setId(Integer.parseInt(request.getParameter("rId")));
			restaurant.setName(request.getParameter("rName"));
			restaurant.setCategory(request.getParameter("rCategory"));
			restaurant.setAddress(request.getParameter("rAddress"));
			restaurant.setPicture(request.getParameter("rPicture"));
			restaurant.setDescription(request.getParameter("rDescription"));
			restaurant.setPhone(request.getParameter("rPhone"));
			dao.update(restaurant);
			ArrayList<Restaurant> restaurant1 = dao.queryAll();
			request.setAttribute("restaurant", restaurant1);
//			request.getRequestDispatcher("admin/index.jsp").forward(request, response);
//			request.getRequestDispatcher("admin/storeadmin.jsp").forward(request, response);
			request.getRequestDispatcher("MemberAdminActionServlet").forward(request, response);
			
		} else if (status.equals("queryall")) {

			ArrayList<Restaurant> restaurant = dao.queryAll();
			
			request.setAttribute("restaurant", restaurant);
			request.getRequestDispatcher("query.jsp").forward(request, response);

		} else if (status.equals("adminindex")) {
			
//			HttpSession session=request.getSession();
			ArrayList<Restaurant> restaurant = dao.queryAll();
			request.setAttribute("restaurant", restaurant);
			request.getRequestDispatcher("admin/index.jsp").forward(request, response);

		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
