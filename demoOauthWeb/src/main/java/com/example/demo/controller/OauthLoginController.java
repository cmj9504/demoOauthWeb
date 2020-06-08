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
import com.example.demo.utile.HttpUtile;

@Controller
public class OauthLoginController {
	
	@Autowired
	private GoogleOauth googleOauth;
	
	@GetMapping("login")
	public String login() {
		return "login";
	}
	
	@GetMapping("getGoogleOauthCode")
	public String getGoogleOauthCode(HttpServletResponse response) {
		HttpUtile<GoogleOauth> utile = new HttpUtile<GoogleOauth>(googleOauth.getCodeBaseUrl());
		utile.setParams(googleOauth.getGoogleOauthCodeParmas());
		
		try {
			String result = utile.getCodeExec();
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
