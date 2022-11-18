package tw.com.hfu.service.android;

import javax.servlet.http.HttpServletRequest;

import tw.com.hfu.dao.android.BaseDAO;
import tw.com.hfu.entity.Member;

public class MemberService {
	
	private BaseDAO<Member> dao;
	
	public MemberService(BaseDAO<Member> memberDAOImpl) {
		this.dao = memberDAOImpl;
	}
	
	/**
	 * login method
	 * @param account
	 * @param password
	 * @return Correspond Member if both account and password are correct.
	 */
	public Member login(String account, String password) {
		
		Member memberdb = dao.getByKeyword(account);
	
		if(memberdb == null) {
			return null;
		}
		
		if (memberdb.getAccount().equals(account) 
				&& memberdb.getPassword().equals(password)) {
			return memberdb;
		}
		
		return null;	
	};
	
	/**
	 * registration
	 * @param member
	 * @return true: successful
	 */
	public Boolean registration(Member member) {
		
		System.out.println("----- Service: registration -----");
		// check if account name duplicate
		System.out.println("check if account duplicated");
		if(dao.getByKeyword(member.getAccount()) != null) {
			return false;
		}

		// add to db
		dao.insert(member);
		System.out.println("user: " + member.getAccount()+ " registered successfully!");
		return true;
	};
	
	public Member getMemberData(String account) {
		
		return dao.getByKeyword(account);
	}
	
	/**
	 * logout: invalid session 
	 * @param request
	 */
	public void logout(HttpServletRequest request) {
		// make session invalidate
		if(request.isRequestedSessionIdFromCookie()) {
			
			if(request.isRequestedSessionIdValid()) {
				request.getSession().invalidate();
				
				System.out.println("Logout");
			}
		}
	}
	
	/**
	 * Update user profile picture 
	 * 
	 * @param account
	 * @param encPic
	 * @return updated picture encoded with base64.
	 */
	public String updatePic(String account, String encPic) {
		
		// get member by account
		Member member = dao.getByKeyword(account);
		
		// update member by account with new pic
		member.setPicture(encPic);
		dao.update(member);
		
		System.out.println("Successfully update the picture");
		
		// send pic back
		String updatedPic = dao.getByKeyword(account).getPicture();
		
		return updatedPic;
	}
}
