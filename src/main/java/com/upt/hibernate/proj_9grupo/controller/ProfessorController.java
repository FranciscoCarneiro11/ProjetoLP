package com.upt.hibernate.proj_9grupo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import com.upt.hibernate.proj_9grupo.model.Professor;
import com.upt.hibernate.proj_9grupo.repository.ProfessorRepository;
import com.upt.hibernate.proj_9grupo.service.ProfessorService;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/professor")
public class ProfessorController {
	
	@Autowired
	private ProfessorService professorService;
	
	 @Autowired 
	private ProfessorRepository professorRepository;
	
	@GetMapping
	public List<Professor> getAllProfessores() {
	return professorService.getAllProfessores();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Professor> getProfessorById(@PathVariable Long id) {
		if (id == null || id < 0) {
			return ResponseEntity.badRequest().body(null); 
		}
		Optional<Professor> professor = professorService.getProfessorById(id);
		return professor.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
	}
	
	@PostMapping
	public Professor criarProfessor(@RequestBody Professor professor) {
		 Professor novoProfessor = professorService.criarProfessor(professor);
	     System.out.println("Professor criado com ID: " + novoProfessor.getId());

	     Optional<Professor> professorVerificado = professorRepository.findById((long) novoProfessor.getId());
	        if (professorVerificado.isPresent()) {
	            System.out.println("Professor verificado com ID: " + professorVerificado.get().getId());
	        } else {
	            System.out.println("Professor não encontrado com ID: " + novoProfessor.getId());
	        }

	        return novoProfessor;
	}
	
	@PutMapping("/{id}")
    public ResponseEntity<Professor> updateProfessor(@PathVariable Long id, @RequestBody Professor professor) {
        if (id == null) {
            return ResponseEntity.badRequest().body(null);
        }
        Professor professorAtualizado = professorService.updateProfessor(id, professor);
        if (professorAtualizado == null) {
            return ResponseEntity.notFound().build(); 
        }
        return ResponseEntity.ok(professorAtualizado);
    }

	
	@DeleteMapping("/{id}")
	public String eliminarProfessor(@PathVariable Long id) {
		professorService.eliminarProfessor(id);
		return "Professor eliminado com sucesso!!";

	}
	

}
