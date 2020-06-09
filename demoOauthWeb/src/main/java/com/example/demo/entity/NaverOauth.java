package com.example.demo.entity;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class NaverOauth {
	@Value("${naver.client_id}")
	private String client_id;
	@Value("${naver.client_secret}")
	private String client_secret;
	
	@Value("${naver.codeBaseUrl}")
	private String codeBaseUrl;
	@Value("${naver.accessTokenBaseUrl}")
	private String accessTokenBaseUrl;
	@Value("${naver.redirect_uri}")
	private String redirect_uri;
	@Value("${naver.userInfoBaseUrl}")
	private String userInfoBaseUrl;
	
	@Value("${naver.response_type}")
	private String response_type;
	@Value("${naver.response_type}")
	private String grant_type;
	
	private String code;
	private String state;
	private String refresh_token;
	private String access_token;
	private String service_provider;
	
	private String resultcode;
	private String message;
	private Map<String, Object> response;
	
	public String getUserInfoBaseUrl() {
		return userInfoBaseUrl;
	}

	public void setUserInfoBaseUrl(String userInfoBaseUrl) {
		this.userInfoBaseUrl = userInfoBaseUrl;
	}

	public String getResultcode() {
		return resultcode;
	}

	public void setResultcode(String resultcode) {
		this.resultcode = resultcode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Map<String, Object> getResponse() {
		return response;
	}

	public void setResponse(Map<String, Object> response) {
		this.response = response;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getRefresh_token() {
		return refresh_token;
	}

	public void setRefresh_token(String refresh_token) {
		this.refresh_token = refresh_token;
	}

	public String getAccess_token() {
		return access_token;
	}

	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}

	public String getService_provider() {
		return service_provider;
	}

	public void setService_provider(String service_provider) {
		this.service_provider = service_provider;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getGrant_type() {
		return grant_type;
	}

	public void setGrant_type(String grant_type) {
		this.grant_type = grant_type;
	}

	public String getClient_id() {
		return client_id;
	}

	public void setClient_id(String client_id) {
		this.client_id = client_id;
	}

	public String getClient_secret() {
		return client_secret;
	}

	public void setClient_secret(String client_secret) {
		this.client_secret = client_secret;
	}

	public String getCodeBaseUrl() {
		return codeBaseUrl;
	}

	public void setCodeBaseUrl(String codeBaseUrl) {
		this.codeBaseUrl = codeBaseUrl;
	}

	public String getAccessTokenBaseUrl() {
		return accessTokenBaseUrl;
	}

	public void setAccessTokenBaseUrl(String accessTokenBaseUrl) {
		this.accessTokenBaseUrl = accessTokenBaseUrl;
	}

	public String getRedirect_uri() {
		return redirect_uri;
	}

	public void setRedirect_uri(String redirect_uri) {
		this.redirect_uri = redirect_uri;
	}

	public String getResponse_type() {
		return response_type;
	}

	public void setResponse_type(String response_type) {
		this.response_type = response_type;
	}

	public Map<String, String> getOauthCodeParmas() {
		Map<String, String> params = new HashMap<String, String>();
		params.put("response_type", getResponse_type());
		params.put("redirect_uri", getRedirect_uri());
		params.put("client_id", getClient_id());
		return params;
	}
	
	public Map<String, String> getOauthTokenParmas() {
		Map<String, String> params = new HashMap<String, String>();
		params.put("grant_type", getGrant_type());
		params.put("code", getCode());
		params.put("client_id", getClient_id());
		params.put("client_secret", getClient_secret());
		return params;
	}
	
	public Map<String, String> getOauthUserInfoParmas() {
		Map<String, String> params = new HashMap<String, String>();
		params.put("access_token", getAccess_token());
		return params;
	} 
}
