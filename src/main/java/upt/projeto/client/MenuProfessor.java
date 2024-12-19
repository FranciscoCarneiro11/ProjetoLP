package upt.projeto.client;

import java.util.List;
import java.util.stream.Collectors;

import javafx.beans.property.SimpleStringProperty;
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
import upt.projeto.modelDTO.DisciplinaDTO;
import upt.projeto.model.Pergunta;
import upt.projeto.model.Professor;
import upt.projeto.model.Quiz;
import upt.projeto.modelDTO.QuizDTO;
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
        Button verQuizzesButton = new Button("Ver quizzes criados");
        Button verRespostasButton = new Button("Ver Respostas dos Alunos");
        Button logoutButton = new Button("Sair");
        logoutButton.setStyle("-fx-text-fill: red;");
        
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
        	//editarQuiz();
        });        
        
        verQuizzesButton.setOnAction(e->{
            verQuizzes();
        });
        
        
        logoutButton.setOnAction(e -> {
            menuProfStage.close(); 
            primaryStage.show();     
        });

        VBox layout = new VBox(10);
        layout.setAlignment(Pos.CENTER);
        layout.setStyle("-fx-background-color: #718063"); 
        layout.getChildren().addAll(welcomeLabel, verDadosButton, verProfessoresButton,associarDisciplinaButton, criarQuizButton, editarQuizButton, verQuizzesButton, verRespostasButton, logoutButton);

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

            ObservableList<Professor> data = FXCollections.observableArrayList(professor);    //utilizado para fornecer apenas os dados do professor que deu login
            tableView.setItems(data);

            Button voltarButton = new Button("Voltar");
            voltarButton.setStyle("-fx-text-fill: red;");
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

        TableView<Professor> tableView = new TableView<>();

        TableColumn<Professor, String> nomeColuna = new TableColumn<>("Nome");
        nomeColuna.setCellValueFactory(new PropertyValueFactory<>("nome"));

        TableColumn<Professor, String> emailColuna = new TableColumn<>("Email");
        emailColuna.setCellValueFactory(new PropertyValueFactory<>("email"));

        TableColumn<Professor, Integer> numeroColuna = new TableColumn<>("Número de Professor");
        numeroColuna.setCellValueFactory(new PropertyValueFactory<>("numProfessor"));

        tableView.getColumns().addAll(nomeColuna, emailColuna, numeroColuna);

        if (professores != null && !professores.isEmpty()) {
            tableView.getItems().addAll(professores);
        } else {
            System.out.println("Nenhum professor encontrado!");
        }

        Button voltarButton = new Button("Voltar");
        voltarButton.setStyle("-fx-text-fill: red;");
        voltarButton.setOnAction(e -> professoresStage.close());

        VBox layout = new VBox(10);
        layout.setAlignment(Pos.CENTER);
        layout.getChildren().addAll(tableView, voltarButton);

        Scene scene = new Scene(layout, 640, 400);
        professoresStage.setScene(scene);
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
        List<DisciplinaDTO> disciplinasDTO = loginService.getTodasDisciplinas();

        for (DisciplinaDTO dto : disciplinasDTO) {
            Disciplina disciplina = new Disciplina();
            disciplina.setId(dto.getId());
            disciplina.setNome(dto.getNome());
            disciplinaChoiceBox.getItems().add(disciplina);
        }
        disciplinaChoiceBox.getSelectionModel().selectFirst();

        Button associarButton = new Button("Associar");
        associarButton.setOnAction(e -> {
            Professor professorSelecionado = professorChoiceBox.getSelectionModel().getSelectedItem();
            Disciplina disciplinaSelecionada = disciplinaChoiceBox.getSelectionModel().getSelectedItem();

            if (professorSelecionado != null && disciplinaSelecionada != null) {
                Long professorId = (long) professorSelecionado.getId();
                Long disciplinaId = disciplinaSelecionada.getId();

                System.out.println("A associar disciplina ID: " + disciplinaId + " ao professor ID: " + professorId);

                boolean sucesso = loginService.associarDisciplinaProfessor(professorId, disciplinaId);
                if (sucesso) {
                    System.out.println("Disciplina associada com sucesso!!");
                } else {
                    System.out.println("Falha ao associar disciplina!!");
                }
            } else {
                System.out.println("Selecione um professor e uma disciplina válidos!!");
            }
            associarDisciplinaStage.close();
        });

        Button voltarButton = new Button("Voltar");
        voltarButton.setStyle("-fx-text-fill: red;");
        voltarButton.setOnAction(e -> associarDisciplinaStage.close());

        associarDisciplinaLayout.getChildren().addAll(professorLabel, professorChoiceBox,disciplinaLabel, disciplinaChoiceBox,associarButton, voltarButton);

        Scene associarDisciplinaScene = new Scene(associarDisciplinaLayout, 640, 360);
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
        ChoiceBox<DisciplinaDTO> disciplinaChoiceBox = new ChoiceBox<>();
        disciplinaChoiceBox.getItems().addAll(loginService.getTodasDisciplinas());
        disciplinaChoiceBox.getSelectionModel().selectFirst();

        Button criarButton = new Button("Criar");
        criarButton.setOnAction(e -> {
            String titulo = tituloTextField.getText();
            Professor professorSelecionado = professorChoiceBox.getSelectionModel().getSelectedItem();
            DisciplinaDTO disciplinaSelecionadaDTO = disciplinaChoiceBox.getSelectionModel().getSelectedItem();

            if (!titulo.isEmpty() && professorSelecionado != null && disciplinaSelecionadaDTO != null) {
                Disciplina disciplinaSelecionada = new Disciplina();
                disciplinaSelecionada.setId(disciplinaSelecionadaDTO.getId());
                disciplinaSelecionada.setNome(disciplinaSelecionadaDTO.getNome());

                Quiz novoQuiz = new Quiz();
                novoQuiz.setTitulo(titulo);
                novoQuiz.setProfessor(professorSelecionado);
                novoQuiz.setDisciplina(disciplinaSelecionada);

                boolean sucesso = loginService.criarQuiz(novoQuiz);
                if (sucesso) {
                    System.out.println("Quiz criado e guardado com sucesso: " + titulo);
                } else {
                    System.out.println("Falha ao guardar o quiz!!");
                }
            } else {
                System.out.println("Insira um título válido e/ou selecione um professor e uma disciplina!!");
            }
            criarQuizStage.close();
        });

        criarQuizLayout.getChildren().addAll(tituloLabel, tituloTextField,professorLabel, professorChoiceBox,disciplinaLabel, disciplinaChoiceBox,criarButton);
        
        Button voltarButton = new Button("Voltar");
        voltarButton.setStyle("-fx-text-fill: red;");
        voltarButton.setOnAction(e -> criarQuizStage.close());

        Scene criarQuizScene = new Scene(criarQuizLayout, 640, 360);
        criarQuizStage.setScene(criarQuizScene);
        criarQuizStage.show();
    }
          
    private void verQuizzes() {
        Stage mostrarQuizzesStage = new Stage();
        mostrarQuizzesStage.setTitle("Quizzes Criados");

        TableView<QuizDTO> tableView = new TableView<>();

        TableColumn<QuizDTO, String> tituloColuna = new TableColumn<>("Título");
        tituloColuna.setCellValueFactory(new PropertyValueFactory<>("titulo"));

        TableColumn<QuizDTO, Long> professorIdColuna = new TableColumn<>("ID Professor");
        professorIdColuna.setCellValueFactory(new PropertyValueFactory<>("professorId"));

        TableColumn<QuizDTO, Long> disciplinaIdColuna = new TableColumn<>("ID Disciplina");
        disciplinaIdColuna.setCellValueFactory(new PropertyValueFactory<>("disciplinaId"));

        tableView.getColumns().addAll(tituloColuna, professorIdColuna, disciplinaIdColuna);

        List<QuizDTO> quizzes = loginService.getTodosQuizzes();
        tableView.getItems().addAll(quizzes);

        VBox layout = new VBox(tableView);
        Scene scene = new Scene(layout, 640, 360);
        mostrarQuizzesStage.setScene(scene);
        mostrarQuizzesStage.show();
    }




    
    
}
