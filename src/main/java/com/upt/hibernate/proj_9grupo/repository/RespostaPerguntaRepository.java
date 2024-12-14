package com.upt.hibernate.proj_9grupo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.upt.hibernate.proj_9grupo.model.Aluno;
import com.upt.hibernate.proj_9grupo.model.RespostaPergunta;
import com.upt.hibernate.proj_9grupo.model.RespostaQuiz;

@Repository
public interface RespostaPerguntaRepository extends JpaRepository<RespostaPergunta, Integer> {
	List<RespostaPergunta> findByAluno(Aluno aluno);
	List<RespostaPergunta> findByRespostaQuiz(RespostaQuiz respostaQuiz);
}
