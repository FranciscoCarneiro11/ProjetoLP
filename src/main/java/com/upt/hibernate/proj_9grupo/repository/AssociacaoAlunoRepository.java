package com.upt.hibernate.proj_9grupo.repository;

import com.upt.hibernate.proj_9grupo.model.AssociacaoAluno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssociacaoAlunoRepository extends JpaRepository<AssociacaoAluno, Long> {
   
}