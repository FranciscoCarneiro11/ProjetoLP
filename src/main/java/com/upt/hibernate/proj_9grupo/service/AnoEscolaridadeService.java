package com.upt.hibernate.proj_9grupo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.upt.hibernate.proj_9grupo.model.AnoEscolaridade;
import com.upt.hibernate.proj_9grupo.model.Curso;
import com.upt.hibernate.proj_9grupo.repository.AnoEscolaridadeRepository;

@Service
public class AnoEscolaridadeService {
	
	@Autowired
    private AnoEscolaridadeRepository anoEscolaridadeRepository;

    public AnoEscolaridade criarAnoEscolaridade(AnoEscolaridade anoEscolaridade) {
        return anoEscolaridadeRepository.save(anoEscolaridade);
    }

    public List<AnoEscolaridade> listarAnosPorCurso(Curso curso) {
        return anoEscolaridadeRepository.findByCurso(curso);
    }

    public void eliminarAnoEscolaridade(Long id) {
        anoEscolaridadeRepository.deleteById(id);
    }
    
}
