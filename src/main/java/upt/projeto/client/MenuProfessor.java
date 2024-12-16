package upt.projeto.client;

import java.util.ArrayList;
import java.util.List;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import upt.projeto.model.Professor;
import upt.projeto.model.Utilizador;
import upt.projeto.service.LoginService;

public class MenuProfessor {

    private Stage primaryStage; 
    private LoginService loginService;
    private Utilizador professor;

    public MenuProfessor() {
        this.primaryStage = primaryStage;
        this.loginService = loginService;
    }

    public MenuProfessor(Stage primaryStage, LoginService loginService) {
        this.primaryStage = primaryStage;
        this.loginService = loginService;
    }
    
    public void setProfessor(Utilizador professor) {
        this.professor = professor;
    }

    public void start(Stage menuProfStage) {
        menuProfStage.setTitle("Menu do Professor");

        Label welcomeLabel = new Label("Bem-vindo, Professor!");
        welcomeLabel.setStyle("-fx-font-size: 28px; -fx-font-weight: bold;");

        Button verDadosButton = new Button("Ver Dados Pessoais");
        Button verProfessoresButton = new Button("Ver Todos os Professores");
        Button criarQuizButton = new Button("Criar Quiz");
        Button verRespostasButton = new Button("Ver Respostas dos Alunos");
        Button logoutButton = new Button("Sair");

        verDadosButton.setOnAction(e ->{
        	mostrarDadosPessoais(professor);
        });
        
        verProfessoresButton.setOnAction(e->{
        	verProfessores();
        });
       
        
        logoutButton.setOnAction(e -> {
            menuProfStage.close(); 
            primaryStage.show(); 
        });

        VBox layout = new VBox(10);
        layout.setAlignment(Pos.CENTER);
        layout.getChildren().addAll(welcomeLabel, verDadosButton, verProfessoresButton, criarQuizButton, verRespostasButton, logoutButton);

        Scene scene = new Scene(layout, 1280, 720);
        menuProfStage.setScene(scene);
        menuProfStage.show();
    }
    
   
    private void mostrarDadosPessoais(Utilizador utilizador) {
        if (utilizador instanceof Professor) {
            Professor professor = (Professor) utilizador; 

            Stage dadosPessoaisStage = new Stage();
            dadosPessoaisStage.setTitle("Dados Pessoais");

            Label nomeLabel = new Label("Nome: " + professor.getNome());
            Label emailLabel = new Label("Email: " + professor.getEmail());
            Label numProfessorLabel = new Label("Número de Professor: " + professor.getNumProfessor());

            Button voltarButton = new Button("Voltar");
            voltarButton.setOnAction(e -> dadosPessoaisStage.close());

            VBox dadosLayout = new VBox(10);
            dadosLayout.setAlignment(Pos.CENTER);
            dadosLayout.getChildren().addAll(nomeLabel, emailLabel, numProfessorLabel, voltarButton);

            Scene dadosScene = new Scene(dadosLayout, 400, 300);
            dadosPessoaisStage.setScene(dadosScene);
            dadosPessoaisStage.show();
        } else {
            System.out.println("O utilizador não é um professor.");
        }
    }
    
    private void verProfessores() {
    	List<Professor> professores = loginService.getTodosProfessores();
    	
    	Stage professoresStage = new Stage();
        professoresStage.setTitle("Lista de Professores");
        
        VBox professoresLayout = new VBox(10);
        professoresLayout.setAlignment(Pos.CENTER);
        
        if (professores != null && !professores.isEmpty()) {
            for (Professor professor : professores) {
                Label professorLabel = new Label("Nome: " + professor.getNome() + ", Email: " + professor.getEmail() + ", Número: " + professor.getNumProfessor());
                professoresLayout.getChildren().add(professorLabel);
            }
        } else {
            Label noProfessoresLabel = new Label("Nenhum professor encontrado!");
            professoresLayout.getChildren().add(noProfessoresLabel);
        }
        
        Button voltarButton = new Button("Voltar");
        voltarButton.setStyle("-fx-text-fill: red;"); 
        voltarButton.setOnAction(e -> {
            professoresStage.close(); 
        });
        
        
        Scene professoresScene = new Scene(professoresLayout, 1280, 720);
        professoresStage.setScene(professoresScene);
        professoresStage.show();
    }
    
    private void criarQuiz() {
    	
    }
    
    private void verRespostas() {
    	
    }
    
}
