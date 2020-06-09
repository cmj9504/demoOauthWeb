package com.example.demo.entity;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class GoogleOauth {
	@Value("${google.client_id}")
	private String client_id;
	@Value("${google.client_secret}")
	private String client_secret;
	
	@Value("${google.codeBaseUrl}")
	private String codeBaseUrl;
	@Value("${google.accessTokenBaseUrl}")
	private String accessTokenBaseUrl;
	@Value("${google.redirect_uri}")
	private String redirect_uri;
	
	@Value("${google.scope}")
	private String scope;
	@Value("${google.access_type}")
	private String access_type;
	private String id_token;
	
	@Value("${google.include_granted_scopes}")
	private String include_granted_scopes;
	@Value("${google.response_type}")
	private String response_type;
	
	@Value("${google.grant_type}")
	private String grant_type;
	private String access_token;
	private String refresh_token;
	
	private String expires_in;
	private String token_type;
	
	private String code;
	
	public Map<String, String> getOauthCodeParmas() {
		Map<String, String> params = new HashMap<String, String>();
		params.put("scope", getScope());
		params.put("access_type", getAccess_type());
		params.put("include_granted_scopes", getInclude_granted_scopes());
		params.put("response_type", getResponse_type());
		params.put("redirect_uri", getRedirect_uri());
		params.put("client_id", getClient_id());
		return params;
	}
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getId_token() {
		return id_token;
	}

	public void setId_token(String id_token) {
		this.id_token = id_token;
	}

	public String getClient_secret() {
		return client_secret;
	}

	public void setClient_secret(String client_secret) {
		this.client_secret = client_secret;
	}

	public String getGrant_type() {
		return grant_type;
	}

	public void setGrant_type(String grant_type) {
		this.grant_type = grant_type;
	}

	public String getAccess_token() {
		return access_token;
	}

	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}

	public String getRefresh_token() {
		return refresh_token;
	}

	public void setRefresh_token(String refresh_token) {
		this.refresh_token = refresh_token;
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

	public String getAccessTokenBaseUrl() {
		return accessTokenBaseUrl;
	}

	public void setAccessTokenBaseUrl(String accessTokenBaseUrl) {
		this.accessTokenBaseUrl = accessTokenBaseUrl;
	}

	public String getCodeBaseUrl() {
		return codeBaseUrl;
	}

	public void setCodeBaseUrl(String codeBaseUrl) {
		this.codeBaseUrl = codeBaseUrl;
	}

	public String getScope() {
		return scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}

	public String getAccess_type() {
		return access_type;
	}

	public void setAccess_type(String access_type) {
		this.access_type = access_type;
	}

	public String getInclude_granted_scopes() {
		return include_granted_scopes;
	}

	public void setInclude_granted_scopes(String include_granted_scopes) {
		this.include_granted_scopes = include_granted_scopes;
	}

	public String getResponse_type() {
		return response_type;
	}

	public void setResponse_type(String response_type) {
		this.response_type = response_type;
	}

	public String getRedirect_uri() {
		return redirect_uri;
	}

	public void setRedirect_uri(String redirect_uri) {
		this.redirect_uri = redirect_uri;
	}

	public String getClient_id() {
		return client_id;
	}

	public void setClient_id(String client_id) {
		this.client_id = client_id;
	}
}
