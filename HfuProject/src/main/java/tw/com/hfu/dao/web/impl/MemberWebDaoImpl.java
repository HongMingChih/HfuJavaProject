package tw.com.hfu.dao.web.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import tw.com.hfu.dao.web.BaseDao;
import tw.com.hfu.entity.Member;
import tw.com.hfu.utils.JDBCUtils;

public class MemberWebDaoImpl extends BaseDao<Member> {

	@Override
	public void update(Member member) {
		Connection conn = null;
		PreparedStatement ps = null;
	
		try {
			
			conn = JDBCUtils.getConnection();
			String deleteSQL = "update member set mName=?,mAccount=?,mPassword=?,mPhone=?,mPicture=?,mEmail=? where mId=? ";
			ps = conn.prepareStatement(deleteSQL);
			ps.setString(1, member.getName());
			ps.setString(2, member.getAccount());
			ps.setString(3, member.getPassword());
			ps.setString(4, member.getPhone());
			ps.setString(5, member.getPicture());
			ps.setString(6, member.getEmail());
			ps.setInt(7, member.getId());
			
			ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			
			JDBCUtils.closeConnection(conn, ps, null);
		}
	}
	
	@Override
	public void delete(Integer Id) {
		
		Connection conn = null;
		PreparedStatement ps = null;
		
		try {
			
			conn = JDBCUtils.getConnection();
			String deleteSQL = "delete from member where mId=?";
			ps = conn.prepareStatement(deleteSQL);
			ps.setInt(1, Id);
			ps.executeUpdate();
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		} finally {
			
			JDBCUtils.closeConnection(conn, ps, null);
		}
	}
	
	@Override
	public ArrayList<Member> queryAll() {
		
		Connection conn = JDBCUtils.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		ArrayList<Member> lists = new ArrayList<>();
		
		try {
			String querySQL = "select mId,mName,mAccount,mPassword,mPhone,mPicture,mEmail from member order by mId desc";
			ps = conn.prepareStatement(querySQL);
			rs = ps.executeQuery();
			
			while (rs.next()) {
				Member member = new Member();
				member.setId(rs.getInt("mId"));
				member.setName(rs.getString("mName"));
				member.setAccount(rs.getString("mAccount"));
				member.setPassword(rs.getString("mPassword"));
				member.setPhone(rs.getString("mPhone"));
				member.setPicture(rs.getString("mPicture"));
				member.setEmail(rs.getString("mEmail"));
				
				lists.add(member);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			
			JDBCUtils.closeConnection(conn, ps, rs);
		}
		return lists;
	}
	
	@Override
	public ArrayList<Member> query(String name) {
		
		Connection conn = JDBCUtils.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		ArrayList<Member> lists = new ArrayList<>();
		
		try {
			
			String querySQL = "select * from member where mName like ?";
			ps = conn.prepareStatement(querySQL);
			ps.setString(1, "%" +name+ "%");
			rs = ps.executeQuery();
			
			while (rs.next()) {
				Member member = new Member();
				member.setId(rs.getInt("mId"));
				member.setName(rs.getString("mName"));
				member.setAccount(rs.getString("mAccount"));
				member.setPassword(rs.getString("mPassword"));
				member.setPhone(rs.getString("mPhone"));
				member.setPicture(rs.getString("mPicture"));
				member.setEmail(rs.getString("mEmail"));
				lists.add(member);
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		} finally {
			
			JDBCUtils.closeConnection(conn, ps, rs);
		}
		return lists;
	}

	@Override
	public void add(Member member) {
		
		// 連接資料庫
		Connection conn = JDBCUtils.getConnection();
		PreparedStatement ps = null;
		
		try {
			// 新增ＳＱＬ語法
			String addSQL = "insert into member(mName,mAccount,mPassword,mPhone,mPicture,mEmail)values(?,?,?,?,?,?);";
			// 傳送語法
			ps = conn.prepareStatement(addSQL);
			ps.setString(1, member.getName());
			ps.setString(2, member.getAccount());
			ps.setString(3, member.getPassword());
			ps.setString(4, member.getPhone());
			ps.setString(5, member.getPicture());
			ps.setString(6, member.getEmail());
			ps.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//有開有關，先開後關
			JDBCUtils.closeConnection(conn, ps, null);
		}

	}
	
	@Override
	public ArrayList<Member> querymenu(String cate) {
		
		return null;
	}
}
