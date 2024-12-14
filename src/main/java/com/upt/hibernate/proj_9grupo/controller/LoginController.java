package com.upt.hibernate.proj_9grupo.controller;

import com.upt.hibernate.proj_9grupo.model.Login;
import com.upt.hibernate.proj_9grupo.model.RespostaLogin;
import com.upt.hibernate.proj_9grupo.service.UtilizadorService; 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class LoginController {

    @Autowired
    private UtilizadorService utilizadorService; 

    @PostMapping("/login")
    public ResponseEntity<RespostaLogin> login(@RequestBody Login login) {
        String email = login.getEmail();
        String password = login.getPassword();
        boolean isAuthenticated = utilizadorService.authenticate(email, password);

        if (isAuthenticated) {
            return ResponseEntity.ok(new RespostaLogin(true, "Login bem-sucedido!"));
        } else {
            return ResponseEntity.status(401).body(new RespostaLogin());
        }
    }
}
