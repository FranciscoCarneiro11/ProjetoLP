package com.upt.hibernate.proj_9grupo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.upt.hibernate.proj_9grupo.model.AnoEscolaridade;
import com.upt.hibernate.proj_9grupo.model.AssociacaoProfessor;
import com.upt.hibernate.proj_9grupo.model.Curso;
import com.upt.hibernate.proj_9grupo.model.Disciplina;
import com.upt.hibernate.proj_9grupo.model.Professor;
import com.upt.hibernate.proj_9grupo.service.AssociacaoProfessorService;

@RestController
@RequestMapping("/api/associacoes")
public class AssociacaoProfessorController {
	
	 @Autowired
	 private AssociacaoProfessorService associacaoProfessorService;
	 
	  @PostMapping
	    public ResponseEntity<AssociacaoProfessor> criarAssociacao(@RequestParam int professorId, @RequestParam Long cursoId, @RequestParam Long anoEscolaridadeId, @RequestParam Long disciplinaId) {
	        Professor professor = new Professor(); 
	        professor.setId(professorId); 

	        Curso curso = new Curso(); 
	        curso.setId(cursoId); 

	        AnoEscolaridade anoEscolaridade = new AnoEscolaridade(); 
	        anoEscolaridade.setId(anoEscolaridadeId); 

	        Disciplina disciplina = new Disciplina(); 
	        disciplina.setId(disciplinaId); 

	        
	        AssociacaoProfessor associacao = associacaoProfessorService.criarAssociacao(professor, curso, anoEscolaridade, disciplina);
	        return ResponseEntity.ok(associacao);
	    }

	    @GetMapping("/professor/{professorId}")
	    public ResponseEntity<List<AssociacaoProfessor>> listarAssociacoesPorProfessor(@PathVariable int professorId) {
	        Professor professor = new Professor(); 
	        professor.setId(professorId);
	        List<AssociacaoProfessor> associacoes = associacaoProfessorService.verAssociacoesPorProfessor(professor);
	        return ResponseEntity.ok(associacoes);
	    }
}
