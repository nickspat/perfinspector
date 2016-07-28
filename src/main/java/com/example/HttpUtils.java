package com.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.methods.GetMethod;
import org.springframework.stereotype.Component;

@Component
public class HttpUtils {
	
	public String getResponseTime(String stringurl){
		
		 // start timer
        long milliStart = System.currentTimeMillis();
        
		URL url;
		try {
			url = new URL(stringurl);
			URLConnection conn = url.openConnection();
	        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	        String content= in.readLine();
	        while (content  != null) {
	        	content= in.readLine();
	        	
	        }
	        in.close();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        
     // stop timer
        long milliEnd = System.currentTimeMillis();

        // report response times
        long milliTime = milliEnd - milliStart;
        
        //String nanoFormatted = String.format("%,.3f", nanoTime / NANO_TO_MILLIS);
        String milliFormatted = "Response Time: " + String.format("%,.3f", milliTime / 1.0 ) + " milliseconds \n";

        return milliFormatted;
        
	}
	
	public Map<String,String> getHttpResponse(String url){
		try {
			HttpClient client = new HttpClient();
			HttpMethod method = new GetMethod(url);
			int statusCode = client.executeMethod(method);
					
			StringBuilder builder = new StringBuilder();
			Header[] headers = method.getResponseHeaders();
			Map<String,String> responseHeaderMap = new HashMap<String,String>();
			for (Header header : headers) {
				responseHeaderMap.put(header.getName(), header.getValue());
				

			}
			return responseHeaderMap;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	

}
