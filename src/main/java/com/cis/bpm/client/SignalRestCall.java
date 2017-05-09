package com.cis.bpm.client;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.codec.binary.Base64;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class SignalRestCall {
	public static void main(String[] args) throws JsonProcessingException {
		//http://localhost:8080/business-central/rest/runtime/cox:Test:1.6/signal?signal=SIGNAL_INPUT

		String base_url = "http://localhost:8080/business-central/rest/runtime/cox:Test:1.6/signal?signal=SIGNAL_INPUT";
		String username = "admin";
		String password = "Admin@123";
		String url = base_url;
		//Map<String, String> params = new HashMap<String, String>();
		//params.put("map_name", "first.last@example.com");
		
		String plainCreds = username + ":" + password;
		byte[] plainCredsBytes = plainCreds.getBytes();
		byte[] base64CredsBytes = Base64.encodeBase64(plainCredsBytes);
		String base64Creds = new String(base64CredsBytes);

		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "Basic " + base64Creds);
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		
		//Object mybody;
		MultiValueMap<String, Object> body = new LinkedMultiValueMap<String, Object>();
		
		Map<String,Object> data = new HashMap<String, Object>();
		data.put("Test", "Test");
		data.put("Test123", "Test");
		
		ObjectMapper obj = new ObjectMapper();
		body.add("event", obj.writeValueAsString(data));
		
		
		
//		body.add("map_name", "Abhay12");

		System.out.println(body);
		
		HttpEntity<MultiValueMap<String, Object>> requestEntity= 
                new HttpEntity<MultiValueMap<String, Object>>(body, headers);
//		HttpEntity<String> request = new HttpEntity(body, headers);
		
		System.out.println(url);
		RestTemplate rs = new RestTemplate();
		
		ResponseEntity<String> s = rs.postForEntity(url, requestEntity, String.class);
		
		System.out.println(s.getBody());
		
//		Object ob = rs.postForObject(url, "", Object.class);
	}

}
