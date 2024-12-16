package upt.projeto.service;

import upt.projeto.model.Administrador;
import upt.projeto.model.Aluno;
import upt.projeto.model.AnoEscolaridade;
import upt.projeto.model.Curso;
import upt.projeto.model.Disciplina;
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
    
    public boolean criarAdministrador(Administrador administrador) {
    	String url = BASE_URL + "/administrador"; 
        ResponseEntity<Administrador> response = restTemplate.postForEntity(url, administrador, Administrador.class);

        if (response.getStatusCode().is2xxSuccessful()) {
            System.out.println("Administrador criado com sucesso: " + response.getBody());
            return true; 
        } else {
            System.out.println("Falha ao criar administrador: " + response.getStatusCode());
            return false; 
        }
    }
    
    public boolean criarCurso(Curso curso) {
    	String url = BASE_URL + "/curso"; 
        ResponseEntity<Curso> response = restTemplate.postForEntity(url, curso, Curso.class);

        if (response.getStatusCode().is2xxSuccessful()) {
            System.out.println("Curso criado com sucesso: " + response.getBody());
            return true; 
        } else {
            System.out.println("Falha ao criar curso: " + response.getStatusCode());
            return false; 
        }
    }
    
    public boolean criarAnoEscolaridade(AnoEscolaridade anoEscolaridade) {
    	String url = BASE_URL + "/ano_escolaridade"; 
        ResponseEntity<AnoEscolaridade> response = restTemplate.postForEntity(url, anoEscolaridade, AnoEscolaridade.class);

        if (response.getStatusCode().is2xxSuccessful()) {
            System.out.println("Ano de escolaridade criado com sucesso: " + response.getBody());
            return true; 
        } else {
            System.out.println("Falha ao criar ano de escolaridade: " + response.getStatusCode());
            return false; 
        }
    }
    
    public boolean criarDisciplina(Disciplina disciplina) {
    	String url = BASE_URL + "/disciplina"; 
        ResponseEntity<Disciplina> response = restTemplate.postForEntity(url, disciplina, Disciplina.class);

        if (response.getStatusCode().is2xxSuccessful()) {
            System.out.println("Disciplina criada com sucesso: " + response.getBody());
            return true; 
        } else {
            System.out.println("Falha ao criar disciplina: " + response.getStatusCode());
            return false; 
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
    

    public Professor getDadosProfessor(String email) {
        Utilizador utilizador = getUtilizador(email);
        if (utilizador instanceof Professor) {
            return (Professor) utilizador; 
        }
        return null;
    }
    
    public boolean criarAluno(Aluno aluno) {
        String url = BASE_URL + "/aluno"; 
        ResponseEntity<Aluno> response = restTemplate.postForEntity(url, aluno, Aluno.class);

        if (response.getStatusCode().is2xxSuccessful()) {
            System.out.println("Aluno criado com sucesso: " + response.getBody());
            return true; 
        } else {
            System.out.println("Falha ao criar aluno: " + response.getStatusCode());
            return false; 
        }
    }
    
}
