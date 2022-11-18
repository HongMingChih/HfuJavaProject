package com.example.project_demo1.ui.login;

import android.graphics.Bitmap;
import android.os.Bundle;

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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.project_demo1.MainActivity;
import com.example.project_demo1.MyApplication;
import com.example.project_demo1.R;
import com.example.project_demo1.entity.Member;
import com.example.project_demo1.utils.HttpUtils;
import com.example.project_demo1.utils.MemberUtils;
import com.example.project_demo1.utils.PictureUtils;
import com.example.project_demo1.utils.PreferenceUtils;
import com.google.android.material.navigation.NavigationView;
import com.google.gson.Gson;

import java.net.HttpURLConnection;
import java.util.Map;
import java.util.concurrent.ExecutorService;

public class LoginFragment extends Fragment{

    private static final String TAG = "LoginFragment";

    // executor service from Activity
    private ExecutorService executorService;

    private Button login_btn;
    private Button registration_btn;
    private NavController navController;
    private SearchView searchView;

    public LoginFragment() {
        // Required empty public constructor
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
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // get nav controller
        navController = Navigation.findNavController(view);

        // hide SearchView if visible
        searchView = getActivity().findViewById(R.id.search_view);
        if(searchView.getVisibility() == View.VISIBLE) {
            searchView.setVisibility(View.INVISIBLE);
        }

        // setup login and registration buttons
        login_btn = view.findViewById(R.id.login_btn);
        registration_btn = view.findViewById(R.id.registration_btn);

        // login button
        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // get user input
                String inputAccount = ((EditText)view.findViewById(R.id.et_login_account)).getText().toString().trim();
                String inputPassword = ((EditText)view.findViewById(R.id.et_login_password)).getText().toString().trim();

                TextView login_err_msg = view.findViewById(R.id.login_err_msg);
                if(inputAccount.isEmpty() || inputPassword.isEmpty()) {
                    login_err_msg.setVisibility(View.VISIBLE);
                    login_err_msg.setText("輸入的帳號或密碼不得為空");
                    login_err_msg.setTextColor(getResources().getColor(R.color.text_warning_red));
                    return;
                }

                /* -------------- start Server Connection on Another Thread -------------- */
                executorService.execute(new Runnable() {
                    @Override
                    public void run() {

                        // get response from server
                        Map<String, String> response = new MemberUtils().login(inputAccount, inputPassword);
                        int statusCode = Integer.parseInt(response.get("statusCode"));
                        String responseBody = response.get("responseBody");

                        PreferenceUtils prefUtils = new PreferenceUtils();

                        // 200: success; 400: wrong acc or pwd; otherwise: unknown error.
                        if (statusCode == HttpURLConnection.HTTP_OK) {

                            // get member data
                            Member memberData = new Gson().fromJson(responseBody, Member.class);

                            // if member picture is not null, download picture
                            String memberPicSrc = memberData.getPicture();
                            Bitmap memberPic;
                            if(memberPicSrc != null) {
                                 memberPic = new PictureUtils().downloadImgAsBitmap(memberPicSrc);
                            } else {
                                 memberPic = null;
                            }

                            // SharedPreferences: save session and member data
                            prefUtils.setLoggedMemberInfo(HttpUtils.getCurrentCookie(), memberData);

                            /* ------ update UI thread ------ */
                            // getActivity == null: means  already leave login page, thus fragment detach and cause null;
                            if (getActivity() == null) return;

                            getActivity().runOnUiThread(new Runnable() {
                                @Override
                                public void run() {

                                    /* ---------- update NavigationView header ----------- */
                                    TextView nav_header_text = getActivity().findViewById(R.id.nav_header_text);
                                    nav_header_text.setText("您好，" + memberData.getName());

                                    ImageView nav_header_picture = getActivity().findViewById(R.id.nav_header_picture);
                                    // nav_header_picture.setImageBitmap(new PictureUtils().base64ToBitmap(prefUtils.getMemberPicture()));
                                    if (memberPic != null ) nav_header_picture.setImageBitmap(memberPic);

                                    /* ----- update Nav view menu: show logout button and hide login button ----- */
                                    NavigationView navigationView = getActivity().findViewById(R.id.nav_view);
                                    navigationView.getMenu().findItem(R.id.nav_logoutDialogFragment).setVisible(true);
                                    navigationView.getMenu().findItem(R.id.nav_loginFragment).setVisible(false);

                                    /* ---------- show message and navigate up ----------- */
                                    Toast.makeText(getActivity(), "登入成功", Toast.LENGTH_SHORT).show();
                                    navController.navigateUp();

                                }
                            });

                        } else if (statusCode == HttpURLConnection.HTTP_BAD_REQUEST) {

                            /* ------ update UI thread ------ */
                            if (getActivity() == null) return;
                            getActivity().runOnUiThread(new Runnable() {
                                @Override
                                public void run() {

                                    login_err_msg.setVisibility(View.VISIBLE);
                                    login_err_msg.setText("帳號或密碼錯誤");
                                    login_err_msg.setTextColor(getResources().getColor(R.color.text_warning_red));
                                    return;

                                }
                            });

                        } else {

                            login_err_msg.setVisibility(View.VISIBLE);
                            login_err_msg.setText("暫時無法登入，請稍後再試");
                            login_err_msg.setTextColor(getResources().getColor(R.color.text_warning_red));
                            return;
                        }
                    }
                });
            }
        });

        // Registration button
        registration_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_nav_loginFragment_to_registrationInfoFragment);
            }
        });
    }
}