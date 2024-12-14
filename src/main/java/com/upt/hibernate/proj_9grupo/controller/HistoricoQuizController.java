package com.upt.hibernate.proj_9grupo.controller;

import com.upt.hibernate.proj_9grupo.model.HistoricoQuiz;
import com.upt.hibernate.proj_9grupo.repository.AlunosRepository;
import com.upt.hibernate.proj_9grupo.model.Aluno;
import com.upt.hibernate.proj_9grupo.service.HistoricoQuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/historico_quiz")
public class HistoricoQuizController {
	
	@Autowired
	private final HistoricoQuizService historicoQuizService;
	
	@Autowired
	private AlunosRepository alunosRepository;

    @Autowired
    public HistoricoQuizController(HistoricoQuizService historicoQuizService) {
        this.historicoQuizService = historicoQuizService;
    }
    
    @GetMapping("/aluno/{alunoId}")
    public ResponseEntity<List<HistoricoQuiz>> obterHistoricoPorAluno(@PathVariable Long alunoId) {
        Aluno aluno = alunosRepository.findById(alunoId).orElse(null);
        
        if (aluno == null) {
            return ResponseEntity.notFound().build();
        }
        
        List<HistoricoQuiz> historico = historicoQuizService.obterHistoricoPorAluno(aluno);
        return ResponseEntity.ok(historico);
    }
        

    @PostMapping
    public ResponseEntity<HistoricoQuiz> adicionarHistorico(@RequestBody HistoricoQuiz historicoQuiz) {
        if (historicoQuiz.getAluno() == null || historicoQuiz.getQuiz() == null || historicoQuiz.getPontuacao() < 0) {
            return ResponseEntity.badRequest().build(); 
        }
        
        HistoricoQuiz novoHistorico = historicoQuizService.adicionarHistorico(historicoQuiz);
        return ResponseEntity.status(201).body(novoHistorico);
    }
	
	
	
}