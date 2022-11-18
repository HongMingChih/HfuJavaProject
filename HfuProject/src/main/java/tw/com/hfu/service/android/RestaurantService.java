package tw.com.hfu.service.android;

import java.util.ArrayList;
import java.util.List;

import tw.com.hfu.dao.android.BaseDAO;
import tw.com.hfu.entity.Restaurant;

public class RestaurantService {
	
	private BaseDAO<Restaurant> dao;
	
	public RestaurantService(BaseDAO<Restaurant> RestaurantDAOImpl) {
		this.dao = RestaurantDAOImpl;
	}
	
	/* ----- Basics for android ----- */
	/**
	 * Get the lastest restaurant from db.
	 * @param count
	 * @return
	 */
	public List<Restaurant> getLatest(int count) {
		
		List<Restaurant> allList = dao.getAll();
		
		if(allList.size() <= 5) {
			return allList;
		}
		
		List<Restaurant> latestList = new ArrayList<>();
		for(int i = allList.size()-count; i < allList.size(); i++) {
			latestList.add(allList.get(i));
		}
		return latestList;
	}
	
	public List<Restaurant> searchResult(String restaurantName) {
		return dao.getByKeywordLike(restaurantName);
	}
	
	/**
	 * Insert new restaurant.
	 * @param restautant
	 */
	public void add(Restaurant restautant) {
		dao.insert(restautant);
	}
}
