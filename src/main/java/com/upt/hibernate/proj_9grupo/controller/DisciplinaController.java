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
import com.upt.hibernate.proj_9grupo.model.Disciplina;
import com.upt.hibernate.proj_9grupo.service.DisciplinaService;

@RestController
@RequestMapping("/api/disciplina")
public class DisciplinaController {

	@Autowired
    private DisciplinaService disciplinaService;
	
	@PostMapping
    public Disciplina criarDisciplina(@RequestBody Disciplina disciplina) {
		Disciplina novaDisciplina = disciplinaService.criarDisciplina(disciplina);
        System.out.println("Disciplina criada com o nome: " + novaDisciplina.getNome());
        
        return novaDisciplina;
    }

    @GetMapping
    public List<Disciplina> listarDisciplinasPorAno(@RequestParam Long anoEscolaridadeId) {
        AnoEscolaridade anoEscolaridade = new AnoEscolaridade(); 
        anoEscolaridade.setId(anoEscolaridadeId);
        return disciplinaService.listarDisciplinasPorAno(anoEscolaridade);
    }

    @DeleteMapping("/{id}")
    public String eliminarDisciplina(@PathVariable Long id) {
    	disciplinaService.eliminarDisciplina(id);
         return "Disciplina eliminada om sucesso!";
    }
}
