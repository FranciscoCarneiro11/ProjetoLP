package upt.projeto.service;

import upt.projeto.model.Administrador;
import upt.projeto.model.Aluno;
import upt.projeto.model.AnoEscolaridade;
import upt.projeto.model.AssociacaoAluno;
import upt.projeto.model.Curso;
import upt.projeto.model.Disciplina;
import upt.projeto.modelDTO.DisciplinaDTO;
import upt.projeto.model.Login;
import upt.projeto.model.Pergunta;
import upt.projeto.model.Professor;
import upt.projeto.model.Quiz;
import upt.projeto.modelDTO.QuizDTO;
import upt.projeto.model.RespostaLogin;
import upt.projeto.model.Utilizador;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import java.util.ArrayList;
import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
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
    
    public Administrador getAdministrador(int administradorId) {
        String url = BASE_URL + "/administrador/" + administradorId; 
        ResponseEntity<Administrador> response = restTemplate.getForEntity(url, Administrador.class);

        if (response.getStatusCode().is2xxSuccessful()) {
            return response.getBody();
        } else {
            System.out.println("Falha ao obter administrador: " + response.getStatusCode());
            return null;
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

    
    
    public List<Curso> getTodosCursos() {
        String url = BASE_URL + "/curso/todos";
        System.out.println("URL chamada: " + url);

        ResponseEntity<List<Curso>> response = restTemplate.exchange(url,HttpMethod.GET,null,new ParameterizedTypeReference<List<Curso>>() {});

        if (response.getStatusCode().is2xxSuccessful()) {
            return response.getBody();
        } else {
            System.out.println("Falha ao obter todos os cursos: " + response.getStatusCode());
            return new ArrayList<>();
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

    public boolean criarQuiz(Quiz quiz) {
        String url = BASE_URL + "/quiz";

        QuizDTO quizDTO = new QuizDTO();
        quizDTO.setTitulo(quiz.getTitulo());
        quizDTO.setProfessorId((long) quiz.getProfessor().getId());
        quizDTO.setDisciplinaId((long) quiz.getDisciplina().getId());

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON); 		//informa ao servidor que os dados enviados devem ser interpretados como JSON

        HttpEntity<QuizDTO> request = new HttpEntity<>(quizDTO, headers);
        ResponseEntity<Quiz> response = restTemplate.postForEntity(url, request, Quiz.class);

        if (response.getStatusCode().is2xxSuccessful()) {
            System.out.println("Quiz criado com sucesso: " + response.getBody());
            return true;
        } else {
            System.out.println("Falha ao criar quiz: " + response.getStatusCode());
            return false;
        }
    }

    
    public boolean editarQuiz(Long quizId, Pergunta pergunta, Long professorId) {
        String url = BASE_URL + "/pergunta/" + quizId + "?professorId=" + professorId;

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Pergunta> request = new HttpEntity<>(pergunta, headers);
        try {
            ResponseEntity<Pergunta> response = restTemplate.postForEntity(url, request, Pergunta.class);
            return response.getStatusCode().is2xxSuccessful();
        } catch (HttpClientErrorException e) {
            System.out.println("Erro ao editar o quiz: " + e.getResponseBodyAsString());
            return false;
        } catch (Exception e) {
            System.out.println("Erro inesperado: " + e.getMessage());
            return false;
        }
    }


    public List<QuizDTO> getTodosQuizzes() {
        String url = BASE_URL + "/quiz";
        ResponseEntity<List<QuizDTO>> response = restTemplate.exchange(url, HttpMethod.GET,null,new ParameterizedTypeReference<List<QuizDTO>>() {});
        if (response.getStatusCode().is2xxSuccessful()) {
            List<QuizDTO> quizzes = response.getBody();
            if (quizzes != null) {
                for (QuizDTO quiz : quizzes) {
                    System.out.println("Quiz encontrado: " + quiz.getTitulo() + " com ID: " + quiz.getId());
                }
            }
            return quizzes;
        } else {
            System.out.println("Falha ao obter todos os quizzes: " + response.getStatusCode());
            return new ArrayList<>();
        }
    }

    
    
    public List<DisciplinaDTO> getTodasDisciplinas() {
        String url = BASE_URL + "/disciplina";

        ResponseEntity<List<DisciplinaDTO>> response = restTemplate.exchange(url,HttpMethod.GET,null,new ParameterizedTypeReference<List<DisciplinaDTO>>() {});

        if (response.getStatusCode().is2xxSuccessful()) {
            return response.getBody();
        } else {
            System.out.println("Falha ao obter todas as disciplinas: " + response.getStatusCode());
            return new ArrayList<>();
        }
    }


    public boolean associarDisciplinaProfessor(Long professorId, Long disciplinaId) {
        String url = BASE_URL + "/professor/" + professorId + "/disciplina/" + disciplinaId;

        ResponseEntity<String> response = restTemplate.postForEntity(url, null, String.class);

        if (response.getStatusCode().is2xxSuccessful()) {
            System.out.println("Disciplina associada ao professor com sucesso.");
            return true;
        } else {
            System.out.println("Falha ao associar disciplina ao professor: " + response.getStatusCode());
            return false;
        }
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
    
    public List<Aluno> getTodosAlunos() {
        String url = BASE_URL + "/aluno"; 
        ResponseEntity<List<Aluno>> response = restTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<List<Aluno>>() {});

        if (response.getStatusCode().is2xxSuccessful()) {
            return response.getBody(); 
        } else {
            System.out.println("Falha ao obter todos os alunos: " + response.getStatusCode());
            return null; 
        }
    }
    
    
    public Aluno getDadosAluno(String email) {
        Utilizador utilizador = getUtilizador(email);
        if (utilizador instanceof Aluno) {
            return (Aluno) utilizador; 
        }
        return null;
    }
    
    
    public boolean associarAluno(Aluno aluno, Curso curso, AnoEscolaridade anoEscolaridade, List<Disciplina> disciplinas) {
        AssociacaoAluno associacao = new AssociacaoAluno();
        associacao.setAluno(aluno);
        associacao.setCurso(curso);
        associacao.setAnoEscolaridade(anoEscolaridade);
        associacao.setDisciplinas(disciplinas);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<AssociacaoAluno> requestEntity = new HttpEntity<>(associacao, headers);

        String url = BASE_URL + "/associacao_aluno";
        try {
            ResponseEntity<AssociacaoAluno> response = restTemplate.postForEntity(url, requestEntity, AssociacaoAluno.class);
            return response.getStatusCode().is2xxSuccessful();
        } catch (Exception e) {
            System.out.println("Erro ao associar aluno: " + e.getMessage());
            return false;
        }
    }


    public List<AnoEscolaridade> getAnosEscolaridadePorCurso(Long cursoId) {
        String url = BASE_URL + "/associacao_aluno/ano_escolaridade?cursoId=" + cursoId; 
        try {
            ResponseEntity<List<AnoEscolaridade>> response = restTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<List<AnoEscolaridade>>() {});
            if (response.getStatusCode().is2xxSuccessful()) {
                return response.getBody();
            } else {
                System.out.println("Falha ao obter anos de escolaridade: " + response.getStatusCode());
                return new ArrayList<>();
            }
        } catch (Exception e) {
            System.out.println("Erro ao chamar a API: " + e.getMessage());
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
       
}
