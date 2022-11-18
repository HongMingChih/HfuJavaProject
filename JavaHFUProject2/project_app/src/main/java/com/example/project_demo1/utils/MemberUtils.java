package com.example.project_demo1.utils;

import com.example.project_demo1.entity.Member;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

public class MemberUtils {

    private HttpUtils httpUtils = new HttpUtils();

    public MemberUtils() {

    }
    /**
     * member login
     *
     * check if the input account and password is correct.
     * Return member account and correspond session if correct.
     *
     * @param inputAccount: user's account.
     * @param inputPassword: user's password.
     * @return response in Map("statusCode", "responseBody")
     */
    public Map<String, String> login(String inputAccount, String inputPassword) {

        // send as map
        Map<String, String> data = new HashMap<>();
        data.put("inputAccount", inputAccount);
        data.put("inputPassword", inputPassword);
        String json = new Gson().toJson(data);
        System.out.println(json);

        return httpUtils.doPOSTRequest(json, "MemberController/login");
    }

    /**
     * logout from server
     */
    public Map<String, String> logout(){
        return httpUtils.doPOSTRequest("", "MemberController/logout");
    }

    /**
     * Registration
     *
     * @param newMember: new Member class that includes information of new user.
     */
    public Map<String, String> registration(Member newMember) {

        // transform into Json object
        String json = new Gson().toJson(newMember);
        return httpUtils.doPOSTRequest(json, "MemberController/registration");
    }

    /**
     * Check the current login status
     *
     * @return true if user login;
     */
    public static boolean hasLogged() {
        return !new PreferenceUtils().getSession().isEmpty();
    }

    public Map<String, String> updatePic(String memberAccount, String encPic) {

        Map<String, String> data = new HashMap<>();
        data.put("memberAccount", memberAccount);
        data.put("encPic", encPic);

        String json = new Gson().toJson(data, Map.class);

        System.out.println(json);

        return httpUtils.doPOSTRequest(json, "MemberController/updatePic");
    }
}



