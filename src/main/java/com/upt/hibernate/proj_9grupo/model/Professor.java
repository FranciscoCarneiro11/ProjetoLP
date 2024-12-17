package com.upt.hibernate.proj_9grupo.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;


@Entity
@Table(name = "professor")
@PrimaryKeyJoinColumn(name = "idProfessor")
public class Professor extends Utilizador {

    @Column(name = "numProfessor", nullable = false)
    private int numProfessor;
    
    @OneToMany(mappedBy = "professor")
    @JsonIgnore
    private List<Quiz> quizzes = new ArrayList<>();


    public Professor() {
        super();
    }
    
    
    public Professor(String nome, String email, int numProfessor, String senha) {
        super.setNome(nome);
        super.setEmail(email);
        super.setPassword(senha); 
        this.numProfessor = numProfessor;
 }

    // Get's e set's
    public int getNumProfessor() {
        return numProfessor;
    }

    public void setNumProfessor(int numProfessor) {
        this.numProfessor = numProfessor;
    }

}
