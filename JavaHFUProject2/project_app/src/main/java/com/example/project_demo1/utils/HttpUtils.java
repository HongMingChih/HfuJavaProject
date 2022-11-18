package com.example.project_demo1.utils;

import android.text.TextUtils;
import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.HttpCookie;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HttpUtils {

    private static final String TAG = "HttpUtils";
    // private final String baseUrl = "http://10.0.2.2:8080/android_server/";
    private final String baseUrl = "http://10.0.2.2:8080/HfuProject/";

    // cookie manager:
    private static final CookieManager cookieManager = new CookieManager();
    public static String getCurrentCookie() {
        return TextUtils.join(";", cookieManager.getCookieStore().getCookies());
    }

    // preference utils
    PreferenceUtils preferenceUtils = new PreferenceUtils();

    private HttpURLConnection httpConn;
    private Map<String, String> response;

    /**
     * Http Get Method
     * @param json: request params(in json form)
     * @param actionPath
     * @return response body from server.
     */
    // GET Request
    public Map<String, String> doGETRequest(String json, String actionPath) {

        try {
            // encode json to url type
            String encodedJson = URLEncoder.encode(json, "UTF-8");
            URL queryUrl = new URL(baseUrl + actionPath + "?" + encodedJson);
            httpConn = (HttpURLConnection)queryUrl.openConnection();

            /* -------------- Request Configuration -------------- */
            // request method
            httpConn.setRequestMethod("GET");

            // request property
            httpConn.setRequestProperty( "Content-Type", "application/json");
            httpConn.setRequestProperty( "charset", "utf-8");
            httpConn.setRequestProperty("Accept", "application/json");
            httpConn.setDoInput(true);

            // set stored session id as cookie
            String storedSessionId = preferenceUtils.getSession();
            if(!storedSessionId.isEmpty()) {
                httpConn.setRequestProperty("Cookie", storedSessionId);
            }

            httpConn.connect();

            // get response
            BufferedReader br ;
            int statusCode = httpConn.getResponseCode();
            Log.d(TAG, "doGETRequest: response statusCode = " + statusCode);
            if(statusCode >= 400) {
                br = new BufferedReader(new InputStreamReader(httpConn.getErrorStream()));
            } else {
                br = new BufferedReader(new InputStreamReader(httpConn.getInputStream()));
            }

            StringBuilder responseBody = new StringBuilder();
            String line;
            while((line = br.readLine()) != null) {
                responseBody.append(line);
            }
            br.close();

            /* ---------- get session from server and store into cookie manager ---------- */
            // get cookies(session) from headerFields
            Map<String, List<String>> headerFields = httpConn.getHeaderFields();
            List<String> cookiesList = headerFields.get("Set-Cookie");

            // add cookies(session) to cookie manager
            if(cookiesList != null){
                for(String cookie : cookiesList) {
                    cookieManager.getCookieStore().add(null, HttpCookie.parse(cookie).get(0));
                }
            }
            Log.d("http utils: ", "session from server: " + getCurrentCookie());
            // return response code + body
            response = new HashMap<>();
            response.put("statusCode", String.valueOf(statusCode));
            response.put("responseBody", responseBody.toString());

            return response;

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (httpConn != null) {
                httpConn.disconnect();
            }
        }
        return null;
    }

    /**
     * Http POST Method
     * @param json request body.
     * @param actionPath
     */
    public Map<String, String> doPOSTRequest (String json, String actionPath){

        try {
            URL postURL = new URL(baseUrl + actionPath);
            httpConn = (HttpURLConnection)postURL.openConnection();

            // request method
            httpConn.setRequestMethod("POST");

            // request property
            httpConn.setRequestProperty( "Content-Type", "application/json");
            httpConn.setRequestProperty( "charset", "utf-8");
            httpConn.setRequestProperty("Accept", "application/json");
            httpConn.setDoInput(true);
            httpConn.setDoOutput(true);
            httpConn.setUseCaches(false);

            // send request body to server
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(httpConn.getOutputStream()));
            bw.write(json);
            bw.flush();
            bw.close();

            // connect to server
            httpConn.connect();

            // get response: if error, print error inputStream
            BufferedReader br ;
            int statusCode = httpConn.getResponseCode();

            if(statusCode >= 400) {
                br = new BufferedReader(new InputStreamReader(httpConn.getErrorStream()));
            } else {
                br = new BufferedReader(new InputStreamReader(httpConn.getInputStream()));
            }

            StringBuilder responseBody = new StringBuilder();
            String line;
            while((line = br.readLine()) != null) {
                responseBody.append(line);
            }
            br.close();


            /* ---------- get session from server and store into cookie manager ---------- */
            // get cookies(session) from headerFields
            Map<String, List<String>> headerFields = httpConn.getHeaderFields();
            List<String> cookiesList = headerFields.get("Set-Cookie");

            // add cookies(session) to cookie manager
            if(cookiesList != null){
                for(String cookie : cookiesList) {
                    cookieManager.getCookieStore().add(null, HttpCookie.parse(cookie).get(0));
                }
            }
            Log.d("http utils: ", "session from server: " + getCurrentCookie());

            // return response code + body
            response = new HashMap<>();
            response.put("statusCode", String.valueOf(statusCode));
            response.put("responseBody", responseBody.toString());

            return response;

        } catch (IOException e) {
            e.printStackTrace();
        } finally {

            if(httpConn != null) {
                httpConn.disconnect();
            }

        }
        return null;
    }
}
