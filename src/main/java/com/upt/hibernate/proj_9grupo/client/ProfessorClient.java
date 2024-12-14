package com.upt.hibernate.proj_9grupo.client;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import com.upt.hibernate.proj_9grupo.model.Professor;
import com.upt.hibernate.proj_9grupo.model.Utilizador.TipoUtilizador;


public class ProfessorClient {

    private RestTemplate restTemplate = new RestTemplate();
    
    private String rootAPIURL = "http://localhost:8080/api/professor";
    
    public void getProfessorById(Long id) {
        ResponseEntity<Professor> response = restTemplate.getForEntity(rootAPIURL + "/" + id.toString(), Professor.class);
        
        if (response.getStatusCode().is2xxSuccessful()) {
            Professor body = response.getBody();
            if (body != null) {
                System.out.println(body.toString());
            } else {
                System.out.println("No body.");
            }
        } else {
            System.out.println("Nada encontrado!!");
        }
    }
    
    public void getAllProfessores() {
        ResponseEntity<Professor[]> response = restTemplate.getForEntity(rootAPIURL, Professor[].class);
        
        if (response.getStatusCode().is2xxSuccessful()) {
            Professor[] professores = response.getBody();
            if (professores != null) {
                for (Professor professor : professores) {
                    System.out.println(professor.toString());
                }
            } else {
                System.out.println("No body!");
            }
        } else {
            System.out.println("Nada encontrado!!");
        }
    }
    
    public void criarProfessor() {
        Professor professor = new Professor();
        
        professor.setNome("Teste Professor");
        professor.setEmail("teste.professor@gmail.com");
        professor.setNumProfessor(1); 
        professor.setDisciplina("Disciplina Teste");
        professor.setTipoUtilizador(TipoUtilizador.professor);
        
        ResponseEntity<Professor> response = restTemplate.postForEntity(rootAPIURL, professor, Professor.class);
        
        if (response.getStatusCode().is2xxSuccessful()) {
            Professor body = response.getBody();
            if (body != null) {
                System.out.println(body.toString());
            } else {
                System.out.println("No body.");
            }
        } else {
            System.out.println("Nada encontrado!!");
        }
    }
    
    public void updateProfessor(Long id) {
        Professor professor = new Professor();
        professor.setId(id.intValue()); 
        professor.setNome("João Professor");
        professor.setEmail("joao.professor@gmail.com");
        professor.setDisciplina("Portugues");
        
        ResponseEntity<Professor> response = restTemplate.exchange(rootAPIURL + "/" + id, HttpMethod.PUT, new HttpEntity<>(professor), Professor.class);
        
        if (response.getStatusCode().is2xxSuccessful()) {
            Professor updatedProfessor = response.getBody();
            if (updatedProfessor != null) {
                System.out.println("Professor atualizado: " + updatedProfessor.toString());
            } else {
                System.out.println("No body.");
            }
        } else {
            System.out.println("Erro ao atualizar professor: " + response.getStatusCode());
        }
    }
    
    public void eliminarProfessor(Long id) {
	    restTemplate.delete(rootAPIURL + "/" + id.toString());
	    System.out.println("Professor eliminado com sucesso!");
	}
}
