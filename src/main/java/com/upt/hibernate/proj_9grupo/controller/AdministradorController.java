package com.upt.hibernate.proj_9grupo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.upt.hibernate.proj_9grupo.model.Administrador;
import com.upt.hibernate.proj_9grupo.service.AdministradorService;

@RestController
@RequestMapping("/api/administrador")
public class AdministradorController {
	
	@Autowired
    private AdministradorService administradorService;
	
	@GetMapping
	public List<Administrador> getAllAdministradores(){
		return administradorService.getAllAdministradores();
	}
	
	@GetMapping("/{id}")
    public ResponseEntity<Administrador> getAdministradorById(@PathVariable Long id){
		if (id < 0) {
            return ResponseEntity.badRequest().body(null); 
        }
		Optional<Administrador> administrador = administradorService.getAdministradorById(id);
		return administrador.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
	
	@PostMapping
	public Administrador criarAdministrador(@RequestBody Administrador administrador) {
		Administrador novoAdm = administradorService.criarAdministrador(administrador);
        System.out.println("Administrador criado com ID: " + novoAdm.getId());
        
        return novoAdm;
    }
	
	@DeleteMapping("/{id}")
    public String eliminarAdministrador(@PathVariable Long id) {
        administradorService.eliminarAdministrador(id);
        return "Administrador eliminado om sucesso!";
    }
}
