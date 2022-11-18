package com.example.project_demo1.ui.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.project_demo1.MainActivity;
import com.example.project_demo1.MyApplication;
import com.example.project_demo1.R;
import com.example.project_demo1.utils.MemberUtils;
import com.example.project_demo1.utils.PreferenceUtils;
import com.google.android.material.navigation.NavigationView;

import java.net.HttpURLConnection;
import java.util.Map;
import java.util.concurrent.ExecutorService;

public class LogoutDialogFragment extends DialogFragment {
    
    private static final String TAG = "LoginDialogFragment";
    private NavController navController;
    private ExecutorService executorService;
    private MainActivity mActivity;

    // Hold context to prevent getActivity == null
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mActivity = (MainActivity) getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView: ");
        View view = inflater.inflate(R.layout.fragment_login_dialog, container, false);
        executorService = MyApplication.getExecutorService();

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onViewCreated: ");
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onCreateDialog: ");

        navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("是否確定登出？")
                .setPositiveButton("登出", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        Log.d(TAG, "onClick: mActivity?" + mActivity.toString());
                        Log.d(TAG, "onClick: mActivity == getActivity?" + mActivity.equals(getActivity()));

                        // logout
                        executorService.execute(new Runnable() {
                            @Override
                            public void run() {

                                // request server to invalid the session
                                Map<String, String> response = new MemberUtils().logout();
                                int statusCode = Integer.parseInt(response.get("statusCode"));

                                if (statusCode == HttpURLConnection.HTTP_OK) {

                                    // clear the prefs
                                    new PreferenceUtils().clearLoggedInfo();

                                    // Update UI: Navigation view
                                    mActivity.runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {

                                            // clear Navigation header
                                            NavigationView navigationView = mActivity.findViewById(R.id.nav_view);
                                            View navHeader = navigationView.getHeaderView(0);
                                            ImageView nav_header_picture =  navHeader.findViewById(R.id.nav_header_picture);
                                            TextView nav_header_text = navHeader.findViewById(R.id.nav_header_text);
                                            ((TextView)mActivity.findViewById(R.id.nav_header_text)).setText("尚未登入");
                                            ((ImageView)mActivity.findViewById(R.id.nav_header_picture)).setImageResource(R.drawable.ic_launcher_foreground);

                                            // hide logout button on nav view
                                            navigationView.getMenu().findItem(R.id.nav_logoutDialogFragment).setVisible(false);

                                            // show login button on nav view
                                            navigationView.getMenu().findItem(R.id.nav_loginFragment).setVisible(true);
                                            Toast.makeText(mActivity, "已登出", Toast.LENGTH_SHORT).show();
                                            // back to homepage
                                            navController.navigateUp();
                                            // release memory to prevent leaking
                                            mActivity = null;

                                            return;
                                        }
                                    });
                                }
                            }
                        });
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
        
        return builder.create();
    }
}
