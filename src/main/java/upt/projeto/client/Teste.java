package upt.projeto.client;

import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
import javafx.geometry.Pos; 
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import upt.projeto.model.Aluno;
import upt.projeto.model.Professor;
import upt.projeto.model.Utilizador;
import upt.projeto.service.LoginService;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class Teste extends Application {

	private Stage primaryStage;
	private List<Utilizador> utilizadores = new ArrayList<>();
	private LoginService loginService = new LoginService();
	
	@Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage; 
        primaryStage.setTitle("Quiz Educativo");

        Label welcomeLabel = new Label("Bem-vindo ao Quiz Educativo!");
        welcomeLabel.setStyle("-fx-font-size: 28px; -fx-font-weight: bold;");

        Label choiceLabel = new Label("Selecione:");
        choiceLabel.setStyle("-fx-font-size: 24px;");

        Button loginButton = new Button("Login");
        Button criarContaButton = new Button("Criar Conta");
        Button exitButton = new Button("Sair");

        loginButton.setOnAction(e -> telaLogin());
        criarContaButton.setOnAction(e -> telaCriarConta());
        exitButton.setOnAction(e -> primaryStage.close());

        VBox layout = new VBox(10);
        layout.setAlignment(Pos.CENTER);
        layout.getChildren().addAll(welcomeLabel, choiceLabel, loginButton, criarContaButton, exitButton);
        Scene scene = new Scene(layout, 1280, 720);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void telaLogin() {
        Stage loginStage = new Stage();
        loginStage.setTitle("Login");

        Label emailLabel = new Label("Email:");
        TextField emailField = new TextField();
        emailField.setPromptText("Escreva o seu email");

        Label passwordLabel = new Label("Password:");
        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Escreva a sua password");
        
        Label errorMessageLabel = new Label();
        errorMessageLabel.setStyle("-fx-text-fill: red;"); 

        Button loginButton = new Button("Entrar");
        loginButton.setOnAction(e -> {
            String email = emailField.getText();
            String password = passwordField.getText();
            
            if (email.isEmpty() || password.isEmpty()) {
                errorMessageLabel.setText("Por favor, preencha todos os campos.");
                return;
            }
            
            LoginService loginService = new LoginService(); 
            if(loginService.login(email, password)) {
            	System.out.println("Login bem sucedido: Email: " + email);
            	loginStage.close();
            	Utilizador utilizador = loginService.getUtilizador(email); 
                if (utilizador != null) {
                    if (utilizador.getTipoUtilizador() == Utilizador.TipoUtilizador.professor) {
                        MenuProfessor menuProfessor = new MenuProfessor(primaryStage, loginService);
                        menuProfessor.setProfessor(utilizador);
                        menuProfessor.start(new Stage());  
                }
            } else {
            	errorMessageLabel.setText("Email ou password incorretos.");
            }
            }
        });

        Button voltarButton = new Button("Voltar");
        voltarButton.setOnAction(e -> {
            loginStage.close(); 
            primaryStage.show(); 
        });

        VBox loginLayout = new VBox(10);
        loginLayout.setAlignment(Pos.CENTER);
        loginLayout.getChildren().addAll(emailLabel, emailField, passwordLabel,errorMessageLabel, passwordField, loginButton, voltarButton);

        Scene loginScene = new Scene(loginLayout, 1280, 720);
        loginStage.setScene(loginScene);
        loginStage.show();
        primaryStage.hide(); 
    }


    private void telaCriarConta() {
    	Stage createAccountStage = new Stage();
        createAccountStage.setTitle("Criar Conta");
        
        Button alunoButton = new Button("Criar Conta como Aluno");
        Button professorButton = new Button("Criar Conta como Professor");
        
        alunoButton.setOnAction(e -> criarContaAluno(createAccountStage));
        professorButton .setOnAction(e -> criarContaProfessor(createAccountStage));
        
        Button voltarButton = new Button("Voltar");
        voltarButton.setOnAction(e -> {
        	createAccountStage.close(); 
            primaryStage.show(); 
        });
        
        VBox selectionLayout = new VBox(10);
        selectionLayout.setAlignment(Pos.CENTER);
        selectionLayout.getChildren().addAll(alunoButton, professorButton);

        Scene selectionScene = new Scene(selectionLayout, 1280, 720);
        createAccountStage.setScene(selectionScene);
        createAccountStage.show();
        primaryStage.hide(); 
    }

    
    public void criarContaAluno(Stage createAccountStage) {
    	Label nameLabel = new Label("Nome:");
        TextField nameField = new TextField();
        nameField.setPromptText("Escreva o seu nome");
        
        Label emailLabel = new Label("Email:");
        TextField emailField = new TextField();
        emailField.setPromptText("Escreva o seu email");
        
        Label numeroAlunoLabel = new Label("Número de Aluno:");
        TextField numeroAlunoField = new TextField();
        numeroAlunoField.setPromptText("Escreva o seu número de aluno");
        
        Label passwordLabel = new Label("Password:");
        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Escreva a sua password");
        
        Button createAccountButton = new Button("Criar Conta");
        createAccountButton.setOnAction(e -> {
            String nome = nameField.getText();
            String email = emailField.getText();
            int numeroAluno = 0;
            try {
                numeroAluno = Integer.parseInt(numeroAlunoField.getText());
            } catch (NumberFormatException ex) {
                System.out.println("Número de aluno inválido! Por favor, insira um número válido.");
            }
            String password = passwordField.getText();
 
            Aluno novoAluno = new Aluno(nome, email, numeroAluno, password);
            utilizadores.add(novoAluno);
            System.out.println("Conta de aluno criada: " + novoAluno);
            createAccountStage.close();
        });
        
        Button voltarButton = new Button("Voltar");
        voltarButton.setOnAction(e -> {
        	createAccountStage.close(); 
            primaryStage.show(); 
        });
        
        VBox createAccountLayout = new VBox(10);
        createAccountLayout.setAlignment(Pos.CENTER);
        createAccountLayout.getChildren().addAll(nameLabel, nameField, emailLabel, emailField, numeroAlunoLabel, numeroAlunoField, passwordLabel, passwordField, createAccountButton, voltarButton);

        Scene createAccountScene = new Scene(createAccountLayout, 1280, 720);
        createAccountStage.setScene(createAccountScene);
        

    }
    
    private void criarContaProfessor(Stage createAccountStage) {
        Label nameLabel = new Label("Nome:");
        TextField nameField = new TextField();
        nameField.setPromptText("Escreva o seu nome");

        Label emailLabel = new Label("Email:");
        TextField emailField = new TextField();
        emailField.setPromptText("Escreva o seu email");

        Label numProfessorLabel= new Label("Número de Professor:");
        TextField numProfessorField = new TextField();
        numProfessorField.setPromptText("Escreva o seu número de professor");
        
        Label disciplinaLabel = new Label("Disciplina:");
        TextField disciplinaField = new TextField();
        disciplinaField.setPromptText("Escreva a disciplina que dá aulas");

        Label passwordLabel = new Label("Password:");
        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Escreva a sua password");

        Button createAccountButton = new Button("Criar Conta");
        createAccountButton.setOnAction(e -> {
            String nome = nameField.getText();
            String email = emailField.getText();
            int numProfessor = 0;
            try {
            	numProfessor = Integer.parseInt(numProfessorField.getText());
            } catch (NumberFormatException ex) {
                System.out.println("Número de aluno inválido. Por favor, insira um número válido.");
            }
            String disciplina = disciplinaField.getText();
            if (disciplina.isEmpty()) {
                System.out.println("A disciplina não pode estar vazia.");
                return; 
            }
            String senha = passwordField.getText();

            Professor novoProfessor = new Professor(nome, email, numProfessor, disciplina, senha);
            utilizadores.add(novoProfessor);
            LoginService loginService = new LoginService();
            boolean sucesso = loginService.criarProfessor(novoProfessor);
            if (sucesso) {
                System.out.println("Conta de professor criada com sucesso!");
                createAccountStage.close(); 
                telaLogin();
            } else {
                System.out.println("Falha ao criar conta de professor.");
            }
            System.out.println("Conta de professor criada: " + novoProfessor);    //usar o to string da classe Professor em vez do da classe Utilizador
            createAccountStage.close();
        });

        Button voltarButton = new Button("Voltar");
        voltarButton.setOnAction(e -> {
            createAccountStage.close();
            primaryStage.show();
        });

        VBox createAccountLayout = new VBox(10);
        createAccountLayout.setAlignment(Pos.CENTER);
        createAccountLayout.getChildren().addAll(nameLabel, nameField, emailLabel, emailField, numProfessorLabel, numProfessorField, disciplinaLabel, disciplinaField, passwordLabel, passwordField, createAccountButton, voltarButton);

        Scene createAccountScene = new Scene(createAccountLayout, 1280, 720);
        createAccountStage.setScene(createAccountScene);
    }
    
    
    public static void main(String[] args) {
        launch(args);
    }

}
