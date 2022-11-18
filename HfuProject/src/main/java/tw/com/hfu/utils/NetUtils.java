package tw.com.hfu.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;


public class NetUtils {
	
	public static String getRequestAction(String requestPathInfo) {
		
		if(requestPathInfo != null) {
			if(requestPathInfo.split("/").length != 0) {
				return requestPathInfo.split("/")[1];
			}
		}
		return null;	
	}
	
	/**
	 * Get request data from request.getInputStream. and turn it into String.
	 * 
	 * @param requestInputStream
	 * @return String
	 */
	public static String getRequestBody(InputStream requestInputStream) {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(requestInputStream));
		StringBuilder sb = new StringBuilder();
		
		String line = null;
		
		try {
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return sb.toString();
	}
	
	/**
	 * Send Response with outputStream
	 * @param responseData
	 * @param responseOutputStream
	 */
	
	public static void sendResponse(String responseData, OutputStream responseOutputStream) {
		
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(responseOutputStream));
		try {
			bw.write(responseData);
			bw.flush();
			bw.close();
		} catch (IOException e) {

			e.printStackTrace();
		}
	}
}
