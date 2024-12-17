package com.upt.hibernate.proj_9grupo.repository;

import com.upt.hibernate.proj_9grupo.model.Professor;     
import com.upt.hibernate.proj_9grupo.model.Quiz;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuizRepository extends JpaRepository<Quiz, Long> {
	
	List <Quiz> findByTitulo(String titulo);
	List <Quiz> findByProfessor(Professor professor);
	
	
	
}
