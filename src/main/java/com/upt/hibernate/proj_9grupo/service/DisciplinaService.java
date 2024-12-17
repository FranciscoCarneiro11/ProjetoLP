package com.upt.hibernate.proj_9grupo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.upt.hibernate.proj_9grupo.model.AnoEscolaridade;
import com.upt.hibernate.proj_9grupo.model.Curso;
import com.upt.hibernate.proj_9grupo.model.Disciplina;
import com.upt.hibernate.proj_9grupo.repository.CursoRepository;
import com.upt.hibernate.proj_9grupo.repository.DisciplinaRepository;

@Service
public class DisciplinaService {
	
	@Autowired
    private DisciplinaRepository disciplinaRepository;
	private CursoRepository cursoRepository;

	public DisciplinaService(DisciplinaRepository disciplinaRepository, CursoRepository cursoRepository) {
		this.disciplinaRepository = disciplinaRepository;
		this.cursoRepository = cursoRepository;
	}

	public Disciplina criarDisciplina(Disciplina disciplina) {
	    Curso curso = cursoRepository.findById(disciplina.getCurso().getId()).orElseThrow(() -> new RuntimeException("Curso não encontrado!"));

	    disciplina.setCurso(curso);

	    return disciplinaRepository.save(disciplina);
	}

    public List<Disciplina> listarDisciplinasPorAno(AnoEscolaridade anoEscolaridade) {
        return disciplinaRepository.findByAnoEscolaridade(anoEscolaridade);
    }
    
    public Optional<Disciplina> getDisciplinaById(Long id) {
        return disciplinaRepository.findById(id);
    }

    public void eliminarDisciplina(Long id) {
        disciplinaRepository.deleteById(id);
    }
}
