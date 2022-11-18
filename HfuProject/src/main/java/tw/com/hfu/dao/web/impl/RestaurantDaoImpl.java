package tw.com.hfu.dao.web.impl;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import tw.com.hfu.entity.Restaurant;
import tw.com.hfu.utils.JDBCUtils;
import tw.com.hfu.dao.web.BaseDao;

public class RestaurantDaoImpl extends BaseDao<Restaurant> {
		
	@Override
	public void update(Restaurant restaurant) {
		
		Connection conn = null;
		PreparedStatement ps = null;
	
		try {
		
			conn = JDBCUtils.getConnection();
			String updateSQL = "update restaurant set rName=?,rDescription=?,rAddress=?,rCategory=?,rPicture=?,rPhone=? where rId=? ";
			ps = conn.prepareStatement(updateSQL);
			ps.setString(1, restaurant.getName());
			ps.setString(2, restaurant.getDescription());
			ps.setString(3, restaurant.getAddress());
			ps.setString(4, restaurant.getCategory());
			ps.setString(5, restaurant.getPicture());
			ps.setString(6, restaurant.getPhone());
			ps.setInt(7, restaurant.getId());
			ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			
			JDBCUtils.closeConnection(conn, ps, null);
		}
	}
	
	@Override
	public void delete(Integer rId) {
		
		Connection conn = null;
		PreparedStatement ps = null;
		
		try {
			
			conn = JDBCUtils.getConnection();
			String deleteSQL = "delete from restaurant where rId=?";
			ps = conn.prepareStatement(deleteSQL);
			ps.setInt(1, rId);
			ps.executeUpdate();
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		} finally {
			
			JDBCUtils.closeConnection(conn, ps, null);
		}
	}
	
	@Override
	public ArrayList<Restaurant> queryAll() {
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		ArrayList<Restaurant> lists = new ArrayList<>();
		
		try {
			
			String querySQL = "select rId,rName,rDescription,rPicture,rCategory,rAddress,rPhone from restaurant order by rId desc;";
			conn = JDBCUtils.getConnection();
			ps = conn.prepareStatement(querySQL);
			rs = ps.executeQuery();
			
			while (rs.next()) {
				
				Restaurant restaurant = new Restaurant();
				restaurant.setId(rs.getInt("rId"));
				restaurant.setName(rs.getString("rName"));
				restaurant.setDescription(rs.getString("rDescription"));
				restaurant.setPicture(rs.getString("rPicture"));
				restaurant.setCategory(rs.getString("rCategory"));
				restaurant.setAddress(rs.getString("rAddress"));
				restaurant.setPhone(rs.getString("rPhone"));
				
				lists.add(restaurant);
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		} finally {
			
			JDBCUtils.closeConnection(conn, ps, rs);
		}
		return lists;
	}
	
	@Override
	public ArrayList<Restaurant> query(String name) {
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		ArrayList<Restaurant> lists = new ArrayList<>();
		
		try {
			
			conn = JDBCUtils.getConnection();
			String querySQL = "select * from restaurant where rName like ?";
			ps = conn.prepareStatement(querySQL);
			ps.setString(1, "%" +name+ "%");
			rs = ps.executeQuery();
			
			while (rs.next()) {
				Restaurant restaurant = new Restaurant();
				restaurant.setId(rs.getInt("rId"));
				restaurant.setName(rs.getString("rName"));
				restaurant.setDescription(rs.getString("rDescription"));
				restaurant.setPicture(rs.getString("rPicture"));
				restaurant.setCategory(rs.getString("rCategory"));
				restaurant.setAddress(rs.getString("rAddress"));
				restaurant.setPhone(rs.getString("rPhone"));
				
				lists.add(restaurant);
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		} finally {
			
			JDBCUtils.closeConnection(conn, ps, rs);
		}
		return lists;
	}

	@Override
	public void add(Restaurant restaurant) {
		
		// 連接資料庫
		Connection conn = JDBCUtils.getConnection();
		PreparedStatement ps = null;
		
		try {
			
			// 新增ＳＱＬ語法
			String addSQL = "insert into restaurant(rName,rDescription,rPicture,rCategory,rAddress,rPhone)values(?,?,?,?,?,?);";
			// 傳送語法
			ps = conn.prepareStatement(addSQL);
			ps.setString(1, restaurant.getName());
			ps.setString(2, restaurant.getDescription());
			ps.setString(3, restaurant.getPicture());
			ps.setString(4, restaurant.getCategory());
			ps.setString(5, restaurant.getAddress());
			ps.setString(6, restaurant.getPhone());
			ps.execute();
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		} finally {
			
			JDBCUtils.closeConnection(conn, ps, null);
		}

	}
	@Override
	public ArrayList<Restaurant> querymenu(String cate) {
		
		Connection conn = JDBCUtils.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<Restaurant> lists = new ArrayList<>();
		
		try {
			
			String querySQL = "select * from restaurant where rCategory like ?";
			ps = conn.prepareStatement(querySQL);
			ps.setString(1, "%" +cate+ "%");
			rs = ps.executeQuery();
			
			while (rs.next()) {
				
				Restaurant restaurant = new Restaurant();
				restaurant.setId(rs.getInt("rId"));
				restaurant.setName(rs.getString("rName"));
				restaurant.setDescription(rs.getString("rDescription"));
				restaurant.setPicture(rs.getString("rPicture"));
				restaurant.setCategory(rs.getString("rCategory"));
				restaurant.setAddress(rs.getString("rAddress"));
				restaurant.setPhone(rs.getString("rPhone"));
				
				lists.add(restaurant);
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		} finally {
			JDBCUtils.closeConnection(conn, ps, rs);
		}
		return lists;
	}
}
