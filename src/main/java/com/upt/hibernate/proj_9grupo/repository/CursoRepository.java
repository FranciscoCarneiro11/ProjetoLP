package com.upt.hibernate.proj_9grupo.repository;

import com.upt.hibernate.proj_9grupo.model.Administrador;
import com.upt.hibernate.proj_9grupo.model.Curso;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CursoRepository extends JpaRepository<Curso, Long> {
	List<Curso> findByAdministrador(Administrador administrador);
    List<Curso> findByNome(String nome);
}
