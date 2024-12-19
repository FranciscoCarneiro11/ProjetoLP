package com.upt.hibernate.proj_9grupo.controller;

import com.upt.hibernate.proj_9grupo.model.Disciplina;
import com.upt.hibernate.proj_9grupo.model.Professor;
import com.upt.hibernate.proj_9grupo.model.Quiz;
import com.upt.hibernate.proj_9grupo.modelDTO.QuizDTO;
import com.upt.hibernate.proj_9grupo.repository.DisciplinaRepository;
import com.upt.hibernate.proj_9grupo.repository.ProfessorRepository;
import com.upt.hibernate.proj_9grupo.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/quiz")
public class QuizController {

	@Autowired
	private QuizService quizService;
	
	@Autowired
	private ProfessorRepository professorRepository;
	
	@Autowired
	private DisciplinaRepository disciplinaRepository;
	
	
	@GetMapping
	public List<QuizDTO> listarTodosQuizzes() {
	    List<Quiz> quizzes = quizService.getAllQuizzes();
	    List<QuizDTO> quizDTOs = new ArrayList<>();

	    for (Quiz quiz : quizzes) {
	        QuizDTO quizDTO = new QuizDTO();
	        quizDTO.setTitulo(quiz.getTitulo());
	        quizDTO.setProfessorId(quiz.getProfessor() != null ? quiz.getProfessor().getId() : null);
	        quizDTO.setDisciplinaId(quiz.getDisciplina() != null ? quiz.getDisciplina().getId() : null);
	        quizDTOs.add(quizDTO);
	    }

	    return quizDTOs;
	}


	
	@GetMapping("/{id}")
	public ResponseEntity<Quiz> getQuizById(@PathVariable Long id) {
		Optional<Quiz> quiz = quizService.getQuizById(id);
		return quiz.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
	}
	
	@PostMapping
	public Quiz criarQuiz(@RequestBody QuizDTO quizDTO) {
	    Quiz quiz = new Quiz();
	    quiz.setTitulo(quizDTO.getTitulo());

	    Professor professor = professorRepository.findById(quizDTO.getProfessorId()).orElseThrow(() -> new RuntimeException("Professor não encontrado"));
	    quiz.setProfessor(professor);

	    Disciplina disciplina = disciplinaRepository.findById(quizDTO.getDisciplinaId()).orElseThrow(() -> new RuntimeException("Disciplina não encontrada"));
	    quiz.setDisciplina(disciplina);

	    return quizService.criarQuiz(quiz);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> eliminarQuiz(@PathVariable Long id) {
		quizService.eliminarQuiz(id);
		return ResponseEntity.noContent().build();
	}
}
