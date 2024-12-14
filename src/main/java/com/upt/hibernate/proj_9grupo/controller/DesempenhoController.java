package com.upt.hibernate.proj_9grupo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.upt.hibernate.proj_9grupo.model.Quiz;
import com.upt.hibernate.proj_9grupo.model.RelatorioDesempenho;
import com.upt.hibernate.proj_9grupo.service.DesempenhoService;
import com.upt.hibernate.proj_9grupo.service.RespostaQuizService;
import com.upt.hibernate.proj_9grupo.repository.QuizRepository;

@RestController
@RequestMapping("/api/desempenho")
public class DesempenhoController {

    @Autowired
    private DesempenhoService desempenhoService;
    
    @Autowired
    private RespostaQuizService respostaquizService;

    @Autowired
    private QuizRepository quizRepository;

    @GetMapping("/{quizId}/relatorio")
    public ResponseEntity<List<RelatorioDesempenho>> obterRelatorioDesempenho(@PathVariable Long quizId) {
        Optional<Quiz> quizOpt = quizRepository.findById(quizId);
        if (!quizOpt.isPresent()) {
            return ResponseEntity.notFound().build(); 
        }

        List<RelatorioDesempenho> relatorio = desempenhoService.criarRelatorioDesempenho(quizId);

        return ResponseEntity.ok(relatorio);
    }
    
    @GetMapping("/quiz/{quizId}/aluno")
    public ResponseEntity<Long> contarAlunos(@PathVariable Long quizId) {
		long numeroDeAlunos = respostaquizService.contarAlunosPorQuiz(quizId);
        return ResponseEntity.ok(numeroDeAlunos);
    }
}
