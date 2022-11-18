package tw.com.hfu.dao.android.impl;


import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import tw.com.hfu.dao.android.BaseDAO;
import tw.com.hfu.entity.Restaurant;
import tw.com.hfu.utils.JDBCUtils;

public class RestaurantDAOImpl implements BaseDAO<Restaurant> {
	
	private String sql;
	private Connection conn;
	private PreparedStatement ps;
	private ResultSet rs;

	@Override
	public void insert(Restaurant restaurant) {
		
		sql = "INSERT INTO `restaurant` (rName, rCategory, rPhone, rAddress, rDescription) VALUES (?,?,?,?,?);";
		
		try {
			conn = JDBCUtils.getConnection();
			ps = conn.prepareStatement(sql);
			
			ps.setObject(1, restaurant.getName());
			ps.setObject(2, restaurant.getCategory());
			ps.setObject(3, restaurant.getPhone());
			ps.setObject(4, restaurant.getAddress());
			ps.setObject(5, restaurant.getDescription());
			
			ps.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCUtils.closeConnection(conn, ps, rs);
		}
	}
	
	@Override
	public void delete(Restaurant t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Restaurant t) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public List<Restaurant> getAll() {
		
		sql = "SELECT rId id,rName name,rCategory category,rPhone phone,rAddress address,rDescription description,rPicture picture FROM `restaurant`;";
			
		try {
			
			conn = JDBCUtils.getConnection();
			ps = conn.prepareStatement(sql);			
			rs = ps.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();	
			
			List<Restaurant> restaurantList = new ArrayList<>();
			while(rs.next()) {
				
				Restaurant restaurant = new Restaurant();
				for(int i = 0; i < rsmd.getColumnCount(); i++) {
					
					String colLabel = rsmd.getColumnLabel(i + 1);
					
					/*
					 *  Fetch picture in String url
					 */
					Field field = Restaurant.class.getDeclaredField(colLabel);
					field.setAccessible(true);
					Object value = rs.getObject(i + 1);
					field.set(restaurant, value);
					
					/* 
					 * Fetch blob picture.
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
							field.set(restaurant, encodedString);
						} catch (IOException e) {
							e.printStackTrace();
						}
						
					} else {						
						Object value = rs.getObject(i + 1);
						field.set(restaurant, value);
					}
					*/
				}
				restaurantList.add(restaurant);				
			}
			return restaurantList;
			
		} catch (SQLException|NoSuchFieldException|SecurityException|IllegalAccessException e) {
		
			e.printStackTrace();
		} finally {
			
			JDBCUtils.closeConnection(conn, ps, rs);
		}	
		return null;
	}

	@Override
	public Restaurant getByKeyword(String category) {
		
		return null;
	}

	@Override
	public List<Restaurant> getByKeywordLike(String restaurantNameOrCate) {
		
		sql = "SELECT rId id,rName name,rCategory category,rPhone phone,rAddress address,rDescription description,rPicture picture FROM `restaurant` WHERE rName LIKE ? OR rCategory LIKE ?;";
		
		try {
			
			conn = JDBCUtils.getConnection();
			ps = conn.prepareStatement(sql);
	
			ps.setObject(1, "%" + restaurantNameOrCate + "%");
			ps.setObject(2, restaurantNameOrCate);
			
			rs = ps.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();	
			
			List<Restaurant> restaurantList = new ArrayList<>();
			while(rs.next()) {
				
				Restaurant restaurant = new Restaurant();
				for(int i = 0; i < rsmd.getColumnCount(); i++) {
					
					String colLabel = rsmd.getColumnLabel(i + 1);
					
					/*
					 * fetch picture in String url
					 */
					Field field = Restaurant.class.getDeclaredField(colLabel);
					field.setAccessible(true);
					Object value = rs.getObject(i + 1);
					field.set(restaurant, value);
					
					/* 
					 * fetch blob
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
							field.set(restaurant, encodedString);
						} catch (IOException e) {
							e.printStackTrace();
						}
						
					} else {						
						Object value = rs.getObject(i + 1);
						field.set(restaurant, value);
					}
					*/
				}
				restaurantList.add(restaurant);				
			}
			return restaurantList;
			
		} catch (SQLException|NoSuchFieldException|SecurityException|IllegalAccessException e) {
			
			e.printStackTrace();
		} finally {
			
			JDBCUtils.closeConnection(conn, ps, rs);
		}
		
		return null;
	}
	
}
