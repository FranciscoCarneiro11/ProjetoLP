package com.upt.hibernate.proj_9grupo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.upt.hibernate.proj_9grupo.model.AnoEscolaridade;
import com.upt.hibernate.proj_9grupo.model.AssociacaoProfessor;
import com.upt.hibernate.proj_9grupo.model.Curso;
import com.upt.hibernate.proj_9grupo.model.Disciplina;
import com.upt.hibernate.proj_9grupo.model.Professor;
import com.upt.hibernate.proj_9grupo.repository.AssociacaoProfessorRepository;

@Service
public class AssociacaoProfessorService {

	
	@Autowired
    private AssociacaoProfessorRepository associacaoProfessorRepository;
	
	public AssociacaoProfessor criarAssociacao(Professor professor, Curso curso, AnoEscolaridade anoEscolaridade, Disciplina disciplina) {
        AssociacaoProfessor associacao = new AssociacaoProfessor();
        associacao.setProfessor(professor);
        associacao.setCurso(curso);
        associacao.setAnoEscolaridade(anoEscolaridade);
        associacao.setDisciplina(disciplina);
        return associacaoProfessorRepository.save(associacao);
    }
	
	public List<AssociacaoProfessor> verAssociacoesPorProfessor(Professor professor) {
        return associacaoProfessorRepository.findByProfessor(professor);
    }
	
}
