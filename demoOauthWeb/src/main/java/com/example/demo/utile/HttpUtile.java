package com.example.demo.utile;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Enumeration;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;

public class HttpUtile<T> {
	private String urlStr;
	private Map<String, String> params;
	private T entity;
	
	public HttpUtile() {}
	public HttpUtile(String urlStr) {
		this.urlStr = urlStr;
	}
	public HttpUtile(String urlStr, T entity) {
		this.urlStr = urlStr;
		this.entity = entity;
	}
	
	public String getHttpRequestExec() throws Exception{
		URL url = new URL(urlStr);
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		con.setRequestMethod("GET");
		
		con.setDoOutput(true);
		DataOutputStream out = new DataOutputStream(con.getOutputStream());
		
		out.writeBytes(getAllParam());
		out.flush();
		out.close();
		
		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer content = new StringBuffer();
		while ((inputLine = in.readLine()) != null) {
		    content.append(inputLine);
		}
		in.close();
		con.disconnect();
		
		return content.toString();
	}
	
	public URI getURI() throws URISyntaxException {
		URI uri = new URI(urlStr);
		return uri;
	}
	
	public HttpEntity<T> getHttpEntity() {
		HttpHeaders headers = new HttpHeaders();
	    headers.set("Content-Type", "application/json"); 
	    HttpEntity<T> httpEntity = new HttpEntity<>(entity, headers);
	    
		return httpEntity;
	}
	
	public String getUri() {
		return urlStr;
	}

	public String getAllParam() throws Exception {
		StringBuilder result = new StringBuilder();
		 
        for (Map.Entry<String, String> entry : params.entrySet()) {
          result.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
          result.append("=");
          result.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
          result.append("&");
        }
		return result.toString();
	}

	public void setParams(Map<String, String> params) {
		this.params = params;
	}
	
	public void checkParams(HttpServletRequest request) {
		Enumeration<String> params = request.getParameterNames();
		System.out.println("----------------------------");
		while (params.hasMoreElements()){
		    String name = (String)params.nextElement();
		    System.out.println(name + " : " +request.getParameter(name));
		}
		System.out.println("----------------------------");
	}
}
