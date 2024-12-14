package com.upt.hibernate.proj_9grupo.repository;

import com.upt.hibernate.proj_9grupo.model.Professor;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfessorRepository extends JpaRepository<Professor, Long> {
	
	List <Professor> findByNome(String nome);
	List <Professor> findByNumProfessor(int numProfessor);
	List <Professor> findByDisciplina(String disciplina);
	boolean existsByNumProfessor(int numProfessor);
}
