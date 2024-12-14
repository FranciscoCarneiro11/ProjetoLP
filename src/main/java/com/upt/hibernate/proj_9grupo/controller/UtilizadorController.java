package com.upt.hibernate.proj_9grupo.controller;

import java.util.List;
import java.util.Optional;

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

import com.upt.hibernate.proj_9grupo.model.Aluno;
import com.upt.hibernate.proj_9grupo.model.Professor;
import com.upt.hibernate.proj_9grupo.model.Utilizador;
import com.upt.hibernate.proj_9grupo.service.UtilizadorService;


@RestController
@RequestMapping("/api/utilizador")
public class UtilizadorController {

	@Autowired
	private UtilizadorService utilizadorService;
	
	
	@GetMapping
	public List<Utilizador> getAllUtilizadores() {
	return utilizadorService.getAllUtilizadores();
	}
	
	@GetMapping("/{id}")
	public Utilizador getUtilizadoresById(@PathVariable Long id) {
		return utilizadorService.getUtilizadoresById(id);
	}
	
	@GetMapping("/email/{email}")
	public ResponseEntity<Utilizador> getUtilizadorByEmail(@PathVariable String email) {
	    Optional<Utilizador> utilizador = utilizadorService.findByEmail(email);
	    if (utilizador.isPresent()) {
	        return ResponseEntity.ok(utilizador.get());
	    } else {
	        return ResponseEntity.notFound().build();
	    }
	}
	
	 @PostMapping("/aluno")
	 public Aluno criarAluno(@RequestBody Aluno aluno) {
		 return (Aluno) utilizadorService.criarUtilizador(aluno);
	 }

	 @PostMapping("/professor")
	 public Professor criarProfessor(@RequestBody Professor professor) {
		 return (Professor) utilizadorService.criarUtilizador(professor);
	    }
	
	@PutMapping("/{id}")
	public Utilizador updateUtilizador(@PathVariable Long id, @RequestBody Utilizador utilizador) {
	return utilizadorService.updateUtilizador(id, utilizador);
	}

	
	@DeleteMapping("/{id}")
	public String eliminarUtilizador(@PathVariable Long id) {
		utilizadorService.eliminarUtilizador(id);
		return "Utilizador eliminado com sucesso!!!";
	}
}
