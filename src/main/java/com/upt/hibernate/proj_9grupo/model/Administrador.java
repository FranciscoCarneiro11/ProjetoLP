package com.upt.hibernate.proj_9grupo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "administrador")
public class Administrador extends Utilizador {
	
	public Administrador() {
        super();
    }
	
	public Administrador(String nome, String email, String password) {
        super.setNome(nome);
        super.setEmail(email);
        super.setPassword(password);
        super.setTipoUtilizador(TipoUtilizador.administrador); 
    }
	

}
