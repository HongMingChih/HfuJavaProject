package tw.com.hfu.controller.web;

import java.io.IOException;
//import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/memberAdminLogoutServlet")
public class MemberAdminLogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/*
	 * 登出按鈕
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		// if no session --> go back to index
		if (request.isRequestedSessionIdFromCookie()) {
			response.sendRedirect("indexServlet");
			return;
		}
		
		// if session valid --> make invalid
		if (request.isRequestedSessionIdValid()) {
			request.getSession(false).invalidate();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
