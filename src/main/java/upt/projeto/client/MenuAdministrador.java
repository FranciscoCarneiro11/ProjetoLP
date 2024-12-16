package upt.projeto.client;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import upt.projeto.model.Administrador;
import upt.projeto.model.Curso;
import upt.projeto.service.LoginService;
import javafx.geometry.Pos;
import javafx.scene.Scene;

public class MenuAdministrador {
	
	private LoginService loginService;
	private Stage primaryStage; 
	private Administrador administrador;

	public MenuAdministrador(LoginService loginService, Stage primaryStage) {
		this.loginService = loginService;
		this.primaryStage = primaryStage;
	}
	
	 public void setAdministrador(Administrador administrador) {
	        this.administrador = administrador;
	    }

	
	 public void start(Stage menuAdmStage) {
	        menuAdmStage.setTitle("Menu do Administrador");

	        Label welcomeLabel = new Label("Bem-vindo, Administrador!");
	        welcomeLabel.setStyle("-fx-font-size: 28px; -fx-font-weight: bold;");

	        Button criarCursoButton = new Button("Criar Curso");
	        Button criarAnoButton = new Button("Criar Ano de Escolaridade");
	        Button criarDisciplinaButton = new Button("Criar Disciplina");
	        Button voltarButton = new Button("Voltar ao Menu Principal");
	        Button logoutButton = new Button("Sair");

	        //Adicionar ações dos botoes
	        
	        criarCursoButton.setOnAction(e-> {
	        	criarCurso();
	        });
	        
	        logoutButton.setOnAction(e -> {
	            menuAdmStage.close(); 
	            primaryStage.show(); 
	        });

	        VBox layout = new VBox(10);
	        layout.setAlignment(Pos.CENTER);
	        layout.getChildren().addAll(welcomeLabel,criarCursoButton, criarAnoButton, criarDisciplinaButton, voltarButton, logoutButton);

	        Scene scene = new Scene(layout, 1280, 720);
	        menuAdmStage.setScene(scene);
	        menuAdmStage.show();
	    }
	 
	 private void criarCurso() {
		    Stage criarCursoStage = new Stage();
		    criarCursoStage.setTitle("Criar Curso");

		    Label nomeCursoLabel = new Label("Nome do Curso:");
		    TextField nomeCursoField = new TextField();
		    nomeCursoField.setText("Insira o nome do curso");

		    Button criarButton = new Button("Criar");
		    criarButton.setOnAction(e -> {
		        String nomeCurso = nomeCursoField.getText();
		        if (!nomeCurso.isEmpty()) {
		            boolean sucesso = loginService.criarCurso(new Curso(nomeCurso, administrador));
		            if (sucesso) {
		                System.out.println("Curso criado com sucesso: " + nomeCurso);
		                criarCursoStage.close(); 
		            } else {
		                System.out.println("Falha ao criar curso.");
		            }
		        } else {
		            System.out.println("Por favor, insira um nome para o curso.");
		        }
		    });

		    Button cancelarButton = new Button("Cancelar");
		    cancelarButton.setOnAction(e -> criarCursoStage.close());

		    VBox layout = new VBox(10);
		    layout.setAlignment(Pos.CENTER);
		    layout.getChildren().addAll(nomeCursoLabel, nomeCursoField, criarButton, cancelarButton);

		    Scene scene = new Scene(layout, 1280, 720);
		    criarCursoStage.setScene(scene);
		    criarCursoStage.show();
		}
	 
	 //adicionar o ver cursos por administrador (fazer em ultimo )
}
