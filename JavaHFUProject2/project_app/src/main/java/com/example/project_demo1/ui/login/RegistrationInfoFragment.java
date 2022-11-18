package com.example.project_demo1.ui.login;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.project_demo1.MainActivity;
import com.example.project_demo1.MyApplication;
import com.example.project_demo1.R;
import com.example.project_demo1.entity.Member;
import com.example.project_demo1.utils.MemberUtils;

import java.net.HttpURLConnection;
import java.util.Map;
import java.util.concurrent.ExecutorService;

public class RegistrationInfoFragment extends Fragment {

    private static final String TAG = "RegistrationInfoFragment";
    // executor service from activity
    private ExecutorService executorService;

    public RegistrationInfoFragment() {
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
        return inflater.inflate(R.layout.fragment_registration_info, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        NavController navController = Navigation.findNavController(view);

        // set registration button
        Button reg_submit_btn = view.findViewById(R.id.reg_submit_btn);
        reg_submit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // registered info
                String name = ((EditText)view.findViewById(R.id.et_name)).getText().toString();
                String account = ((EditText)view.findViewById(R.id.et_account)).getText().toString();
                String password = ((EditText)view.findViewById(R.id.et_password)).getText().toString();
                String phone = ((EditText)view.findViewById(R.id.et_phone)).getText().toString();
                String email = ((EditText)view.findViewById(R.id.et_email)).getText().toString();

                // input check
                boolean reject = false;

                if (name.isEmpty()) {
                    TextView msg = view.findViewById(R.id.regInput_name_msg);
                    msg.setVisibility(View.VISIBLE);
                    msg.setTextColor(getResources().getColor(R.color.text_warning_red));
                    reject = true;
                }

                if (account.isEmpty() || account.length() < 6) {
                    TextView msg = view.findViewById(R.id.regInput_account_msg);
                    msg.setTextColor(getResources().getColor(R.color.text_warning_red));
                    reject = true;
                }

                if (password.isEmpty() || password.length() < 6) {
                    TextView msg = view.findViewById(R.id.regInput_password_msg);
                    msg.setTextColor(getResources().getColor(R.color.text_warning_red));
                    reject = true;
                }

                if (phone.isEmpty()) {
                    TextView msg = view.findViewById(R.id.regInput_phone_msg);
                    msg.setVisibility(View.VISIBLE);
                    msg.setTextColor(getResources().getColor(R.color.text_warning_red));
                    reject = true;
                }

                if (email.isEmpty() || !email.contains("@")) {
                    TextView msg = view.findViewById(R.id.regInput_email_msg);
                    msg.setVisibility(View.VISIBLE);
                    msg.setTextColor(getResources().getColor(R.color.text_warning_red));
                    reject = true;
                }

                if (reject) return;

                Member newMember = new Member(name, account, password, phone, email);

                /* -------------- Server Connection on Another Thread -------------- */
                executorService.execute(new Runnable() {
                    @Override
                    public void run() {

                        Map<String, String> response = new MemberUtils().registration(newMember);
                        int statusCode = Integer.parseInt(response.get("statusCode"));

                        // update ui thread
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {

                                if(statusCode == HttpURLConnection.HTTP_CONFLICT) {
                                    Toast.makeText(getActivity(), "此帳號已被註冊", Toast.LENGTH_SHORT).show();
                                    return;
                                }

                                navController.navigate(R.id.action_registrationInfoFragment_to_registrationDoneFragment);
                            }
                        });
                    }
                });
            }
        });
    }
}