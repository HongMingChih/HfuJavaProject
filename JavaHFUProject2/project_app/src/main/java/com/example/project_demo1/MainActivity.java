package com.example.project_demo1;



import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.graphics.Bitmap;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.project_demo1.utils.MemberUtils;
import com.example.project_demo1.utils.PictureUtils;
import com.example.project_demo1.utils.PreferenceUtils;
import com.google.android.material.navigation.NavigationView;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {

    private static final String TAG ="MainActivity";
    private AppBarConfiguration mAppBarConfiguration;
    private NavController navController;
    private ExecutorService executorService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: START");
        setContentView(R.layout.activity_main);
        executorService = MyApplication.getExecutorService();

        /* ---------- Setup Actionbar ---------- */
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /* ---------- Basic setup for Drawer Navigation view with Jetpack Navigation component ---------- */
        // 1. bind controller with sidebar(navigation view)
        //      now the menu in navigation view links to nav controller, but still not affect the action bar
        navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationView navigationView = findViewById(R.id.nav_view);
        NavigationUI.setupWithNavController(navigationView, navController);

        // 2. bind sidebar to actionbar
        //      NavigationUI uses an AppBarConfiguration to manage the behavior of the Navigation button in the upper-left
        //      corner of your app's display area.
        DrawerLayout drawer_layout = findViewById(R.id.drawer_layout);

        // setup actionbar with top destination page
        // top-level destination: nav_homeFragment, nav_loginFragment, mav_myProfileFragment
        mAppBarConfiguration = new AppBarConfiguration.Builder(R.id.nav_homeFragment, R.id.nav_loginFragment, R.id.nav_myProfileFragment, R.id.nav_settingsFragment, R.id.nav_logoutDialogFragment)
                .setOpenableLayout(drawer_layout)
                .build();

        // setup actionbar with NavController
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);


        /* ---------- Setup Search View ---------- */
        // get SearchView
        SearchView searchView = findViewById(R.id.search_view);

        // set submit Listener: navigate and pass the value
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                NavDestination navDestination = navController.getCurrentDestination();
                Bundle bundle = new Bundle();
                bundle.putString("query", query);

                // navigation:
                //      if in home page, home -> result
                //      if in result, result -> result (pop the backstack!)
                if(navDestination.getId() == R.id.nav_homeFragment) {

                    navController.navigate(R.id.action_nav_homeFragment_to_searchResultFragment, bundle);
                    return false;
                }

                navController.navigate(R.id.action_searchResultFragment_self, bundle);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        /* ------- if has logged, initialize navigation view in logged menu ------- */

        if(MemberUtils.hasLogged()) {

            PreferenceUtils prefs = new PreferenceUtils();
            // get header of navigation view
            View navHeader = navigationView.getHeaderView(0);
            ImageView nav_header_picture =  navHeader.findViewById(R.id.nav_header_picture);
            TextView nav_header_text = navHeader.findViewById(R.id.nav_header_text);

            nav_header_text.setText("您好，" + prefs.getMemberName());

            // download pictures from internet
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    // show progress bar while loading the picture
                    navHeader.findViewById(R.id.nav_header_pb).setVisibility(View.VISIBLE);
                    String memberPicSrc = prefs.getMemberPicture();
                    Bitmap memberPic = new PictureUtils().downloadImgAsBitmap(memberPicSrc);

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            nav_header_picture.setImageBitmap(memberPic);

                            // hide progress bar when finish download
                            navHeader.findViewById(R.id.nav_header_pb).setVisibility(View.GONE);

                        }
                    });
                }
            });

            // set navigation menu
            navigationView.getMenu().findItem(R.id.nav_loginFragment).setVisible(false);
            navigationView.getMenu().findItem(R.id.nav_logoutDialogFragment).setVisible(true);
        }
    }


    // Jetpack Navigation Component: hamburger and back arrow button
    @Override
    public boolean onSupportNavigateUp() {

        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

}