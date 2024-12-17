package com.upt.hibernate.proj_9grupo.service;

import com.upt.hibernate.proj_9grupo.model.Disciplina;
import com.upt.hibernate.proj_9grupo.model.Professor;
import com.upt.hibernate.proj_9grupo.model.ProfessorDisciplina;
import com.upt.hibernate.proj_9grupo.model.Utilizador;
import com.upt.hibernate.proj_9grupo.repository.ProfessorDisciplinaRepository;
import com.upt.hibernate.proj_9grupo.repository.ProfessorRepository;
import com.upt.hibernate.proj_9grupo.repository.UtilizadorRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ProfessorService {

	private final ProfessorRepository professorRepository;
	private final UtilizadorRepository utilizadorRepository;
	private final DisciplinaService disciplinaService;
	private final ProfessorDisciplinaRepository professorDisciplinaRepository;
	
	@Autowired
	public ProfessorService(ProfessorRepository professorRepository, UtilizadorRepository utilizadorRepository, DisciplinaService disciplinaService, ProfessorDisciplinaRepository professorDisciplinaRepository) {
		this.professorRepository = professorRepository;
		this.utilizadorRepository = utilizadorRepository;
		this.disciplinaService =  disciplinaService;
		this.professorDisciplinaRepository = professorDisciplinaRepository;
	}
	
	public List<Professor> getAllProfessores(){
		return professorRepository.findAll();
	}
	
	public Optional<Professor> getProfessorById(Long id) {
		return professorRepository.findById(id);
	}

	public Professor criarProfessor(Professor professor) {
		if (utilizadorRepository.existsByEmail(professor.getEmail())) {
	        throw new RuntimeException("Email já registrado!");
	    }
		
		if(professor.getNumProfessor() <= 0) {
			throw new RuntimeException("O nº do professor deve ser maior que 0!!!");
		}
		
		if(professorRepository.existsByNumProfessor(professor.getNumProfessor())) {
			throw new RuntimeException("Nº de professor já existente! Por favor escolha um diferente.");
		}
		
		professor.setTipoUtilizador(Utilizador.TipoUtilizador.professor);
		return professorRepository.save(professor);
	}
	
	public Professor updateProfessor(Long id, Professor detalhesProfessor) {
		 if (id == null) {
			 throw new IllegalArgumentException("O ID não pode ser nulo.");
		 }
		 if (detalhesProfessor.getNome() == null || detalhesProfessor.getNome().isEmpty()) {
			 throw new IllegalArgumentException("O nome do professor não pode ser vazio.");
		 }
		 if (detalhesProfessor.getEmail() == null || detalhesProfessor.getEmail().isEmpty()) {
			 throw new IllegalArgumentException("O email do professor não pode ser vazio.");
		 }
		 
		 System.out.println("Atualizando professor com ID: " + id);
	     System.out.println("Detalhes do Professor: " + detalhesProfessor);
		
		Professor professor = professorRepository.findById(id).orElse(null);
		if(professor != null) {
			professor.setNome(detalhesProfessor.getNome());
			professor.setEmail(detalhesProfessor.getEmail());
			professor.setNumProfessor(detalhesProfessor.getNumProfessor());
			
			return professorRepository.save(professor);
		} else {
			System.out.println("Professor não encontrado com id: " + id);
		}
		
		return null;
	}
	
	public void eliminarProfessor(Long id) {
		if (professorRepository.existsById(id)) {
				professorRepository.deleteById(id);
		} else {
				throw new RuntimeException("Professor não encontrado com o id: "+ id);
		}
	}
	
	public void associarProfessorADisciplina(Long professorId, Long disciplinaId) {
	    Professor professor = getProfessorById(professorId).orElseThrow(() -> new RuntimeException("Professor não encontrado com ID: " + professorId));

	    Disciplina disciplina = disciplinaService.getDisciplinaById(disciplinaId).orElseThrow(() -> new RuntimeException("Disciplina não encontrada com ID: " + disciplinaId));

	    ProfessorDisciplina professorDisciplina = new ProfessorDisciplina();
	    professorDisciplina.setProfessor(professor);
	    professorDisciplina.setDisciplina(disciplina);
	    professorDisciplinaRepository.save(professorDisciplina);
	}
	
	
}
