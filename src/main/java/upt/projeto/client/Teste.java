package upt.projeto.client;

import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
import javafx.geometry.Pos; 
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import upt.projeto.model.Administrador;
import upt.projeto.model.Aluno;
import upt.projeto.model.Professor;
import upt.projeto.model.Utilizador;
import upt.projeto.service.LoginService;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Teste extends Application {

	private Stage primaryStage;
	private List<Utilizador> utilizadores = new ArrayList<>();
	private LoginService loginService = new LoginService();
	
	@Override
	public void start(Stage primaryStage) {
	    this.primaryStage = primaryStage; 
	    primaryStage.setTitle("Quiz Educativo");

	    Image image = new Image("imagens/quiz-line-computer.png"); 
	    ImageView imageView = new ImageView(image);
	    imageView.setFitWidth(100); 
	    imageView.setPreserveRatio(true); 

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
	    layout.setStyle("-fx-background-color: #718063"); 
	    layout.getChildren().addAll(imageView, welcomeLabel, choiceLabel, loginButton, criarContaButton, exitButton); 
	    Scene scene = new Scene(layout, 630, 360);
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
	            errorMessageLabel.setText("Por favor preencha todos os campos.");
	            return;
	        }
	        
	        LoginService loginService = new LoginService(); 
	        if (loginService.login(email, password)) {
	            System.out.println("Login bem sucedido: Email: " + email);
	            loginStage.close(); 
	            Utilizador utilizador = loginService.getUtilizador(email); 
	            if (utilizador != null) {		            
	                if (utilizador.getTipoUtilizador() == Utilizador.TipoUtilizador.professor) {
	                    Professor professor = (Professor) utilizador; 
	                    MenuProfessor menuProfessor = new MenuProfessor(primaryStage, loginService);
	                    menuProfessor.setProfessor(professor);
	                    menuProfessor.start(new Stage());  
	                }
	                if (utilizador.getTipoUtilizador() == Utilizador.TipoUtilizador.administrador) {
	                    Administrador adm = (Administrador) utilizador;
	                    MenuAdministrador menuAdm = new MenuAdministrador(loginService, primaryStage);
	                    menuAdm.setAdministrador(adm);
	                    menuAdm.start(new Stage()); 
	                }
	                if (utilizador.getTipoUtilizador() == Utilizador.TipoUtilizador.aluno) {
	                    Aluno aluno = (Aluno) utilizador;
	                    MenuAluno menuAluno = new MenuAluno(primaryStage, loginService);
	                    menuAluno.setAluno(aluno);
	                    menuAluno.start(new Stage()); 
	                }
	            }
	        } else {
	            errorMessageLabel.setText("Email ou password incorretos!!");
	        }
	    });

	    Button voltarButton = new Button("Voltar");
	    voltarButton.setOnAction(e -> {
	        loginStage.close(); 
	        primaryStage.show(); 
	    });

	    VBox loginLayout = new VBox(10); 
	    loginLayout.setAlignment(Pos.CENTER); 
	    loginLayout.setStyle("-fx-padding: 20; -fx-background-color: #718063;"); 
	    loginLayout.getChildren().addAll(emailLabel, emailField, passwordLabel, passwordField, errorMessageLabel, loginButton, voltarButton);

	    Scene loginScene = new Scene(loginLayout, 400, 300); 
	    loginStage.setScene(loginScene);
	    loginStage.show();
	    primaryStage.hide(); 
	}


	private void telaCriarConta() {
	    Stage createAccountStage = new Stage();
	    createAccountStage.setTitle("Criar Conta");

	    GridPane createAccountLayout = new GridPane();
	    createAccountLayout.setVgap(15);
	    createAccountLayout.setHgap(15);
	    createAccountLayout.setAlignment(Pos.CENTER);
	    createAccountLayout.setStyle("-fx-background-color: #718063;"); 

	    Label titleLabel = new Label("Escolha o tipo de conta:");
	    titleLabel.setStyle("-fx-font-size: 24px; -fx-font-weight: bold; -fx-text-fill: #333;");

	    Button alunoButton = new Button("Criar Conta como Aluno");
	    Button professorButton = new Button("Criar Conta como Professor");
	    Button administradorButton = new Button("Criar Conta como Administrador");

	    alunoButton.setOnAction(e -> criarContaAluno(createAccountStage));
	    professorButton.setOnAction(e -> criarContaProfessor(createAccountStage));
	    administradorButton.setOnAction(e -> criarContaAdministrador(createAccountStage));

	    Button voltarButton = new Button("Voltar");
	    voltarButton.setOnAction(e -> {
	        createAccountStage.close();
	        primaryStage.show();
	    });

	    createAccountLayout.add(titleLabel, 0, 0, 2, 1);
	    createAccountLayout.add(alunoButton, 0, 1);
	    createAccountLayout.add(professorButton, 0, 2);
	    createAccountLayout.add(administradorButton, 0, 3);
	    createAccountLayout.add(voltarButton, 0, 4);

	    Scene selectionScene = new Scene(createAccountLayout, 400, 300);
	    createAccountStage.setScene(selectionScene);
	    createAccountStage.show();
	    primaryStage.hide();
	}

    
    public void criarContaAluno(Stage createAccountStage) {
        GridPane createAccountLayout = new GridPane();
        createAccountLayout.setVgap(10);
        createAccountLayout.setHgap(10); 
        createAccountLayout.setAlignment(Pos.CENTER); 
        createAccountLayout.setStyle("-fx-background-color: #718063;");

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
            int numeroAluno = Integer.parseInt(numeroAlunoField.getText());
            String password = passwordField.getText();

            Aluno novoAluno = new Aluno(nome, email, numeroAluno, password);
            utilizadores.add(novoAluno);
            LoginService loginService = new LoginService();
            boolean sucesso = loginService.criarAluno(novoAluno);
            if (sucesso) {
                System.out.println("Conta de aluno criada com sucesso!");
                createAccountStage.close(); 
                telaLogin();
            } else {
                System.out.println("Falha ao criar conta de aluno.");
            }
            System.out.println("Conta de aluno criada: " + novoAluno);
        });

        Button voltarButton = new Button("Voltar");
        voltarButton.setOnAction(e -> {
            createAccountStage.close();
            primaryStage.show();
        });

        createAccountLayout.add(nameLabel, 0, 0); 
        createAccountLayout.add(nameField, 1, 0); 
        createAccountLayout.add(emailLabel, 0, 1); 
        createAccountLayout.add(emailField, 1, 1); 
        createAccountLayout.add(numeroAlunoLabel, 0, 2);
        createAccountLayout.add(numeroAlunoField, 1, 2); 
        createAccountLayout.add(passwordLabel, 0, 1); 
        createAccountLayout.add(passwordField, 1, 3); 
        createAccountLayout.add(createAccountButton, 0, 4); 
        createAccountLayout.add(voltarButton, 1, 4); 

        Scene createAccountScene = new Scene(createAccountLayout, 630, 360);
        createAccountStage.setScene(createAccountScene);
        createAccountStage.show();
    }
    
    private void criarContaProfessor(Stage createAccountStage) {
        GridPane createAccountLayout = new GridPane();
        createAccountLayout.setVgap(10); 
        createAccountLayout.setHgap(10);
        createAccountLayout.setAlignment(Pos.CENTER); 
        createAccountLayout.setStyle("-fx-background-color: #718063;");

        Label nameLabel = new Label("Nome:");
        TextField nameField = new TextField();
        nameField.setPromptText("Escreva o seu nome");

        Label emailLabel = new Label("Email:");
        TextField emailField = new TextField();
        emailField.setPromptText("Escreva o seu email");

        Label numProfessorLabel = new Label("Número de Professor:");
        TextField numProfessorField = new TextField();
        numProfessorField.setPromptText("Escreva o seu número de professor");

        Label passwordLabel = new Label("Password:");
        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Escreva a sua password");

        Button createAccountButton = new Button("Criar Conta");
        createAccountButton.setOnAction(e -> {
            String nome = nameField.getText();
            String email = emailField.getText();
            int numProfessor = Integer.parseInt(numProfessorField.getText());
            String password = passwordField.getText();

            Professor novoProfessor = new Professor(nome, email, numProfessor, password);
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
            System.out.println("Conta de professor criada: " + novoProfessor);
        });

        Button voltarButton = new Button("Voltar");
        voltarButton.setOnAction(e -> {
            createAccountStage.close();
            primaryStage.show();
        });

        createAccountLayout.add(nameLabel, 0, 0); 
        createAccountLayout.add(nameField, 1, 0); 
        createAccountLayout.add(emailLabel, 0, 1); 
        createAccountLayout.add(emailField, 1, 1); 
        createAccountLayout.add(numProfessorLabel, 0, 2); 
        createAccountLayout.add(numProfessorField, 1, 2); 
        createAccountLayout.add(passwordLabel, 0, 3); 
        createAccountLayout.add(passwordField, 1, 3); 
        createAccountLayout.add(createAccountButton, 1, 4); 
        createAccountLayout.add(voltarButton, 1, 5); 

        Scene createAccountScene = new Scene(createAccountLayout, 630, 360);
        createAccountStage.setScene(createAccountScene);
        createAccountStage.show();
    }
    
    private void criarContaAdministrador(Stage createAccountStage) {
        GridPane createAccountLayout = new GridPane();
        createAccountLayout.setVgap(10); 				
        createAccountLayout.setHgap(10); 
        createAccountLayout.setAlignment(Pos.CENTER); 
        createAccountLayout.setStyle("-fx-background-color: #718063;");

        Label nameLabel = new Label("Nome:");
        TextField nameField = new TextField();
        nameField.setPromptText("Escreva o seu nome");

        Label emailLabel = new Label("Email:");
        TextField emailField = new TextField();
        emailField.setPromptText("Escreva o seu email");

        Label passwordLabel = new Label("Password:");
        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Escreva a sua password");

        Button createAccountButton = new Button("Criar Conta");
        createAccountButton.setOnAction(e -> {
            String nome = nameField.getText();
            String email = emailField.getText();
            String password = passwordField.getText();

            Administrador novoAdministrador = new Administrador(nome, email, password);
            utilizadores.add(novoAdministrador);
            LoginService loginService = new LoginService();
            boolean sucesso = loginService.criarAdministrador(novoAdministrador);
            if (sucesso) {
                System.out.println("Conta de administrador criada com sucesso!");
                createAccountStage.close(); 
                telaLogin();
            } else {
                System.out.println("Falha ao criar conta de administrador.");
            }
            System.out.println("Conta de administrador criada: " + novoAdministrador);
        });

        Button voltarButton = new Button("Voltar");
        voltarButton.setOnAction(e -> {
            createAccountStage.close();
            primaryStage.show();
        });

        createAccountLayout.add(nameLabel, 0, 0); 
        createAccountLayout.add(nameField, 1, 0); 
        createAccountLayout.add(emailLabel, 0, 1); 
        createAccountLayout.add(emailField, 1, 1); 
        createAccountLayout.add(passwordLabel, 0, 2); 
        createAccountLayout.add(passwordField, 1, 2); 
        createAccountLayout.add(createAccountButton, 1, 3); 
        createAccountLayout.add(voltarButton, 1, 4); 

        Scene createAccountScene = new Scene(createAccountLayout, 630, 360);
        createAccountStage.setScene(createAccountScene);
        createAccountStage.show();
    }
    
    
    public static void main(String[] args) {
        launch(args);
    }

}
