package com.upt.hibernate.proj_9grupo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.upt.hibernate.proj_9grupo.model.AnoEscolaridade;
import com.upt.hibernate.proj_9grupo.model.AssociacaoAluno;
import com.upt.hibernate.proj_9grupo.model.Curso;
import com.upt.hibernate.proj_9grupo.model.Disciplina;
import com.upt.hibernate.proj_9grupo.repository.AnoEscolaridadeRepository;
import com.upt.hibernate.proj_9grupo.repository.AssociacaoAlunoRepository;
import com.upt.hibernate.proj_9grupo.repository.CursoRepository;
import com.upt.hibernate.proj_9grupo.repository.DisciplinaRepository;

@Service
public class AssociacaoAlunoService {

    @Autowired
    private AnoEscolaridadeRepository anoEscolaridadeRepository;

    @Autowired
    private DisciplinaRepository disciplinaRepository;

    @Autowired
    private AssociacaoAlunoRepository associacaoAlunoRepository;

    @Autowired
    private CursoRepository cursoRepository; 

    public List<AnoEscolaridade> getAnosEscolaridadePorCurso(Long cursoId) {
        Curso curso = cursoRepository.findById(cursoId).orElseThrow(() -> new RuntimeException("Curso não encontrado com ID: " + cursoId));
        return anoEscolaridadeRepository.findByCurso(curso);
    }

    public List<Disciplina> getDisciplinasPorCurso(Long cursoId) {
        Curso curso = cursoRepository.findById(cursoId).orElseThrow(() -> new RuntimeException("Curso não encontrado com ID: " + cursoId));
        return disciplinaRepository.findByCurso(curso);
    }

    public AssociacaoAluno salvarAssociacaoAluno(AssociacaoAluno associacao) {
        return associacaoAlunoRepository.save(associacao);
    }
}
