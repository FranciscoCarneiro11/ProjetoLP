package com.upt.hibernate.proj_9grupo.repository;

import com.upt.hibernate.proj_9grupo.model.Aluno;       //sem um aluno, ninguem irá responder a umquiz
import com.upt.hibernate.proj_9grupo.model.Quiz;        //sem um quiz criado, não haverão respostas
import com.upt.hibernate.proj_9grupo.model.RespostaQuiz;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RespostaQuizRepository extends JpaRepository<RespostaQuiz, Long> {
	
	List <RespostaQuiz> findByAluno(Aluno aluno);
	List <RespostaQuiz> findByQuiz(Quiz quiz);
	 boolean existsByAlunoAndQuiz(Aluno aluno, Quiz quiz);
}
