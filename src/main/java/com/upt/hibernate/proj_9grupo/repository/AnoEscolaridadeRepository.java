package com.upt.hibernate.proj_9grupo.repository;

import com.upt.hibernate.proj_9grupo.model.AnoEscolaridade;
import com.upt.hibernate.proj_9grupo.model.Curso;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository; 
import org.springframework.stereotype.Repository;

@Repository public interface AnoEscolaridadeRepository extends JpaRepository<AnoEscolaridade, Long> { 
	List<AnoEscolaridade> findByCurso(Curso curso);
}
