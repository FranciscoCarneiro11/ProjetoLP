package com.upt.hibernate.proj_9grupo.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.upt.hibernate.proj_9grupo.model.Administrador;
import com.upt.hibernate.proj_9grupo.model.Curso;
import com.upt.hibernate.proj_9grupo.service.CursoService;

@RestController
@RequestMapping("/api/curso")
public class CursoController {
	
	  @Autowired
	  private CursoService cursoService;
	  
	  @GetMapping("/todos")
	    public List<Curso> listarTodosCursos() {
	        return cursoService.listarTodosCursos();
	    }
	  
	  @GetMapping
	  public List<Curso> listarCursosPorAdministrador(@RequestParam int administradorId) {
		  Administrador administrador = new Administrador(); 
	      administrador.setId(administradorId);
	      return cursoService.listarCursosPorAdministrador(administrador);
	  }

	  @PostMapping
	  public Curso criarCurso(@RequestBody Curso curso) {
		  Curso novoCurso = cursoService.criarCurso(curso);
	      System.out.println("Administrador criado com o nome: " + novoCurso.getNome());
	      return novoCurso;
	  }
	  
	  @DeleteMapping("/{id}")
	  public String eliminarCurso(@PathVariable Long id) {
		  cursoService.eliminarCurso(id);
	      return "Curso eliminado om sucesso!";
	  }
	  
}
