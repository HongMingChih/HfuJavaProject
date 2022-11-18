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
import tw.com.hfu.dao.web.admin.MemberAdminListDaoImpl;
import tw.com.hfu.entity.MemberAdmin;

/**
 * Servlet implementation class MemberAdminListServlet
 */
@WebServlet("/MemberAdminListServlet")
public class MemberAdminListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String ma_name = null;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		BaseDao<MemberAdmin> dao = new MemberAdminListDaoImpl();

		ArrayList<MemberAdmin> memberadmin = dao.queryAll();
		request.setAttribute("memberadmin", memberadmin);
		request.getRequestDispatcher("admin/adminlist.jsp").forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		BaseDao<MemberAdmin> dao = new MemberAdminListDaoImpl();
		String status = request.getParameter("status");
		if (status.equals("queryadminlist")) {
			ma_name = request.getParameter("ma_Name");
			if (ma_name.length() != 0) {
				ArrayList<MemberAdmin> memberadmin = dao.query(ma_name);
				request.setAttribute("memberadmin", memberadmin);
				request.getRequestDispatcher("admin/adminlist.jsp").forward(request, response);
			} else if (ma_name.length() == 0) {
				PrintWriter out = response.getWriter();
				out.println("<script language='javascript'>");
				out.println("history.go(-1)");
				out.println("</script>");
				out.close();
			}
		} else {
			doGet(request, response);
		}

	}

}
