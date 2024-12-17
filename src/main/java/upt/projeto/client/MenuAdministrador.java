package upt.projeto.client;

import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import upt.projeto.model.Administrador;
import upt.projeto.model.AnoEscolaridade;
import upt.projeto.model.Curso;
import upt.projeto.service.LoginService;

import java.util.List;

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
	        Button verCursosButton = new Button("Ver Cursos");
	        Button verAnosButton = new Button("Ver Anos de Escolaridade");
	        Button verDisciplinasButton = new Button("Ver Disciplinas");
	        Button voltarButton = new Button("Voltar ao Menu Principal");
	        Button logoutButton = new Button("Sair");

	        //Adicionar ações dos botoes
	        
	        criarCursoButton.setOnAction(e-> {
	        	criarCurso();
	        });
	        
	        criarAnoButton.setOnAction(e-> {
	        	criarAnoEscolaridade();
	        });
	        
	        criarDisciplinaButton.setOnAction(e->{
	        	criarDisciplina();
	        });        
	        
	        verCursosButton.setOnAction(e->{
	        	verCursos();
	        });  
	        
	        verAnosButton.setOnAction(e->{
	        	verAnosEscolaridade();
	        });  
	        
	        verDisciplinasButton.setOnAction(e->{
	        	verDisciplinas();
	        });  
	        
	        logoutButton.setOnAction(e -> {
	            menuAdmStage.close(); 
	            primaryStage.show(); 
	        });

	        VBox layout = new VBox(10);
	        layout.setAlignment(Pos.CENTER);
		     layout.setStyle("-fx-background-color: #718063"); 
	        layout.getChildren().addAll(welcomeLabel,criarCursoButton, criarAnoButton, criarDisciplinaButton,verCursosButton,verAnosButton,verDisciplinasButton, voltarButton, logoutButton);

	        Scene scene = new Scene(layout, 854, 480);
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
		    layout.setStyle("-fx-background-color: #718063"); 
		    layout.getChildren().addAll(nomeCursoLabel, nomeCursoField, criarButton, cancelarButton);

		    Scene scene = new Scene(layout, 854, 480);
		    criarCursoStage.setScene(scene);
		    criarCursoStage.show();
		    primaryStage.hide();
		}
	 
	 private void criarAnoEscolaridade() {
		    Stage criarAnoStage = new Stage();
		    criarAnoStage.setTitle("Criar Ano de Escolaridade");

		    Label cursoLabel = new Label("Selecione o Curso:");
		    ComboBox<Curso> cursoComboBox = new ComboBox<>();

		    List<Curso> cursos = loginService.getTodosCursos();
		    if (cursos.isEmpty()) {
		        System.out.println("Nenhum curso encontrado.");
		    } else {
		        System.out.println("Cursos recebidos: " + cursos);
		        cursoComboBox.getItems().addAll(cursos);
		    }
		    Label anoLabel = new Label("Ano de Escolaridade:");
		    TextField anoField = new TextField();
		    anoField.setPromptText("Insira o ano de escolaridade");

		    Button criarButton = new Button("Criar");
		    criarButton.setOnAction(e -> {
		        String anoTexto = anoField.getText();
		        Curso cursoSelecionado = cursoComboBox.getValue();

		        if (cursoSelecionado != null && !anoTexto.isEmpty()) {
		            try {
		                int anoEscolaridade = Integer.parseInt(anoTexto);

		                AnoEscolaridade novoAno = new AnoEscolaridade(anoEscolaridade, cursoSelecionado);
		                
		                boolean sucesso = loginService.criarAnoEscolaridade(novoAno);
		                if (sucesso) {
		                    System.out.println("Ano de escolaridade criado com sucesso: " + anoEscolaridade);
		                    criarAnoStage.close();
		                } else {
		                    System.out.println("Falha ao criar ano de escolaridade.");
		                }
		            } catch (NumberFormatException ex) {
		                System.out.println("Por favor, insira um número válido para o ano de escolaridade.");
		            }
		        } else {
		            System.out.println("Por favor, selecione um curso e insira um ano de escolaridade.");
		        }
		    });

		    Button cancelarButton = new Button("Cancelar");
		    cancelarButton.setOnAction(e -> criarAnoStage.close());

		    VBox layout = new VBox(10);
		    layout.setAlignment(Pos.CENTER);
		    layout.getChildren().addAll(cursoLabel, cursoComboBox, anoLabel, anoField, criarButton, cancelarButton);

		    Scene scene = new Scene(layout, 854, 480);
		    criarAnoStage.setScene(scene);
		    criarAnoStage.show();
		    primaryStage.hide();
		}
	 
	 private void criarDisciplina() {
		    
		}

	    private void verCursos() {
	     
	    }

	    private void verAnosEscolaridade() {
	     
	    }

	    private void verDisciplinas() {
	       
	    }
	    
	    
	   

	 
}
