package com.upt.hibernate.proj_9grupo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.upt.hibernate.proj_9grupo.model.Administrador;
import com.upt.hibernate.proj_9grupo.model.Curso;
import com.upt.hibernate.proj_9grupo.repository.CursoRepository;

@Service
public class CursoService {
	
	@Autowired
    private CursoRepository cursoRepository;
	
	public Curso criarCurso(Curso curso) {
		return cursoRepository.save(curso);
	}
	
	public List<Curso> listarCursosPorAdministrador(Administrador administrador) {
        return cursoRepository.findByAdministrador(administrador);
    }
	
	 public List<Curso> getAllCursos() {
		 return cursoRepository.findAll();
	 }
	 
	 public void eliminarCurso(Long id) {
		 cursoRepository.deleteById(id);
	 }
}
