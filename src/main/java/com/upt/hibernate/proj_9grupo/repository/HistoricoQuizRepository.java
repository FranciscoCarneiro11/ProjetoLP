package com.upt.hibernate.proj_9grupo.repository;

import com.upt.hibernate.proj_9grupo.model.HistoricoQuiz;
import com.upt.hibernate.proj_9grupo.model.Aluno;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HistoricoQuizRepository extends JpaRepository<HistoricoQuiz, Long> {
    List<HistoricoQuiz> findByAluno(Aluno aluno);
}
