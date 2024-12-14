package com.upt.hibernate.proj_9grupo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.upt.hibernate.proj_9grupo.model.Administrador;
import com.upt.hibernate.proj_9grupo.model.Utilizador;
import com.upt.hibernate.proj_9grupo.repository.AdministradorRepository;
import com.upt.hibernate.proj_9grupo.repository.UtilizadorRepository;

@Service
public class AdministradorService {
	
	@Autowired
    private final AdministradorRepository administradorRepository;
	private final UtilizadorRepository utilizadorRepository;
	
	public AdministradorService(AdministradorRepository administradorRepository,UtilizadorRepository utilizadorRepository ) {
		this.administradorRepository = administradorRepository;
		this.utilizadorRepository = utilizadorRepository;
	}
	
	public List<Administrador> getAllAdministradores(){
		return administradorRepository.findAll();
	}
	
	public Optional<Administrador> getAdministradorById(Long id) {
		return administradorRepository.findById(id);
	}
	
	public Administrador criarAdministrador(Administrador administrador) {
		if (utilizadorRepository.existsByEmail(administrador.getEmail())) {
	        throw new RuntimeException("Email já registrado!");
	    }
		
		administrador.setTipoUtilizador(Utilizador.TipoUtilizador.administrador);
		return administradorRepository.save(administrador);
	}
	
	public void eliminarAdministrador(Long id) {
		if (administradorRepository.existsById(id)) {
			administradorRepository.deleteById(id);
		} else {
			throw new RuntimeException("Administrador não encontrado com o id: "+ id);
		}
	}
}
