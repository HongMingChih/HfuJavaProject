package tw.com.hfu.controller.android;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import tw.com.hfu.dao.android.impl.RestaurantDAOImpl;
import tw.com.hfu.entity.Restaurant;
import tw.com.hfu.service.android.RestaurantService;
import tw.com.hfu.utils.NetUtils;

@WebServlet("/RestaurantController/*")
public class RestaurantController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
		/* -------- get request query string --------- */
		String queryString = null;
		
		if(request.getQueryString() != null) {			
			String requestQueryString = URLDecoder.decode(request.getQueryString(), "UTF-8");
			queryString = new Gson().fromJson(requestQueryString, String.class).replaceAll("\"", "");
		}			
		
		/* ----- service ----- */
		RestaurantService service = new RestaurantService(new RestaurantDAOImpl());
		/* -------- get action path -------- */
		String pathInfo = request.getPathInfo();		
		String requestAction = NetUtils.getRequestAction(pathInfo);
		
		System.out.println("request Action = " + requestAction);
		
		if(requestAction == null) {
			return;
		}
		
		switch(requestAction) {
		
		case "search":
			
			List<Restaurant> searchResult = service.searchResult(queryString);
			String responseBody = new Gson().toJson(searchResult, TypeToken.getParameterized(ArrayList.class, Restaurant.class).getType());
			System.out.println("Search restaurant: " + responseBody);
			NetUtils.sendResponse(responseBody, response.getOutputStream());
			
			break;
			
		case "latest":
			
			// get latest restaurant
			int latestCount = 5;
			
			List<Restaurant> latestList = service.getLatest(latestCount);
			String responseBodyOfLatest = new Gson().toJson(latestList, TypeToken.getParameterized(ArrayList.class, Restaurant.class).getType());
			NetUtils.sendResponse(responseBodyOfLatest, response.getOutputStream());
			
			break;
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
