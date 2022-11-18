package com.example.project_demo1.ui.home;

import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.project_demo1.MyApplication;
import com.example.project_demo1.R;
import com.example.project_demo1.adapter.SliderAdapter;
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

public class HomeFragment extends Fragment implements View.OnClickListener{

    private static final String TAG = "HomeFragment";
    private NavController navController;
    // executor service from application
    private ExecutorService executorService;
    
    // fastQuery Text Item
    private CardView home_chinese, home_japanese, home_french, home_american, home_innovation;

    public HomeFragment() {
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
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // get nav controller
        navController = Navigation.findNavController(view);

        // Set SearchView as VISIBLE
        // NOTE:
        //     SearchView could be null sometimes, since applying Jetpack Navigation Component
        //     might lead some different life-cycle relationship between MainActivity and
        //     HomeFragment(The first attached fragment).
        SearchView searchView = getActivity().findViewById(R.id.search_view);
        if (searchView != null && searchView.getVisibility() == View.INVISIBLE) {
            searchView.setVisibility(View.VISIBLE);
        }

        /* -------- homepage: get latest restaurant on server ----------- */
        executorService.execute(new Runnable() {
            @Override
            public void run() {

                // get the response from server
                Map<String, String> response = new RestaurantUtils().getLatest();
                int statusCode = Integer.parseInt(response.get("statusCode"));
                String responseBody = response.get("responseBody");

                // check status code
                if(statusCode != HttpURLConnection.HTTP_OK) return;

                List<Restaurant> restaurantList =
                        new Gson().fromJson(responseBody, TypeToken.getParameterized(ArrayList.class, Restaurant.class).getType());

                if (restaurantList.size() == 0) return;

                // download pictures for all restaurant from List
                List<Bitmap> downloadedPicList =  new ArrayList<>();

                for(Restaurant restaurant : restaurantList) {
                    String picUrl = restaurant.getPicture();
                    Bitmap downloadPic = new PictureUtils().downloadImgAsBitmap(picUrl);
                    downloadedPicList.add(downloadPic);
                }

                // render on ui thread
                if(getActivity() == null) return;
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        // hide progress bar
                        view.findViewById(R.id.home_loading_pb).setVisibility(View.GONE);

                        // inflate view pager
                        ViewPager2 home_viewpager_latest = view.findViewById(R.id.home_viewpager_latest);
                        home_viewpager_latest.setAdapter(new SliderAdapter(restaurantList, navController, downloadedPicList));
                    }
                });
            }
        });

        /* ------ homepage: quick search by category  ------ */

        home_chinese = view.findViewById(R.id.home_chinese);
        home_japanese = view.findViewById(R.id.home_japanese);
        home_french = view.findViewById(R.id.home_french);
        home_american = view.findViewById(R.id.home_american);
        home_innovation = view.findViewById(R.id.home_innovation);
        
        home_chinese.setOnClickListener(this);
        home_japanese.setOnClickListener(this);
        home_french.setOnClickListener(this);
        home_american.setOnClickListener(this);
        home_innovation.setOnClickListener(this);
        
    }

    // quick search onClick method
    @Override
    public void onClick(View v) {

        Bundle bundle = new Bundle();

        if(v.equals(home_chinese)) {
            bundle.putString("query", "中式料理");
        }else if(v.equals(home_japanese)) {
            bundle.putString("query", "日式料理");
        }else if(v.equals(home_french)) {
            bundle.putString("query", "法式料理");
        }else if(v.equals(home_american)) {
            bundle.putString("query", "美式料理");
        }else if(v.equals(home_innovation)) {
            bundle.putString("query", "創新料理");
        }
        navController.navigate(R.id.action_nav_homeFragment_to_searchResultFragment, bundle);
    }
}