package com.example.demo.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.demo.entity.GoogleOauth;
import com.example.demo.entity.NaverOauth;
import com.example.demo.utile.HttpUtile;

@Controller
public class OauthLoginController {
	
	@Autowired
	private GoogleOauth googleOauth;
	@Autowired
	private NaverOauth naverOauth;
	
	
	@GetMapping("login")
	public String login() {
		return "login";
	}
	
	@GetMapping("getNaverOauthCode")
	public String getNaverOauthCode(HttpServletResponse response) {
		HttpUtile<NaverOauth> utile = new HttpUtile<NaverOauth>(naverOauth.getCodeBaseUrl());
		utile.setParams(naverOauth.getOauthCodeParmas());
		
		try {
			
			String result = utile.getHttpRequestExec(); 
			result += "<script type=\"text/javascript\">document.getElementById(\"frmNIDLogin\").submit();</script>";
			response.getWriter().write(result);
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return "getNaverOauthCode";
	}
	
	@GetMapping("naverCallBack")
	public String getNaverCallBack(HttpServletRequest request) {
		String code = request.getParameter("code");
		if(code != null) {
			try {
				String accToken = naverAccToken(code);
				Map<String, Object> result = getNaverUserInfo(accToken);
				request.setAttribute("result", result);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}else {
			//TODO err handle 
		}
		
		return "naverCallBack";
	}
	
	public String naverAccToken(String code) throws Exception {
		naverOauth.setCode(code);
		naverOauth.setGrant_type("authorization_code");
		HttpUtile<NaverOauth> utile = new HttpUtile<NaverOauth>(naverOauth.getAccessTokenBaseUrl());
		utile.setParams(naverOauth.getOauthTokenParmas());
		
		RestTemplate restTemplate = new RestTemplate();
		String url = naverOauth.getAccessTokenBaseUrl() +"?"+ utile.getAllParam();
		ResponseEntity<NaverOauth> entity = restTemplate.getForEntity(url, NaverOauth.class);
		
		//TODO refresh_token handle must read naver login api
		//String refToken = entity.getBody().getRefresh_token();
		return entity.getBody().getAccess_token();
	}
	
	public Map<String, Object> getNaverUserInfo(String accToken) throws Exception {
		naverOauth.setAccess_token(accToken);
		HttpUtile<NaverOauth> utile = new HttpUtile<NaverOauth>();
		utile.setParams(naverOauth.getOauthUserInfoParmas());
		
		RestTemplate restTemplate = new RestTemplate();
		String url = naverOauth.getUserInfoBaseUrl() +"?"+ utile.getAllParam();
		ResponseEntity<NaverOauth> entity = restTemplate.getForEntity(url, NaverOauth.class);
		return entity.getBody().getResponse();
	}
	
	@GetMapping("getGoogleOauthCode")
	public String getGoogleOauthCode(HttpServletResponse response) {
		HttpUtile<GoogleOauth> utile = new HttpUtile<GoogleOauth>(googleOauth.getCodeBaseUrl());
		utile.setParams(googleOauth.getOauthCodeParmas());
		
		try {
			String result = utile.getHttpRequestExec();
			response.getWriter().write(result);
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return "getGoogleOauthCode";
	}
	
	@GetMapping("googleCallBack")
	public String getGoogleCallBack(HttpServletRequest request) {
		String code = request.getParameter("code");
		if(code != null) {
			googleOauth.setCode(code);
			HttpUtile<GoogleOauth> utile = new HttpUtile<GoogleOauth>(googleOauth.getAccessTokenBaseUrl(), googleOauth);
			RestTemplate restTemplate = new RestTemplate();
			try {
				ResponseEntity<GoogleOauth> result = restTemplate.postForEntity(utile.getURI(), utile.getHttpEntity(), GoogleOauth.class);
				
				//TODO after process
				//String refToken = result.getBody().getRefresh_token();
				//String accToken = result.getBody().getAccess_token();
				String token = result.getBody().getId_token();
				DecodedJWT decodeJwt = JWT.decode(token);
				Map<String, Claim> claims = decodeJwt.getClaims();
				
				request.setAttribute("name", claims.get("name").asString()); 
				request.setAttribute("email", claims.get("email").asString()); 
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}else {
			//TODO err handle 
		}
		
		return "googleCallBack";
	}
}
