package com.upt.hibernate.proj_9grupo.service;

import com.upt.hibernate.proj_9grupo.model.Login;
import com.upt.hibernate.proj_9grupo.model.RespostaLogin;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class LoginService {
    private static final String BASE_URL = "http://localhost:8080/api"; 
    private RestTemplate restTemplate = new RestTemplate();
    

    public boolean login(String email, String password) {
        String url = BASE_URL + "/login"; 
        Login loginRequest = new Login(email, password);

        ResponseEntity<RespostaLogin> response = restTemplate.postForEntity(url, loginRequest, RespostaLogin.class);

        if (response.getStatusCode().is2xxSuccessful()) {
        	RespostaLogin loginResponse = response.getBody();
            return loginResponse != null && loginResponse.isSuccess(); 
        } else {
            System.out.println("Login falhou: " + response.getStatusCode());
            return false;
        }
    }
    
}
