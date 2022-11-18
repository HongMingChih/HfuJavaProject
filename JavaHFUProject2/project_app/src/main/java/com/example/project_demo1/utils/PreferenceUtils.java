package com.example.project_demo1.utils;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.example.project_demo1.MainActivity;
import com.example.project_demo1.MyApplication;
import com.example.project_demo1.entity.Member;

public class PreferenceUtils {

    private final String KEY = "my_key";
    private final String SESSION_KEY = "LOGIN_SESSION_DATA";
    private final String MEMBER_NAME_KEY = "MEMBER_NAME";
    private final String MEMBER_ACCOUNT_KEY = "MEMBER_ACCOUNT";
    private final String MEMBER_PICTURE_KEY = "MEMBER_PICTURE";
    private SharedPreferences sharedPreferences;

    public PreferenceUtils () {

        this.sharedPreferences = MyApplication.getPrefs();
        // this.sharedPreferences = MainActivity.getMainActivityContext().getSharedPreferences(KEY, Context.MODE_PRIVATE);
    }

    /**
     * Store user data and session.
     * @param session
     * @param member
     */
    public void setLoggedMemberInfo(String session, Member member) {

        sharedPreferences.edit()
                .putString(SESSION_KEY, session)
                .putString(MEMBER_NAME_KEY, member.getName())
                .putString(MEMBER_ACCOUNT_KEY, member.getAccount())
                .putString(MEMBER_PICTURE_KEY, member.getPicture())
                .commit();
    }

    public String getSession() {
        return sharedPreferences.getString(SESSION_KEY, "");
    }

    public String getMemberName() {return sharedPreferences.getString(MEMBER_NAME_KEY, "");}

    public String getMemberAccount() {return sharedPreferences.getString(MEMBER_ACCOUNT_KEY, "");}

    public String getMemberPicture() {return sharedPreferences.getString(MEMBER_PICTURE_KEY, "");}

    /**
     * Clear user info and session while logout.
     */
    public void clearLoggedInfo() {
        sharedPreferences.edit()
                .putString(SESSION_KEY, "")
                .putString(MEMBER_NAME_KEY, "")
                .putString(MEMBER_ACCOUNT_KEY,"")
                .putString(MEMBER_PICTURE_KEY, "")
                .commit();
    }

    public void setPicture(String encPic) {
        sharedPreferences.edit().putString(MEMBER_PICTURE_KEY, encPic).commit();
    }
}
