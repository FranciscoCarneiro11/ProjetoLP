package com.upt.hibernate.proj_9grupo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.upt.hibernate.proj_9grupo.model.AnoEscolaridade;
import com.upt.hibernate.proj_9grupo.model.Disciplina;
import com.upt.hibernate.proj_9grupo.repository.DisciplinaRepository;

@Service
public class DisciplinaService {
	
	@Autowired
    private DisciplinaRepository disciplinaRepository;

    public Disciplina criarDisciplina(Disciplina disciplina) {
        return disciplinaRepository.save(disciplina);
    }

    public List<Disciplina> listarDisciplinasPorAno(AnoEscolaridade anoEscolaridade) {
        return disciplinaRepository.findByAnoEscolaridade(anoEscolaridade);
    }

    public void eliminarDisciplina(Long id) {
        disciplinaRepository.deleteById(id);
    }
}
