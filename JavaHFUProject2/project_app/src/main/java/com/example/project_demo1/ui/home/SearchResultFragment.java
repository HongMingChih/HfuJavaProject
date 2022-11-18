package com.example.project_demo1.ui.home;

import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;


import com.example.project_demo1.MainActivity;
import com.example.project_demo1.MyApplication;
import com.example.project_demo1.R;
import com.example.project_demo1.adapter.SearchResultAdapter;
import com.example.project_demo1.entity.Restaurant;
import com.example.project_demo1.utils.PictureUtils;
import com.example.project_demo1.utils.RestaurantUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;

public class SearchResultFragment extends Fragment {

    private static final String TAG = "SearchResultFragment";
    private NavController navController;
    private ExecutorService executorService;

    public SearchResultFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        executorService = MyApplication.getExecutorService();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search_result, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // setup nav controller
        navController = Navigation.findNavController(view);

        // set SearchView visibility
        SearchView searchView = getActivity().findViewById(R.id.search_view);
        if (searchView.getVisibility() == View.INVISIBLE) {
            searchView.setVisibility(View.VISIBLE);
        }

        /* ----- search from server and database ----- */

        // input query
        String query = getArguments().getString("query");
        Log.d(TAG, "onViewCreated: " + query);
        String jsonQuery = new Gson().toJson(query, String.class);

        // send to server
        executorService.execute(new Runnable() {
            @Override
            public void run() {

                Map<String, String > response = new RestaurantUtils().search(jsonQuery);
                int statusCode = Integer.parseInt(response.get("statusCode"));
                String responseBody = response.get("responseBody");

                // get search result list(restaurant)
                List<Restaurant> restaurantList = new Gson().fromJson(responseBody, TypeToken.getParameterized(ArrayList.class, Restaurant.class).getType());

                // download picture first
                List<Bitmap> downloadedPicList = new ArrayList<>();
                for(Restaurant restaurant : restaurantList) {
                    String picUrl = restaurant.getPicture();
                    Bitmap downloadPic = new PictureUtils().downloadImgAsBitmap(picUrl);
                    downloadedPicList.add(downloadPic);
                }

                if(getActivity() == null) return;
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        if (statusCode == HttpURLConnection.HTTP_OK) {

                            // show views and hide progress bar
                            TextView search_result_notfound = view.findViewById(R.id.search_result_notfound);
                            RecyclerView recyclerView = view.findViewById(R.id.search_result_recyclerview);
                            view.findViewById(R.id.search_result_pb).setVisibility(View.GONE);

                            if (restaurantList != null && restaurantList.size() != 0) {

                                recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                                recyclerView.setAdapter(new SearchResultAdapter(restaurantList, downloadedPicList, navController));

                            } else {
                                // 麥
                                // restaurant not found, hide recycler view
                                search_result_notfound.setVisibility(View.VISIBLE);
                                // recyclerView.setVisibility(View.INVISIBLE);
                            }

                        } else if (statusCode == HttpURLConnection.HTTP_UNAUTHORIZED) {
                            Toast.makeText(getActivity(), "請重新登入", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getActivity(), "未知的錯誤：" + statusCode, Toast.LENGTH_SHORT).show();

                        }
                    }
                });
            }
        });


        // test button to info page
//        Button test_info_page_btn = view.findViewById(R.id.test_info_page_btn);
//        test_info_page_btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                navController.navigate(R.id.action_searchResultFragment_to_infoPageFragment);
//            }
//        });
    }

    void showOrHide(View view) {
        if (view.getVisibility() == View.VISIBLE) {
            view.setVisibility(View.INVISIBLE);
            return;
        } else {
            view.setVisibility(View.VISIBLE);
        }
    }
}