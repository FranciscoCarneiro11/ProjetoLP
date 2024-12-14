package com.upt.hibernate.proj_9grupo.repository;

import com.upt.hibernate.proj_9grupo.model.Aluno;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlunosRepository extends JpaRepository<Aluno, Long> {
	
	//@Query(value = "SELECT " FROM Aluno where numAluno =?1, nativeQuery = true)
	List <Aluno> findByNome(String nome);
	List <Aluno> findByNumAluno(int numAluno);
	List <Aluno> findByAnoEscolaridade(int anoEscolaridade);
	boolean existsByNumAluno(int numAluno);
	
}
