package tw.com.hfu.dao.web.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import tw.com.hfu.dao.web.BaseDao;
import tw.com.hfu.entity.MemberAdmin;
import tw.com.hfu.utils.JDBCUtils;

public class MemberAdminDaoImpl extends BaseDao<MemberAdmin> {

	private Connection conn = null;
	private PreparedStatement ps = null;
	private ResultSet rs = null;

	@Override
	public Integer addM(MemberAdmin ma) {
		int rs = 0;
		try {
			conn = JDBCUtils.getConnection();
			String addsql = "INSERT INTO `memberadmin`(ma_name,ma_email,ma_password,ma_picture)values(?,?,?,?);";
			ps = conn.prepareStatement(addsql);
			ps.setString(1, ma.getMa_name());
			ps.setString(2, ma.getMa_email());
			ps.setString(3, ma.getMa_password());
			ps.setString(4, ma.getMa_picture());
			
			ps.execute();
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			JDBCUtils.closeConnection(conn, ps, null);
		}
		return rs;
	}

	@Override
	public ArrayList<MemberAdmin> queryAll() {
		Connection conn = JDBCUtils.getConnection();
		ArrayList<MemberAdmin> lists = new ArrayList<>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			String querySQL = "select ma_id,ma_name,ma_email,ma_password from memberadmin order by ma_id desc";
			ps = conn.prepareStatement(querySQL);
			rs = ps.executeQuery();
			while (rs.next()) {
				MemberAdmin memberadmin = new MemberAdmin();
				memberadmin.setMa_id(rs.getInt("ma_id"));
				memberadmin.setMa_name(rs.getString("ma_name"));
				memberadmin.setMa_email(rs.getString("ma_email"));
				memberadmin.setMa_password(rs.getString("ma_password"));

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
	public void delete(Integer ma_id) {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = JDBCUtils.getConnection();
			String deleteSQL = "delete from memberadmin where ma_id=?";
			ps = conn.prepareStatement(deleteSQL);
			ps.setInt(1, ma_id);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.closeConnection(conn, ps, rs);
		}
	}

	@Override
	public void update(MemberAdmin memberadmin) {
		Connection conn = null;
		PreparedStatement ps = null;

		try {
			conn = JDBCUtils.getConnection();
			String deleteSQL = "update memberadmin set ma_name=?,ma_email=?,ma_password=? where ma_id=? ";
			ps = conn.prepareStatement(deleteSQL);
			ps.setString(1, memberadmin.getMa_name());
			ps.setString(2, memberadmin.getMa_email());
			ps.setString(3, memberadmin.getMa_password());
			ps.setInt(4, memberadmin.getMa_id());

			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.closeConnection(conn, ps, rs);
		}

	}

	@Override
	public void add(MemberAdmin t) {

	}

	@Override
	public ArrayList<MemberAdmin> query(String ma_email) {
		
		
		String querySQL = "SELECT * FROM `memberadmin` WHERE ma_email=?;";
		conn = JDBCUtils.getConnection();	
		ArrayList<MemberAdmin> lists = new ArrayList<>();

		try {
			
			ps = conn.prepareStatement(querySQL);
			ps.setString(1, ma_email);
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
	public ArrayList<MemberAdmin> querymenu(String cate) {
		// TODO Auto-generated method stub
		return null;
	}

	
}
