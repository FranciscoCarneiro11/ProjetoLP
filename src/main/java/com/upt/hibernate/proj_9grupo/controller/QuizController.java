package com.upt.hibernate.proj_9grupo.controller;

import com.upt.hibernate.proj_9grupo.model.Quiz;
import com.upt.hibernate.proj_9grupo.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/quiz")
public class QuizController {

	@Autowired
	private QuizService quizService;
	
	@GetMapping
	public List<Quiz> getAllQuizzes() {
	return quizService.getAllQuizzes();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Quiz> getQuizById(@PathVariable Long id) {
		Optional<Quiz> quiz = quizService.getQuizById(id);
		return quiz.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
	}
	
	@PostMapping
	public Quiz criarQuiz(@RequestBody Quiz quiz) {
		return quizService.criarQuiz(quiz);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> eliminarQuiz(@PathVariable Long id) {
		quizService.eliminarQuiz(id);
		return ResponseEntity.noContent().build();
	}
}
