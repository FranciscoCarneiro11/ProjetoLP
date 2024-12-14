package com.upt.hibernate.proj_9grupo.repository;

import com.upt.hibernate.proj_9grupo.model.Pergunta;
import com.upt.hibernate.proj_9grupo.model.Quiz;         //sem o quiz não conseguimos criar uma pergunta
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PerguntaRepository extends JpaRepository<Pergunta, Long> {
	
	List <Pergunta> findByQuiz(Quiz quiz);
	List <Pergunta> findByQuestao(String questao);
	
}
