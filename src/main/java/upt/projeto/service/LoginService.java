package upt.projeto.service;

import upt.projeto.model.Login;
import upt.projeto.model.Professor;
import upt.projeto.model.RespostaLogin;
import upt.projeto.model.Utilizador;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
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
    
    public Utilizador getUtilizador(String email) {
        String url = BASE_URL + "/utilizador/email/" + email; 
        ResponseEntity<Utilizador> response = restTemplate.getForEntity(url, Utilizador.class);

        if (response.getStatusCode().is2xxSuccessful()) {
            return response.getBody(); 
        } else {
            System.out.println("Falha ao obter utilizador: " + response.getStatusCode());
            return null; 
        }
    }
    
    public boolean criarProfessor(Professor professor) {
        String url = BASE_URL + "/professor"; 
        ResponseEntity<Professor> response = restTemplate.postForEntity(url, professor, Professor.class);

        if (response.getStatusCode().is2xxSuccessful()) {
            System.out.println("Professor criado com sucesso: " + response.getBody());
            return true; 
        } else {
            System.out.println("Falha ao criar professor: " + response.getStatusCode());
            return false; 
        }
    }
    
    public List<Professor> getTodosProfessores() {
        String url = BASE_URL + "/professor"; 
        ResponseEntity<List<Professor>> response = restTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<List<Professor>>() {});

        if (response.getStatusCode().is2xxSuccessful()) {
            return response.getBody(); 
        } else {
            System.out.println("Falha ao obter todos os professores: " + response.getStatusCode());
            return null; 
        }
    }
    
}
