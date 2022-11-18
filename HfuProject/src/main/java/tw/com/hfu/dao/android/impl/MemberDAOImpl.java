package tw.com.hfu.dao.android.impl;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import tw.com.hfu.dao.android.BaseDAO;
import tw.com.hfu.entity.Member;
import tw.com.hfu.utils.JDBCUtils;


public class MemberDAOImpl implements BaseDAO<Member> {
	
	private Connection conn;
	private PreparedStatement ps;
	private ResultSet rs;

	@Override
	public void insert(Member member) {
		
		String sql = "INSERT INTO `member` (mName, mAccount, mPassword, mPhone, mEmail) VALUES (?,?,?,?,?);";
		
		try {

			conn = JDBCUtils.getConnection();
			ps = conn.prepareStatement(sql);
			
			ps.setObject(1, member.getName());
			ps.setObject(2, member.getAccount());
			ps.setObject(3, member.getPassword());
			ps.setObject(4, member.getPhone());
			ps.setObject(5, member.getEmail());
			
			ps.executeUpdate();
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		} finally {
			
			JDBCUtils.closeConnection(conn, ps, null);
		}		
		
	}
	
	@Override
	public void delete(Member t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Member member) {
		
		String sql = "UPDATE `member` SET mName=?, mPassword=?, mPhone=?, mEmail=?, mPicture=? WHERE mAccount=?;";
		conn = JDBCUtils.getConnection();
		
		try {
			ps = conn.prepareStatement(sql);
			
			ps.setObject(1, member.getName());
			ps.setObject(2, member.getPassword());
			ps.setObject(3, member.getPhone());
			ps.setObject(4, member.getEmail());
			ps.setObject(5, member.getPicture());
			
			// set picture
			byte[] pic;
			try {
				pic = Base64.getDecoder().decode(member.getPicture().getBytes("UTF-8"));
				InputStream is = new ByteArrayInputStream(pic);
				
				// set blob: utf8mb3 -> utf8mb4
				// setByte may fix the problem?
				ps.setBlob(6, is);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			
			ps.executeUpdate();
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		} finally {
			
			JDBCUtils.closeConnection(conn, ps, null);
		}
	}

	@Override
	public List<Member> getAll() {
		
		String sql = "SELECT mId id,mName name,mAccount account,mPassword password,mPhone phone,mEmail email FROM `member`;";
		
		try {
			
			conn = JDBCUtils.getConnection();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();	
			
			List<Member> memberList = new ArrayList<>();
			
			while(rs.next()) {
				
				Member member = new Member();
				for(int i = 0; i < rsmd.getColumnCount(); i++) {
					
					Object value = rs.getObject(i + 1);
					String colLable = rsmd.getColumnLabel(i + 1);
					
					Field field = Member.class.getDeclaredField(colLable);
					field.setAccessible(true);
					field.set(member, value);
				}
				memberList.add(member);
			}
			return memberList;
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			
			e.printStackTrace();
		} catch (SecurityException e) {
			
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			
			e.printStackTrace();
		} finally {
			
			JDBCUtils.closeConnection(conn, ps, rs);
		}
		
		return null;
	}

	@Override
	public Member getByKeyword(String account) {
		
		String sql = "SELECT mId id,mName name,mAccount account,mPassword password,mPhone phone,mEmail email,mPicture picture FROM `member` WHERE mAccount=?;";
		
		try {
			
			conn = JDBCUtils.getConnection();
			ps = conn.prepareStatement(sql);
	
			ps.setObject(1, account);		
			rs = ps.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();	
			
			if(rs.next()) {
				
				Member member = new Member();
				for(int i = 0; i < rsmd.getColumnCount(); i++) {
					
					String colLabel = rsmd.getColumnLabel(i + 1);
					
					Field field = Member.class.getDeclaredField(colLabel);
					field.setAccessible(true);
					Object value = rs.getObject(i + 1);
					field.set(member, value);
					
					/*
					 * Get blob pic from db.
					 * 
					if (colLabel.equalsIgnoreCase("picture")) {
						Blob value = rs.getBlob(i + 1);
						if(value == null) break;
						InputStream is = value.getBinaryStream();
						byte[] bt;
						
						try {
							bt = is.readAllBytes();
							byte[] encode = Base64.getEncoder().encode(bt);
							String encodedString = new String(encode, "UTF-8");
							field.set(member, encodedString);
						} catch (IOException e) {
							e.printStackTrace();
						}
						
					} else {						
						Object value = rs.getObject(i + 1);
						field.set(member, value);
					}
					*/
				}
				return member;
			}
		} catch (SQLException|NoSuchFieldException|SecurityException|IllegalAccessException e) {
			
			e.printStackTrace();
		} finally {
			
			JDBCUtils.closeConnection(conn, ps, rs);
		}
		
		return null;
	}

	@Override
	public List<Member> getByKeywordLike(String account) {
		// TODO Auto-generated method stub
		return null;
	}

}
