package com.ility.customconfig.services;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
@Service
public class JspServiceImpl implements JspService{

	
	@Override
	public void getAccessToken(String username,String password) {
		// TODO Auto-generated method stub
		

		try {
			  JSONObject json = new JSONObject();
              json.put("username", username);
              json.put("password", password);
              RestTemplate restTemplate=new RestTemplate();
              HttpHeaders headers = new HttpHeaders();
              MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
              headers.setContentType(type);
              headers.add("Accept", MediaType.APPLICATION_JSON.toString());
              HttpEntity<String> formEntity = new HttpEntity<String>(json.toString(), headers);
              String s= restTemplate.postForEntity("https://ility-authentication.herokuapp.com/v1/Auth/Login",
            		  formEntity,String.class).getBody();
              System.out.print(s);
		}catch(Exception e) {
			e.printStackTrace();
		}

		
		
	}

}
