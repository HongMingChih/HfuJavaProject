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
import tw.com.hfu.dao.web.impl.MemberWebDaoImpl;
import tw.com.hfu.entity.Member;

/**
 * Servlet implementation class MemberServlet
 */
@WebServlet("/MemberWebServlet")
public class MemberWebServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private String rname = null;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		BaseDao<Member> dao = new MemberWebDaoImpl();
		String status = request.getParameter("status");

		if (status.equals("add")) {	
			
			Member member = new Member();
			member.setName(request.getParameter("mName"));
			member.setAccount(request.getParameter("mAccount"));
			member.setPassword(request.getParameter("mPassword"));
			member.setPhone(request.getParameter("mPhone"));
			member.setPicture(request.getParameter("mPicture"));
			member.setEmail(request.getParameter("mEmail"));
			dao.add(member);
			// 跳轉回新增頁面
//			request.getRequestDispatcher("admin/add.jsp").forward(request, response);
//			request.getRequestDispatcher("member/memberadd.jsp").forward(request, response);
			request.getRequestDispatcher("MemberWebListServlet").forward(request, response);
		}else if (status.equals("query")) {
			rname=request.getParameter("sName");
			if (rname.length() != 0) {
			ArrayList<Member> member=dao.query(rname);
			request.setAttribute("member", member);
			request.getRequestDispatcher("query.jsp").forward(request, response);
//			request.getRequestDispatcher("storequery.jsp").forward(request, response);
			}else if (rname.length() == 0) {
//				response.sendRedirect("admin/storequery.jsp");
//				request.getHeader("referer").
//				response.setContentType("text/html;charset=UTF-8");
				PrintWriter out=response.getWriter();
//				out.println("<HTML> <BODY>");
				out.println("<script language='javascript'>");
//				out.println("parent.biao.location.replace('admin/storequery.jsp')");//填寫需跳轉頁面，或者返回上一頁用("history.go(-1)");
				out.println("history.go(-1)");
				out.println("</script>");
//				out.println("</body> </html>");
//				out.flush();
				out.close();
			}
			
		}else if (status.equals("queryalladmin")) {
			
			ArrayList<Member> member=dao.queryAll();
			request.setAttribute("member", member);
//			request.getRequestDispatcher("admin/query.jsp").forward(request, response);
//			request.getRequestDispatcher("admin/storequery.jsp").forward(request, response);
			request.getRequestDispatcher("member/memberquery.jsp").forward(request, response);
			
		} else if (status.equals("querymember")) {
			rname=request.getParameter("sName");
			if (rname.length() != 0) {
				ArrayList<Member> member=dao.query(rname);
			request.setAttribute("memberList", member);
//			request.getRequestDispatcher("admin/query.jsp").forward(request, response);
//			request.getRequestDispatcher("admin/storequery.jsp").forward(request, response);
//			request.getRequestDispatcher("admin/storequery.jsp").forward(request, response);
			request.getRequestDispatcher("member/memberlist.jsp").forward(request, response);
			}else if (rname.length() == 0) {
//				response.sendRedirect("admin/storequery.jsp");
//				request.getHeader("referer").
//				response.setContentType("text/html;charset=UTF-8");
				PrintWriter out=response.getWriter();
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
			int id = Integer.parseInt(request.getParameter("mId"));
			dao.delete(id);
			ArrayList<Member> member = dao.query(rname);
				
			request.setAttribute("member", member);
//			request.getRequestDispatcher("admin/query.jsp").forward(request, response);
//			request.getRequestDispatcher("member/memberlist.jsp").forward(request, response);
			request.getRequestDispatcher("MemberWebListServlet").forward(request, response);
		} else if (status.equals("update")) {
			Member member = new Member();
			member.setId(Integer.parseInt(request.getParameter("mId")));
			member.setName(request.getParameter("mName"));
			member.setAccount(request.getParameter("mAccount"));
			member.setPassword(request.getParameter("mPassword"));
			member.setPhone(request.getParameter("mPhone"));
			member.setPicture(request.getParameter("mPicture"));
			member.setEmail(request.getParameter("mEmail"));
			dao.update(member);
			ArrayList<Member> member1=dao.queryAll();
			request.setAttribute("memberList", member1);
//			request.getRequestDispatcher("admin/index.jsp").forward(request, response);
			request.getRequestDispatcher("member/memberlist.jsp").forward(request, response);
		}else if (status.equals("queryall")) {
		
			ArrayList<Member> member=dao.queryAll();
			request.setAttribute("member", member);
			request.getRequestDispatcher("memberquery.jsp").forward(request, response);
			
		}
		else if (status.equals("adminindex")) {
//			HttpSession session=request.getSession();
			ArrayList<Member> member=dao.queryAll();
			request.setAttribute("member", member);
			request.getRequestDispatcher("memberquery.jsp").forward(request, response);
			
		}
	}

	
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
