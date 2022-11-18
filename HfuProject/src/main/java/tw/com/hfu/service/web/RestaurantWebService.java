package tw.com.hfu.service.web;

import java.util.List;

import tw.com.hfu.dao.web.BaseDao;
import tw.com.hfu.entity.Restaurant;

public class RestaurantWebService {
	
	private BaseDao<Restaurant> dao;
	
	public RestaurantWebService(BaseDao<Restaurant> restaurantDAOImpl) {
		
		this.dao = restaurantDAOImpl;
	}
	
	/**
	 * Get all restaurants.
	 * 
	 * @return List of all restaurants.
	 */
	public List<Restaurant> getAllRestaurant() {	
		
		return dao.queryAll();
	}
}
