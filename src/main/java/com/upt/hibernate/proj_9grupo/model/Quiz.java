package com.upt.hibernate.proj_9grupo.model;

import java.util.List;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.ArrayList;

@Entity
@Table(name = "quiz")
public class Quiz {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String titulo;

    @ManyToOne
    @JoinColumn(name = "idProfessor", nullable = false)
    private Professor professor;

    @OneToMany(mappedBy = "quiz")
    private List<Pergunta> perguntas = new ArrayList<>();

    // Get's e set's
    public int getId() { 
    	return id; 
    }
    public void setId(int id) { 
    	this.id = id; 
    }

    public String getTitulo() { 
    	return titulo; 
    }
    public void setTitulo(String titulo) { 
    	this.titulo = titulo; 
    }

    public Professor getProfessor() { 
    	return professor; 
    }
    public void setProfessor(Professor professor) { 
    	this.professor = professor; 
    }

    public List<Pergunta> getPerguntas() { 
    	return perguntas; 
    }
    public void setPerguntas(List<Pergunta> perguntas) { 
    	this.perguntas = perguntas; 
    }

    public void addPergunta(Pergunta pergunta) {
        perguntas.add(pergunta);
        pergunta.setQuiz(this);
    }
}