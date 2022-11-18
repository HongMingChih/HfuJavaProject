package tw.com.hfu.controller.android;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import tw.com.hfu.dao.android.impl.MemberDAOImpl;
import tw.com.hfu.entity.Member;
import tw.com.hfu.service.android.MemberService;
import tw.com.hfu.utils.NetUtils;

@WebServlet("/MemberController/*")
public class MemberController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private String requestBody = null;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		System.out.println("Now entering MemberController POST method..");
		
		
		/* ----- service ----- */
		
		MemberService service = new MemberService(new MemberDAOImpl());
		
		/* ----- Handle the request actions ----- */
		
		// get request body
		requestBody = NetUtils.getRequestBody(request.getInputStream());
		System.out.println("requestBody = " + requestBody);
		
		// get action
		String pathInfo = request.getPathInfo();		
		String requestAction = NetUtils.getRequestAction(pathInfo);
		
		System.out.println(requestAction);
		if(requestAction == null) {
			return;
		}
		
		// handle actions
		switch(requestAction) {
		
		case "login":
			
			System.out.println("login action called!!!!");
						
			@SuppressWarnings("unchecked") 
			Map<String, String> requestMap = new Gson().fromJson(requestBody, Map.class);
			
			String account = requestMap.get("inputAccount");
			String password = requestMap.get("inputPassword");
			
			Member loginMember = service.login(account, password);
			if(loginMember != null) {
				
				request.getSession();
				String loginResponse = new Gson().toJson(loginMember, Member.class);
				NetUtils.sendResponse(loginResponse, response.getOutputStream());
				
			} else {
				
				response.sendError(400);
			}
			
			break;
			
		case "registration":
			
			System.out.println("Registration action called!!!!");
			
			Member member = new Gson().fromJson(requestBody, Member.class);

			Boolean regResult = service.registration(member);
			
			if(regResult) {
				
				System.out.println("Successfully registered");
			} else {
				
				response.sendError(409);
				System.out.println("Failed registered: duplicate account");
			}
			
			break;
			
		case "getMemberData":
			
			// parse request
			String getMemberByAccount = new Gson().fromJson(requestBody, String.class);
			// get member data from db.
			Member getMember = service.getMemberData(getMemberByAccount);
			String responseData = new Gson().toJson(getMember);
			NetUtils.sendResponse(responseData, response.getOutputStream());
			
			break;
			
		case "logout":
			
			service.logout(request);
	
			break;
			
		case "updatePic":
			
			@SuppressWarnings("unchecked") Map<String, String> updPicRequestMap = new Gson().fromJson(requestBody, Map.class);
			
			String memberAccount = updPicRequestMap.get("memberAccount");
			System.out.println("member acc = " + memberAccount);
			String encPic = updPicRequestMap.get("encPic").replace("\n", "");
			// String encPic = updPicRequestMap.get("encPic").replaceAll("\\r\\n|\\r|\\n", "");
			
			
			String updatedPic = service.updatePic(memberAccount, encPic);
			
			NetUtils.sendResponse(new Gson().toJson(updatedPic, String.class), response.getOutputStream());
			
			break;
		}
			
	}
}
