package upt.projeto.client;

import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import upt.projeto.model.Aluno;
import upt.projeto.model.AnoEscolaridade;
import upt.projeto.model.Curso;
import upt.projeto.model.Disciplina;
import upt.projeto.modelDTO.DisciplinaDTO;
import upt.projeto.service.LoginService;

public class MenuAluno {
	 
	 private Stage primaryStage; 
	 private LoginService loginService;
	 private Aluno aluno;
	 
	 public MenuAluno() {
	 }
	 
	 public MenuAluno(Stage primaryStage, LoginService loginService) {
		 this.primaryStage = primaryStage;
		 this.loginService = loginService;
	 }
	 
	 public void setAluno(Aluno aluno) {
		 this.aluno = aluno;
	 }
	 
	 public void start(Stage menuAlunoStage) {
		 menuAlunoStage.setTitle("Menu do Aluno");

	     Label welcomeLabel = new Label("Bem-vindo, Aluno!");
	     welcomeLabel.setStyle("-fx-font-size: 28px; -fx-font-weight: bold;");

	     Button verDadosButton = new Button("Ver Dados Pessoais");
	     Button verAlunosButton = new Button("Ver Todos os alunos");
	     Button associarAlunoButton = new Button("Associar aluno a um curso/ano/disciplina");
	     Button verQuizzesButton = new Button("Ver quizzes");
	     Button responderQuizButton = new Button("Responder a um Quiz");
	     Button verPontuacaoButton = new Button("Ver pontuação de todos que responderam ao quiz");
	     Button logoutButton = new Button("Sair");

	     verDadosButton.setOnAction(e->{
	    	 mostrarDadosAluno(aluno);
	     });
	     
	     verAlunosButton.setOnAction(e ->{
	    	 verAlunos();
	     });
	     
	      associarAlunoButton.setOnAction(e->{
	    	  associarAluno();
	     });
	    
	     logoutButton.setOnAction(e -> {
	    	 menuAlunoStage.close(); 
	    	 primaryStage.show(); 
	     });

	     VBox layout = new VBox(10);
	     layout.setAlignment(Pos.CENTER);
	     layout.setStyle("-fx-background-color: #718063"); 
	     layout.getChildren().addAll(welcomeLabel, verDadosButton, verAlunosButton,associarAlunoButton,verQuizzesButton, responderQuizButton, verPontuacaoButton, logoutButton);

	     Scene scene = new Scene(layout, 854, 480);
	     menuAlunoStage.setScene(scene);
	     menuAlunoStage.show();
	 }
	 
	 private void mostrarDadosAluno(Aluno aluno) {
		 if (aluno != null) {
	            Stage dadosPessoaisStage = new Stage();
	            dadosPessoaisStage.setTitle("Dados Pessoais");
	            
	            TableView<Aluno> tableView = new TableView<>();

	            TableColumn<Aluno, String> nomeColuna = new TableColumn<>("Nome");
	            nomeColuna.setCellValueFactory(new PropertyValueFactory<>("nome"));

	            TableColumn<Aluno, String> emailColuna = new TableColumn<>("Email");
	            emailColuna.setCellValueFactory(new PropertyValueFactory<>("email"));

	            TableColumn<Aluno, Integer> numAlunoColuna = new TableColumn<>("Número de Aluno");
	            numAlunoColuna.setCellValueFactory(new PropertyValueFactory<>("numAluno"));

	            tableView.getColumns().addAll(nomeColuna, emailColuna, numAlunoColuna);

	            ObservableList<Aluno> data = FXCollections.observableArrayList(aluno); 
	            tableView.setItems(data);

	            Button voltarButton = new Button("Voltar");
	            voltarButton.setOnAction(e -> dadosPessoaisStage.close());

	            VBox dadosLayout = new VBox(10);
	            dadosLayout.setAlignment(Pos.CENTER);
	            dadosLayout.getChildren().addAll(tableView, voltarButton);

	            Scene dadosScene = new Scene(dadosLayout, 640, 360);
	            dadosPessoaisStage.setScene(dadosScene);
	            dadosPessoaisStage.show();
	        } else {
	            System.out.println("Aluno não encontrado.");
	        }
	 }
	 
	 private void verAlunos() {
		    List<Aluno> alunos = loginService.getTodosAlunos();

		    Stage alunosStage = new Stage();
		    alunosStage.setTitle("Lista de Alunos");

		    TableView<Aluno> tableView = new TableView<>();
		    ObservableList<Aluno> observableAlunos = FXCollections.observableArrayList(alunos);

		    TableColumn<Aluno, String> nomeColumn = new TableColumn<>("Nome");
		    nomeColumn.setCellValueFactory(new PropertyValueFactory<>("nome"));

		    TableColumn<Aluno, String> emailColumn = new TableColumn<>("Email");
		    emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));

		    TableColumn<Aluno, String> numeroColumn = new TableColumn<>("Número de Aluno");
		    numeroColumn.setCellValueFactory(new PropertyValueFactory<>("numAluno"));

		    tableView.getColumns().addAll(nomeColumn, emailColumn, numeroColumn);
		    tableView.setItems(observableAlunos);

		    VBox alunosLayout = new VBox(10);
		    alunosLayout.setAlignment(Pos.CENTER);
		    alunosLayout.getChildren().addAll(tableView);

		    Button voltarButton = new Button("Voltar");
		    voltarButton.setStyle("-fx-text-fill: red;");
		    voltarButton.setOnAction(e -> {
		        alunosStage.close();
		    });

		    alunosLayout.getChildren().add(voltarButton);

		    Scene alunosScene = new Scene(alunosLayout, 640, 360);
		    alunosStage.setScene(alunosScene);
		    alunosStage.show();
		}
	 
	 private void associarAluno() {
		    Stage associarAlunoStage = new Stage();
		    associarAlunoStage.setTitle("Associar Aluno a Curso, Ano e Disciplinas");

		    VBox layout = new VBox(10);
		    layout.setAlignment(Pos.CENTER);

		    Label cursoLabel = new Label("Selecione o Curso:");
		    ChoiceBox<Curso> cursoChoiceBox = new ChoiceBox<>();
		    cursoChoiceBox.getItems().addAll(loginService.getTodosCursos());

		    Label anoEscolaridadeLabel = new Label("Selecione o Ano Escolaridade:");
		    ChoiceBox<AnoEscolaridade> anoEscolaridadeChoiceBox = new ChoiceBox<>();
		    
		    cursoChoiceBox.setOnAction(e -> {															//utilizado para apenas aparecer os anos de escolaridade de um curso em si
		        Curso cursoSelecionado = cursoChoiceBox.getSelectionModel().getSelectedItem();
		        if (cursoSelecionado != null) {
		            List<AnoEscolaridade> anosEscolaridade = loginService.getAnosEscolaridadePorCurso((long) cursoSelecionado.getId());
		            anoEscolaridadeChoiceBox.getItems().setAll(anosEscolaridade);
		        }
		    });

		    Label disciplinaLabel = new Label("Selecione a/as Disciplina(s):");
		    ListView<Disciplina> disciplinaListView = new ListView<>();
		    disciplinaListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		    List<DisciplinaDTO> disciplinasDTO = loginService.getTodasDisciplinas();
		    List<Disciplina> disciplinas = new ArrayList<>();

		    for (DisciplinaDTO dto : disciplinasDTO) {
		        Disciplina disciplina = new Disciplina();
		        disciplina.setId(dto.getId());
		        disciplina.setNome(dto.getNome());
		        disciplinas.add(disciplina);
		    }
		    disciplinaListView.getItems().addAll(disciplinas);

		    Button associarButton = new Button("Associar");
		    associarButton.setOnAction(e -> {
		        Curso cursoSelecionado = cursoChoiceBox.getSelectionModel().getSelectedItem();
		        AnoEscolaridade anoEscolaridadeSelecionado = anoEscolaridadeChoiceBox.getSelectionModel().getSelectedItem();
		        List<Disciplina> disciplinasSelecionadas = disciplinaListView.getSelectionModel().getSelectedItems();

		        if (cursoSelecionado != null && anoEscolaridadeSelecionado != null && !disciplinasSelecionadas.isEmpty()) {
		            boolean sucesso = loginService.associarAluno(aluno, cursoSelecionado, anoEscolaridadeSelecionado, disciplinasSelecionadas);
		            if (sucesso) {
		                System.out.println("Aluno associado com sucesso!");
		            } else {
		                System.out.println("Erro ao associar aluno.");
		            }
		            associarAlunoStage.close();
		        } else {
		            System.out.println("Selecione curso, ano e pelo menos uma disciplina.");
		        }
		    });

		    Button voltarButton = new Button("Voltar");
		    voltarButton.setOnAction(e -> associarAlunoStage.close());

		    layout.getChildren().addAll(cursoLabel, cursoChoiceBox,anoEscolaridadeLabel, anoEscolaridadeChoiceBox,disciplinaLabel, disciplinaListView,associarButton, voltarButton);

		    Scene scene = new Scene(layout, 640, 480);
		    associarAlunoStage.setScene(scene);
		    associarAlunoStage.show();
		}


}
