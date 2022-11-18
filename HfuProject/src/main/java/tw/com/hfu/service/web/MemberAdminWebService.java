package tw.com.hfu.service.web;

import java.util.List;

import tw.com.hfu.dao.web.BaseDao;
import tw.com.hfu.entity.MemberAdmin;

/**
 * Admin Login Servic: handle all business logic for manipulating admin request
 * 
 * @author toka
 *
 */
public class MemberAdminWebService {
	
	// dao
	BaseDao<MemberAdmin> adminDao;
	
	// constructors
	public MemberAdminWebService(BaseDao<MemberAdmin> memberAdminDaoImpl) {
		
		this.adminDao = memberAdminDaoImpl;
	}
	
	// admin login method
	public MemberAdmin loginAdmin(String maEmail, String maPassword) {
		
		// get member admin list
		List<MemberAdmin> adminList = adminDao.query(maEmail);
		
		if (adminList.size() == 0) return null;
		
		// check if maEmail and Password is correct
		MemberAdmin admin = adminList.get(0);	
		if(admin.getMa_email().equals(maEmail) && admin.getMa_password().equals(maPassword)) {
			return admin;
		}
		
		return null;
	}
}
