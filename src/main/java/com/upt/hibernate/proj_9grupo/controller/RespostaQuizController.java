package com.upt.hibernate.proj_9grupo.controller;

import com.upt.hibernate.proj_9grupo.model.Aluno;
import com.upt.hibernate.proj_9grupo.model.RespostaQuiz;
import com.upt.hibernate.proj_9grupo.service.RespostaQuizService;
import com.upt.hibernate.proj_9grupo.repository.AlunosRepository; 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/resposta_quiz")
public class RespostaQuizController {

	@Autowired
	private RespostaQuizService respostaQuizService;
	
	@Autowired
    private AlunosRepository alunoRepository;
	
	@GetMapping
    public ResponseEntity<List<RespostaQuiz>> getAllRespostas() {
        return ResponseEntity.ok(respostaQuizService.getAllRespostas());
    }
	
	@GetMapping("/aluno/{id}")
    public ResponseEntity<List<RespostaQuiz>> getRespostasByAlunoId(@PathVariable Long id) {
        Aluno aluno = alunoRepository.findById(id).orElseThrow(() -> new RuntimeException("Aluno não encontrado com ID: " + id));
        List<RespostaQuiz> respostas = respostaQuizService.getRespostasByAlunoId((long) aluno.getId());
        return ResponseEntity.ok(respostas);
    }
	
	 
	@PostMapping
    public ResponseEntity<RespostaQuiz> criarRespostaQuiz(@RequestBody RespostaQuiz respostaquiz) {
        RespostaQuiz novaResposta = respostaQuizService.criarRespostaQuiz(respostaquiz);
        return ResponseEntity.ok(novaResposta);
    }
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> eliminarResposta(@PathVariable Long id) {
		respostaQuizService.eliminarRespostaQuiz(id);
		return ResponseEntity.noContent().build();
	}
	
	 @GetMapping("/quiz/{quizId}/aluno")
	 public ResponseEntity<Long> contarAlunos(@PathVariable Long quizId) {
		 long numeroDeAlunos = respostaQuizService.contarAlunosPorQuiz(quizId);
		 return ResponseEntity.ok(numeroDeAlunos);
	    }
}
