package com.example.demo.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

import com.example.demo.entity.KakaoOauth;
import com.example.demo.utile.HttpUtile;

@Controller
public class KakaoOauthController {
	@Autowired
	private KakaoOauth kakaoOauth;
	
	@GetMapping("getKakaoOauthCode")
	public String getKakaoOauthCode(HttpServletResponse response) {
		HttpUtile<KakaoOauth> utile = new HttpUtile<KakaoOauth>();
		utile.setParams(kakaoOauth.getOauthCodeParmas());
		String redirectUrl = "redirect:";
		try {
			redirectUrl += kakaoOauth.getCodeBaseUrl() + "?" + utile.getAllParam();
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return redirectUrl;
	}
	
	@GetMapping("kakaoCallBack")
	public String getKakaoCallBack(HttpServletRequest request) {
		String code = request.getParameter("code");
		if(code != null) {
			try {
				String accToken = getAccToken(code);
				if(accToken != null && !"".equals(accToken)) {
					ResponseEntity<KakaoOauth> entity = getKakaoEntity(accToken);
					
					//TODO get need data 
					request.setAttribute("email", entity.getBody().getKakao_account().get("email")); 
					request.setAttribute("nickname", entity.getBody().getProperties().get("nickname")); 
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}else {
			//TODO err handle 
		}
		
		return "kakaoCallBack";
	}
	
	public ResponseEntity<KakaoOauth> getKakaoEntity(String accToken) {
		HttpHeaders headers = new HttpHeaders();
	    headers.set("Authorization", "Bearer " + accToken); 
	    headers.set("Content-Type", "application/json;charset=utf-8"); 
		
	    HttpEntity<KakaoOauth> httpEntity = new HttpEntity<KakaoOauth>(kakaoOauth, headers);
	    
	    RestTemplate restTemplate = new RestTemplate();
		return restTemplate.postForEntity(kakaoOauth.getUserInfoBaseUrl(), httpEntity, KakaoOauth.class);
	}
	
	public String getAccToken(String code) throws Exception{
		String accToken = "";
		kakaoOauth.setCode(code);
		HttpUtile<KakaoOauth> utile = new HttpUtile<KakaoOauth>();
		utile.setParams(kakaoOauth.getOauthTokenParmas());
		
		String url = kakaoOauth.getAccessTokenBaseUrl() + "?" + utile.getAllParam();
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<KakaoOauth> entity = restTemplate.getForEntity(url, KakaoOauth.class);
		accToken = entity.getBody().getAccess_token();
		
		return accToken;	
	}
}
