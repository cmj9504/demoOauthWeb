package com.example.demo.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

import com.example.demo.entity.NaverOauth;
import com.example.demo.utile.HttpUtile;

@Controller
public class NaverOauthController {
	@Autowired
	private NaverOauth naverOauth;
	
	@GetMapping("getNaverOauthCode")
	public String getNaverOauthCode(HttpServletResponse response) {
		HttpUtile<NaverOauth> utile = new HttpUtile<NaverOauth>();
		utile.setParams(naverOauth.getOauthCodeParmas());
		String redirectUrl = "redirect:";
		try {
			redirectUrl += naverOauth.getCodeBaseUrl() + "?" + utile.getAllParam();
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return redirectUrl;
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
}
