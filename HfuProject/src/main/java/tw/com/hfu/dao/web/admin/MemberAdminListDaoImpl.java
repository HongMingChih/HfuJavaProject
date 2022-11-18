package tw.com.hfu.dao.web.admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import tw.com.hfu.dao.web.BaseDao;
import tw.com.hfu.entity.MemberAdmin;
import tw.com.hfu.utils.JDBCUtils;

public class MemberAdminListDaoImpl extends BaseDao<MemberAdmin> {
	
	private Connection conn = null;
	private PreparedStatement ps = null;

	@Override
	public ArrayList<MemberAdmin> queryAll() {
		Connection conn = JDBCUtils.getConnection();
		ArrayList<MemberAdmin> lists = new ArrayList<>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			String querySQL = "select ma_id,ma_name,ma_email,ma_password,ma_picture from `memberadmin` order by ma_id desc";
			ps = conn.prepareStatement(querySQL);
			rs = ps.executeQuery();
			while (rs.next()) {
				MemberAdmin memberadmin = new MemberAdmin();
				memberadmin.setMa_id(rs.getInt("ma_id"));
				memberadmin.setMa_name(rs.getString("ma_name"));
				memberadmin.setMa_email(rs.getString("ma_email"));
				memberadmin.setMa_password(rs.getString("ma_password"));
				memberadmin.setMa_picture(rs.getString("ma_picture"));

				lists.add(memberadmin);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.closeConnection(conn, ps, rs);
		}
		return lists;
	}

	@Override
	public void add(MemberAdmin t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ArrayList<MemberAdmin> query(String name) {
		Connection conn = JDBCUtils.getConnection();
		ArrayList<MemberAdmin> lists = new ArrayList<>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			String querySQL = "select * from `memberadmin` where ma_name like ?";
			ps = conn.prepareStatement(querySQL);
			ps.setString(1,"%" +name+ "%");
			rs = ps.executeQuery();
			while (rs.next()) {
				MemberAdmin memberadmin = new MemberAdmin();
				memberadmin.setMa_id(rs.getInt("ma_id"));
				memberadmin.setMa_name(rs.getString("ma_name"));
				memberadmin.setMa_email(rs.getString("ma_email"));
				memberadmin.setMa_picture(rs.getString("ma_picture"));
//				System.out.println("memberadmin"+memberadmin);
				lists.add(memberadmin);			
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();	
		} finally {
			
			JDBCUtils.closeConnection(conn, ps, rs);
		}
		return lists;
	}

	

	@Override
	public ArrayList<MemberAdmin> querymenu(String cate) {
		// TODO Auto-generated method stub
		return null;
	}

	

	@Override
	public void delete(Integer pId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(MemberAdmin t) {
		// TODO Auto-generated method stub
		
	}

}
