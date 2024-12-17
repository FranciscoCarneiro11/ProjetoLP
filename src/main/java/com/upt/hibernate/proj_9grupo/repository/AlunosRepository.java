package com.upt.hibernate.proj_9grupo.repository;

import com.upt.hibernate.proj_9grupo.model.Aluno;
import com.upt.hibernate.proj_9grupo.model.AnoEscolaridade;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlunosRepository extends JpaRepository<Aluno, Long> {

	List <Aluno> findByNome(String nome);
	List <Aluno> findByNumAluno(int numAluno);
	List <Aluno> findByAnoEscolaridade(AnoEscolaridade anoEscolaridade);
	boolean existsByNumAluno(int numAluno);
	
}
