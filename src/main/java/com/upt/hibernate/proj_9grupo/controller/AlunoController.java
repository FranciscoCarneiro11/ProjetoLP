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
import com.upt.hibernate.proj_9grupo.repository.AlunosRepository;
import com.upt.hibernate.proj_9grupo.service.AlunoService;

@RestController
@RequestMapping("/api/aluno")
public class AlunoController {

    @Autowired
    private AlunoService alunoService;

    @Autowired 
    private AlunosRepository alunoRepository;

    @GetMapping
    public List<Aluno> getAllAlunos() {
        return alunoService.getAllAlunos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Aluno> getAlunoById(@PathVariable Long id) {
    	if (id < 0) {
            return ResponseEntity.badRequest().body(null); 
        }
    	Optional<Aluno> aluno = alunoService.getAlunoById(id);
        return aluno.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Aluno criarAluno(@RequestBody Aluno aluno) {
        Aluno novoAluno = alunoService.criarAluno(aluno);
        System.out.println("Aluno criado com ID: " + novoAluno.getId());

        Optional<Aluno> alunoVerificado = alunoRepository.findById((long) novoAluno.getId());
        if (alunoVerificado.isPresent()) {
            System.out.println("Aluno verificado com ID: " + alunoVerificado.get().getId());
        } else {
            System.out.println("Aluno não encontrado com ID: " + novoAluno.getId());
        }

        return novoAluno; 
    }

    @PutMapping("/{id}")
    public ResponseEntity<Aluno> updateAluno(@PathVariable Long id, @RequestBody Aluno aluno) {
        if (id == null) {
            return ResponseEntity.badRequest().body(null);
        }
        Aluno alunoAtualizado = alunoService.updateAluno(id, aluno);
        if (alunoAtualizado == null) {
            return ResponseEntity.notFound().build(); 
        }
        return ResponseEntity.ok(alunoAtualizado);
    }

    @DeleteMapping("/{id}")
    public String eliminarAluno(@PathVariable Long id) {
        alunoService.eliminarAluno(id);
        return "Aluno eliminado com sucesso!!";
    }
}
