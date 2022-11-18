package com.example.project_demo1.utils;

import com.google.gson.Gson;

import java.util.Map;

public class RestaurantUtils {

    private HttpUtils httpUtils = new HttpUtils();

    public RestaurantUtils() {

    }

    /**
     * Search restaurants with keyword.
     * @param query: restaurant name.
     * @return response body with the correspond restaurant info.
     */
    public Map<String, String> search(String query) {

        String json = new Gson().toJson(query, String.class);
        return httpUtils.doGETRequest(json, "RestaurantController/search");
    }

    /**
     * Fetch the latest restaurants and show them on homepage.
     * @return response body from the server.
     */
    public Map<String, String> getLatest() {
        return httpUtils.doGETRequest("", "RestaurantController/latest");
    }
}
