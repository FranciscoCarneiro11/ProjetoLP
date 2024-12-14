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
import com.upt.hibernate.proj_9grupo.model.AnoEscolaridade;
import com.upt.hibernate.proj_9grupo.model.Curso;
import com.upt.hibernate.proj_9grupo.service.AnoEscolaridadeService;

@RestController
@RequestMapping("/api/ano_escolaridade")
public class AnoEscolaridadeController {
	
	@Autowired
    private AnoEscolaridadeService anoEscolaridadeService;

    @PostMapping
    public AnoEscolaridade criarAnoEscolaridade(@RequestBody AnoEscolaridade anoEscolaridade) {
    	AnoEscolaridade novoAno = anoEscolaridadeService.criarAnoEscolaridade(anoEscolaridade);
        System.out.println("Administrador criado com ID: " + novoAno.getId());
        
        return novoAno;
    }

    @GetMapping
    public List<AnoEscolaridade> listarAnosPorCurso(@RequestParam Long cursoId) {
        Curso curso = new Curso(); 
        curso.setId(cursoId);
        return anoEscolaridadeService.listarAnosPorCurso(curso);
    }

    @DeleteMapping("/{id}")
    public String eliminarAnoEscolaridade(@PathVariable Long id) {
    	anoEscolaridadeService.eliminarAnoEscolaridade(id);
        return "Ano eliminado om sucesso!";
    }

}
