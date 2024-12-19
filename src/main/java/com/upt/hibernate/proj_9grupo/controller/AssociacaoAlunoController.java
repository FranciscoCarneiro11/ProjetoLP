package com.upt.hibernate.proj_9grupo.controller;

import com.upt.hibernate.proj_9grupo.model.AssociacaoAluno;
import com.upt.hibernate.proj_9grupo.model.AnoEscolaridade;
import com.upt.hibernate.proj_9grupo.model.Disciplina;
import com.upt.hibernate.proj_9grupo.service.AssociacaoAlunoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/associacao_aluno")
public class AssociacaoAlunoController {

    @Autowired
    private AssociacaoAlunoService associacaoAlunoService;

    @GetMapping("/ano_escolaridade")
    public ResponseEntity<List<AnoEscolaridade>> getAnosEscolaridade(@RequestParam Long cursoId) {
        List<AnoEscolaridade> anos = associacaoAlunoService.getAnosEscolaridadePorCurso(cursoId);
        return ResponseEntity.ok(anos);
    }

    @GetMapping("/disciplina")
    public ResponseEntity<List<Disciplina>> getDisciplinasPorCurso(@RequestParam Long cursoId) {
        List<Disciplina> disciplinas = associacaoAlunoService.getDisciplinasPorCurso(cursoId);
        return ResponseEntity.ok(disciplinas);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AssociacaoAluno> criarAssociacaoAluno(@RequestBody AssociacaoAluno associacao) {
        System.out.println("Recebido: " + associacao);
        AssociacaoAluno novaAssociacao = associacaoAlunoService.salvarAssociacaoAluno(associacao);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaAssociacao);
    }
}
