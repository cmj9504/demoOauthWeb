package com.example.demo.entity;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class KakaoOauth {
	@Value("${kakao.client_id}")
	private String client_id;
	@Value("${kakao.client_secret}")
	private String client_secret;
	
	@Value("${kakao.codeBaseUrl}")
	private String codeBaseUrl;
	@Value("${kakao.accessTokenBaseUrl}")
	private String accessTokenBaseUrl;
	@Value("${kakao.userInfoBaseUrl}")
	private String userInfoBaseUrl;
	@Value("${kakao.redirect_uri}")
	private String redirect_uri;
	
	@Value("${kakao.response_type}")
	private String response_type;
	@Value("${kakao.grant_type}")
	private String grant_type;
	
	private String expires_in;
	private String token_type;
	private String scope;
	private String code;
	private String state;
	private String refresh_token;
	private String access_token;
	private String service_provider;
	private String refresh_token_expires_in;
	
	private Map<String, Object> properties;
	private Map<String, Object> kakao_account;

	
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
	
	public String getUserInfoBaseUrl() {
		return userInfoBaseUrl;
	}

	public void setUserInfoBaseUrl(String userInfoBaseUrl) {
		this.userInfoBaseUrl = userInfoBaseUrl;
	}

	public Map<String, Object> getProperties() {
		return properties;
	}

	public void setProperties(Map<String, Object> properties) {
		this.properties = properties;
	}

	public Map<String, Object> getKakao_account() {
		return kakao_account;
	}

	public void setKakao_account(Map<String, Object> kakao_account) {
		this.kakao_account = kakao_account;
	}

	public String getRefresh_token_expires_in() {
		return refresh_token_expires_in;
	}

	public void setRefresh_token_expires_in(String refresh_token_expires_in) {
		this.refresh_token_expires_in = refresh_token_expires_in;
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

	public String getGrant_type() {
		return grant_type;
	}

	public void setGrant_type(String grant_type) {
		this.grant_type = grant_type;
	}

	public String getExpires_in() {
		return expires_in;
	}

	public void setExpires_in(String expires_in) {
		this.expires_in = expires_in;
	}

	public String getToken_type() {
		return token_type;
	}

	public void setToken_type(String token_type) {
		this.token_type = token_type;
	}

	public String getScope() {
		return scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
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

}
