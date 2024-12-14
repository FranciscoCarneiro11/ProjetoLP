package com.upt.hibernate.proj_9grupo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.upt.hibernate.proj_9grupo.model.AssociacaoProfessor;
import com.upt.hibernate.proj_9grupo.model.Professor;

import java.util.List;

@Repository
public interface AssociacaoProfessorRepository extends JpaRepository<AssociacaoProfessor, Long> {
    
    List<AssociacaoProfessor> findByProfessor(Professor professor);
    
}
