package com.example.project_demo1.ui.myprofile;

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import android.widget.TextView;
import android.widget.Toast;

import com.example.project_demo1.MainActivity;
import com.example.project_demo1.MyApplication;
import com.example.project_demo1.R;
import com.example.project_demo1.utils.MemberUtils;
import com.example.project_demo1.utils.PictureUtils;
import com.example.project_demo1.utils.PreferenceUtils;
import com.google.gson.Gson;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.Map;
import java.util.concurrent.ExecutorService;

public class MyProfileFragment extends Fragment {

    private static final String TAG = "MyProfileFragment";
    private NavController navController;
    private ActivityResultLauncher activityResultLauncher;
    private PreferenceUtils preferenceUtils = new PreferenceUtils();

    // executor service
    private ExecutorService executorService;

    public MyProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // get executor service
        executorService = MyApplication.getExecutorService();

        /* ----------- Open the gallery -----------*/

        activityResultLauncher = registerForActivityResult(new ActivityResultContracts.GetContent(), new ActivityResultCallback<Uri>() {
            @Override
            public void onActivityResult(Uri result) {

                if(result == null) return;

                try {
                    String encodedBase64 = new PictureUtils().compressContentUri(result, getActivity());

                    // upload to web

                    /*---------- connect to server --------- */

                    executorService.execute(new Runnable() {
                        @Override
                        public void run() {
                            System.out.println("member account: " + preferenceUtils.getMemberAccount());
                            Map<String, String> response = new MemberUtils().updatePic(preferenceUtils.getMemberAccount(), encodedBase64);
                            int statusCode = Integer.parseInt(response.get("statusCode"));
                            String responseBody = response.get("responseBody");
                            System.out.println(responseBody);

                            getActivity().runOnUiThread(new Runnable() {
                                @Override
                                public void run() {

                                    if(statusCode == HttpURLConnection.HTTP_OK) {

                                        // get new pic string from response body
                                        String updatedPic = new Gson().fromJson(responseBody, String.class);

                                        // update MemberData in preference
                                        preferenceUtils.setPicture(updatedPic);

                                        // update profile image
                                        ImageView iv_profile_picture = getActivity().findViewById(R.id.iv_profile_picture);
                                        iv_profile_picture.setImageBitmap(new PictureUtils().base64ToBitmap(preferenceUtils.getMemberPicture()));

                                        Toast.makeText(getActivity(), "已更新圖片", Toast.LENGTH_SHORT).show();

                                    } else if(statusCode == HttpURLConnection.HTTP_UNAUTHORIZED) {

                                        // forced to logout
                                        preferenceUtils.clearLoggedInfo();
                                        // jump to homepage
                                        navController.popBackStack();
                                        navController.navigateUp();

                                        Toast.makeText(getActivity(), "已逾時，請重新登入", Toast.LENGTH_SHORT).show();

                                    } else {
                                        Toast.makeText(getActivity(), "未知的錯誤", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_my_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        // get navController
        navController = Navigation.findNavController(view);

        // hide SearchView if visible
        SearchView searchView = getActivity().findViewById(R.id.search_view);
        if(searchView.getVisibility() == View.VISIBLE) {
            searchView.setVisibility(View.INVISIBLE);
        }

        // check isLogged: if false, hide views;
        if(!MemberUtils.hasLogged()){
            navController.navigate(R.id.action_nav_myProfileFragment_to_loginDialogFragment);
            view.setVisibility(View.INVISIBLE);
            return;
        }
        view.setVisibility(View.VISIBLE);

        /* --------- set member data to profile --------- */
        PreferenceUtils preferenceUtils = new PreferenceUtils();

        TextView tv_profile_name = view.findViewById(R.id.tv_profile_name);
        tv_profile_name.setText(preferenceUtils.getMemberName());

        TextView tv_profile_account = view.findViewById(R.id.tv_profile_account);
        tv_profile_account.setText(preferenceUtils.getMemberAccount());

        /* ------ set profile picture ------ */
        ImageView iv_profile_picture = view.findViewById(R.id.iv_profile_picture);
        // download picture
        executorService.execute(new Runnable() {
            @Override
            public void run() {

                Bitmap profilePic;
                if (preferenceUtils.getMemberPicture() != null) {
                    String src = preferenceUtils.getMemberPicture();
                    profilePic = new PictureUtils().downloadImgAsBitmap(src);
                } else {
                    profilePic = null;
                }

                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        iv_profile_picture.setImageBitmap(profilePic);
                    }
                });
            }
        });


        /* -------- update profile picture -------*/
        Button btn_profile_upload = view.findViewById(R.id.btn_profile_upload);
        btn_profile_upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                activityResultLauncher.launch("image/*");
          }
        });

        // logout button
        Button logout_btn = view.findViewById(R.id.logout_btn);
        logout_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                navController.navigate(R.id.action_nav_myProfileFragment_to_logoutDialogFragment);
            }
        });
    }
}