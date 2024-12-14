package com.upt.hibernate.proj_9grupo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;

@Entity
@Table(name = "aluno")
@PrimaryKeyJoinColumn(name = "idAluno")
public class Aluno extends Utilizador {

    @Column(name = "numAluno", nullable = false)
    private int numAluno;

    @Column(name = "anoEscolaridade")
    private int anoEscolaridade;

   
    public Aluno() {
       
    }

    public int getNumAluno() {
        return numAluno;
    }

    public void setNumAluno(int numAluno) {
        this.numAluno = numAluno;
    }

    public int getAnoEscolaridade() {
        return anoEscolaridade;
    }

    public void setAnoEscolaridade(int anoEscolaridade) {
        this.anoEscolaridade = anoEscolaridade;
    }

    @Override
    public String toString() {
        return "Aluno [numAluno=" + numAluno + ", anoEscolaridade=" + anoEscolaridade + ", getId()=" + getId()
                + ", getNome()=" + getNome() + ", getTipoUtilizador()=" + getTipoUtilizador() + ", getEmail()="
                + getEmail() + "]";
    }
}


