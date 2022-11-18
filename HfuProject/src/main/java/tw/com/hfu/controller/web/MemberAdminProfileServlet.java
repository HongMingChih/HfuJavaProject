package tw.com.hfu.controller.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/memberAdminProfileServlet")
public class MemberAdminProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/*
	 * 個人介面Server
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
//		BaseDao<MemberAdmin> dao = new MemberAdminDaoImpl();
//		String status = request.getParameter("status");

		HttpSession session = request.getSession(false);
		// 如果session 不為空 轉發至個人介面
		if (session != null) {
//		  if (status.equals("queryprofile")) {	
//			  ArrayList<MemberAdmin> memberadmin=dao.queryAll();
//				request.setAttribute("memberadmin", memberadmin);
			request.getRequestDispatcher("admin/profile.jsp").forward(request, response);
		} else {
			// 否則重定向首頁入口
//          request.getRequestDispatcher("admin/loginadmin.jsp").forward(request, response);
			response.sendRedirect("indexServlet");
		}

//	}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
