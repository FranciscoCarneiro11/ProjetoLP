package upt.projeto.client;

import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import upt.projeto.model.Disciplina;
import upt.projeto.model.Pergunta;
import upt.projeto.model.Professor;
import upt.projeto.model.Quiz;
import upt.projeto.service.LoginService;

public class MenuProfessor {

    private Stage primaryStage; 
    private LoginService loginService;
    private Professor  professor;

    public MenuProfessor() {
    }

    public MenuProfessor(Stage primaryStage, LoginService loginService) {
        this.primaryStage = primaryStage;
        this.loginService = loginService;
    }
    
    public void setProfessor(Professor  professor) {
        this.professor = professor;
    }

    public void start(Stage menuProfStage) {
        menuProfStage.setTitle("Menu do Professor");

        Label welcomeLabel = new Label("Bem-vindo, Professor!");
        welcomeLabel.setStyle("-fx-font-size: 28px; -fx-font-weight: bold;");

        Button verDadosButton = new Button("Ver Dados Pessoais");
        Button verProfessoresButton = new Button("Ver Todos os Professores");
        Button associarDisciplinaButton = new Button("Associar disciplina ao professor");
        Button criarQuizButton = new Button("Criar Quiz");
        Button editarQuizButton = new Button("Editar Quiz");
        Button verRespostasButton = new Button("Ver Respostas dos Alunos");
        Button logoutButton = new Button("Sair");
        
        verDadosButton.setOnAction(e ->{
        	mostrarDadosPessoais(professor);
        });
        
        verProfessoresButton.setOnAction(e->{
        	verProfessores();
        });
        
        associarDisciplinaButton.setOnAction(e->{
        	associarDisciplinaProfessor();
        }); 
        
        criarQuizButton.setOnAction(e->{
        	criarQuiz();
        });
        
        editarQuizButton.setOnAction(e->{
        	editarQuiz();
        });        
        logoutButton.setOnAction(e -> {
            menuProfStage.close(); 
            primaryStage.show(); 
        });

        VBox layout = new VBox(10);
        layout.setAlignment(Pos.CENTER);
        layout.setStyle("-fx-background-color: #718063"); 
        layout.getChildren().addAll(welcomeLabel, verDadosButton, verProfessoresButton,associarDisciplinaButton, criarQuizButton,editarQuizButton, verRespostasButton, logoutButton);

        Scene scene = new Scene(layout, 854, 480);
        menuProfStage.setScene(scene);
        menuProfStage.show();
    }
    
   
    private void mostrarDadosPessoais(Professor professor) {
    	if (professor != null) {
            Stage dadosPessoaisStage = new Stage();
            dadosPessoaisStage.setTitle("Dados Pessoais");
            
            TableView<Professor> tableView = new TableView<>();

            TableColumn<Professor, String> nomeColuna = new TableColumn<>("Nome");
            nomeColuna.setCellValueFactory(new PropertyValueFactory<>("nome"));

            TableColumn<Professor, String> emailColuna = new TableColumn<>("Email");
            emailColuna.setCellValueFactory(new PropertyValueFactory<>("email"));

            TableColumn<Professor, Integer> numProfessorColuna = new TableColumn<>("Número de Professor");
            numProfessorColuna.setCellValueFactory(new PropertyValueFactory<>("numProfessor"));

            tableView.getColumns().addAll(nomeColuna, emailColuna, numProfessorColuna);

            ObservableList<Professor> data = FXCollections.observableArrayList(professor); 
            tableView.setItems(data);

            Button voltarButton = new Button("Voltar");
            voltarButton.setOnAction(e -> dadosPessoaisStage.close());

            VBox dadosLayout = new VBox(10);
            dadosLayout.setAlignment(Pos.CENTER);
            dadosLayout.getChildren().addAll(tableView, voltarButton);

            Scene dadosScene = new Scene(dadosLayout, 854, 480);
            dadosPessoaisStage.setScene(dadosScene);
            dadosPessoaisStage.show();
        } else {
            System.out.println("Professor não encontrado!!");
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
    
    private void associarDisciplinaProfessor() {
        Stage associarDisciplinaStage = new Stage();
        associarDisciplinaStage.setTitle("Associar Disciplina a Professor");

        VBox associarDisciplinaLayout = new VBox(10);
        associarDisciplinaLayout.setAlignment(Pos.CENTER);

        Label professorLabel = new Label("Selecione o Professor:");
        ChoiceBox<Professor> professorChoiceBox = new ChoiceBox<>();
        professorChoiceBox.getItems().addAll(loginService.getTodosProfessores());
        professorChoiceBox.getSelectionModel().selectFirst();

        Label disciplinaLabel = new Label("Selecione a Disciplina:");
        ChoiceBox<Disciplina> disciplinaChoiceBox = new ChoiceBox<>();
        disciplinaChoiceBox.getItems().addAll(loginService.getTodasDisciplinas());
        disciplinaChoiceBox.getSelectionModel().selectFirst();

        Button associarButton = new Button("Associar");
        associarButton.setOnAction(e -> {
            Professor professorSelecionado = professorChoiceBox.getSelectionModel().getSelectedItem();
            Disciplina disciplinaSelecionada = disciplinaChoiceBox.getSelectionModel().getSelectedItem();

            if (professorSelecionado != null && disciplinaSelecionada != null) {
                Long professorId = (long) professorSelecionado.getId(); 
                Long disciplinaId = disciplinaSelecionada.getId(); 
 
                System.out.println("Associando disciplina ID: " + disciplinaId + " ao professor ID: " + professorId);

                boolean sucesso = loginService.associarDisciplinaProfessor(professorId, disciplinaId);
                if (sucesso) {
                    System.out.println("Disciplina associada com sucesso.");
                } else {
                    System.out.println("Falha ao associar disciplina.");
                }
            } else {
                System.out.println("Selecione um professor e uma disciplina válidos.");
            }
            associarDisciplinaStage.close();
        });

        associarDisciplinaLayout.getChildren().addAll(professorLabel, professorChoiceBox, disciplinaLabel, disciplinaChoiceBox, associarButton);

        Scene associarDisciplinaScene = new Scene(associarDisciplinaLayout, 400, 300);
        associarDisciplinaStage.setScene(associarDisciplinaScene);
        associarDisciplinaStage.show();
    }
    
    
    private void criarQuiz() {
        Stage criarQuizStage = new Stage();
        criarQuizStage.setTitle("Criar Quiz");

        VBox criarQuizLayout = new VBox(10);
        criarQuizLayout.setAlignment(Pos.CENTER);

        Label tituloLabel = new Label("Título do Quiz:");
        TextField tituloTextField = new TextField();

        Label professorLabel = new Label("Selecione o Professor:");
        ChoiceBox<Professor> professorChoiceBox = new ChoiceBox<>();
        professorChoiceBox.getItems().addAll(loginService.getTodosProfessores());
        professorChoiceBox.getSelectionModel().selectFirst();

        Label disciplinaLabel = new Label("Selecione a Disciplina:");
        ChoiceBox<Disciplina> disciplinaChoiceBox = new ChoiceBox<>();
        disciplinaChoiceBox.getItems().addAll(loginService.getTodasDisciplinas());
        disciplinaChoiceBox.getSelectionModel().selectFirst();

        Button criarButton = new Button("Criar");
        criarButton.setOnAction(e -> {
            String titulo = tituloTextField.getText();
            Professor professorSelecionado = professorChoiceBox.getSelectionModel().getSelectedItem();
            Disciplina disciplinaSelecionada = disciplinaChoiceBox.getSelectionModel().getSelectedItem();

            if (!titulo.isEmpty() && professorSelecionado != null && disciplinaSelecionada != null) {
                Quiz novoQuiz = new Quiz();
                novoQuiz.setTitulo(titulo);
                novoQuiz.setProfessor(professorSelecionado);
                novoQuiz.setDisciplina(disciplinaSelecionada);

                boolean sucesso = loginService.criarQuiz(novoQuiz);
                if (sucesso) {
                    System.out.println("Quiz criado e salvo com sucesso: " + titulo);
                } else {
                    System.out.println("Falha ao salvar o quiz.");
                }
            } else {
                System.out.println("Insira um título válido e selecione um professor e uma disciplina.");
            }
            criarQuizStage.close();
        });

        criarQuizLayout.getChildren().addAll(tituloLabel, tituloTextField, professorLabel, professorChoiceBox, disciplinaLabel, disciplinaChoiceBox, criarButton);

        Scene criarQuizScene = new Scene(criarQuizLayout, 400, 300);
        criarQuizStage.setScene(criarQuizScene);
        criarQuizStage.show();
    }

    private void editarQuiz() {
        Stage editarQuizStage = new Stage();
        editarQuizStage.setTitle("Editar Quiz");

        VBox editarQuizLayout = new VBox(10);
        editarQuizLayout.setAlignment(Pos.CENTER);

        Label quizLabel = new Label("Selecione o Quiz:");
        ChoiceBox<Quiz> quizChoiceBox = new ChoiceBox<>();
        quizChoiceBox.getItems().addAll(loginService.getTodosQuizzes());
        quizChoiceBox.getSelectionModel().selectFirst();

        Label perguntaLabel = new Label("Texto da Pergunta:");
        TextField perguntaTextField = new TextField();

        Label opcoesLabel = new Label("Opções de Resposta:");
        TextField opcaoATextField = new TextField("Opção A");
        TextField opcaoBTextField = new TextField("Opção B");
        TextField opcaoCTextField = new TextField("Opção C");
        TextField opcaoDTextField = new TextField("Opção D");

        Label corretaLabel = new Label("Opção Correta:");
        ChoiceBox<String> corretaChoiceBox = new ChoiceBox<>();
        corretaChoiceBox.getItems().addAll("Opção A", "Opção B", "Opção C", "Opção D");
        corretaChoiceBox.getSelectionModel().selectFirst();

        Button salvarButton = new Button("Salvar");
        salvarButton.setOnAction(e -> {
            Quiz quizSelecionado = quizChoiceBox.getSelectionModel().getSelectedItem();
            String textoPergunta = perguntaTextField.getText();
            String opcaoA = opcaoATextField.getText();
            String opcaoB = opcaoBTextField.getText();
            String opcaoC = opcaoCTextField.getText();
            String opcaoD = opcaoDTextField.getText();
            String opcaoCorreta = corretaChoiceBox.getSelectionModel().getSelectedItem();

            if (quizSelecionado != null && !textoPergunta.isEmpty() && !opcaoA.isEmpty() && !opcaoB.isEmpty() && !opcaoC.isEmpty() && !opcaoD.isEmpty()) {
                Pergunta novaPergunta = new Pergunta();
                novaPergunta.setQuestao(textoPergunta);
                novaPergunta.setOpcaoA(opcaoA);
                novaPergunta.setOpcaoB(opcaoB);
                novaPergunta.setOpcaoC(opcaoC);
                novaPergunta.setOpcaoD(opcaoD);
                novaPergunta.setRespostaCorreta(opcaoCorreta);

                Long professorId = (long) professor.getId(); 

                boolean sucesso = loginService.editarQuiz(quizSelecionado.getId(), novaPergunta, professorId);
                if (sucesso) {
                    System.out.println("Pergunta adicionada com sucesso ao quiz.");
                } else {
                    System.out.println("Falha ao adicionar pergunta ao quiz.");
                }
            } else {
                System.out.println("Preencha todos os campos corretamente.");
            }
            editarQuizStage.close();
        });

        editarQuizLayout.getChildren().addAll(quizLabel, quizChoiceBox, perguntaLabel, perguntaTextField, opcoesLabel, opcaoATextField, opcaoBTextField, opcaoCTextField, opcaoDTextField, corretaLabel, corretaChoiceBox, salvarButton);

        Scene editarQuizScene = new Scene(editarQuizLayout, 400, 500);
        editarQuizStage.setScene(editarQuizScene);
        editarQuizStage.show();
    }
    
    
}
