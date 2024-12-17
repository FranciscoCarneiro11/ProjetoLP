package com.upt.hibernate.proj_9grupo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;

@Entity
@Table(name = "aluno")
@PrimaryKeyJoinColumn(name = "idAluno")
public class Aluno extends Utilizador {

    @Column(name = "numAluno", nullable = false)
    private int numAluno;
    
    @ManyToOne
    @JoinColumn(name = "ano_escolaridade_id")
    private AnoEscolaridade anoEscolaridade;

    public Aluno() {
       
    }

    public int getNumAluno() {
        return numAluno;
    }

    public void setNumAluno(int numAluno) {
        this.numAluno = numAluno;
    }


    @Override
    public String toString() {
        return "Aluno [numAluno=" + numAluno + ", getId()=" + getId()
                + ", getNome()=" + getNome() + ", getTipoUtilizador()=" + getTipoUtilizador() + ", getEmail()="
                + getEmail() + "]";
    }
}


