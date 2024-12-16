package com.upt.hibernate.proj_9grupo.repository;

import java.util.List;
import com.upt.hibernate.proj_9grupo.model.ProfessorDisciplina;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfessorDisciplinaRepository extends JpaRepository<ProfessorDisciplina, Long> {
    List<ProfessorDisciplina> findByProfessorId(Long professorId);
}
