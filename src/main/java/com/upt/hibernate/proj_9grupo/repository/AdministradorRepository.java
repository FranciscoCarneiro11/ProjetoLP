package com.upt.hibernate.proj_9grupo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.upt.hibernate.proj_9grupo.model.Administrador;

@Repository
public interface AdministradorRepository extends JpaRepository<Administrador, Long> {
    List<Administrador> findByNome(String nome);
    Optional<Administrador> findByEmail(String email);
}
