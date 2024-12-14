package com.upt.hibernate.proj_9grupo.repository;

import com.upt.hibernate.proj_9grupo.model.Utilizador;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UtilizadorRepository extends JpaRepository<Utilizador, Long> {
	
	List <Utilizador> findByNome(String nome);
	Optional<Utilizador> findByEmail(String email);
	List<Utilizador> findByTipoUtilizador(Utilizador.TipoUtilizador tipoUtilizador);
	boolean existsByEmail(String email);
	
}