package com.upt.hibernate.proj_9grupo.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

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
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Quiz {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String titulo;

    @ManyToOne
    @JoinColumn(name = "idProfessor", nullable = false)
    private Professor professor;
    
    @ManyToOne
    @JoinColumn(name = "disciplina_id", nullable = false) 
    @JsonManagedReference
    private Disciplina disciplina;

    @OneToMany(mappedBy = "quiz")
    @JsonBackReference
    private List<Pergunta> perguntas = new ArrayList<>();

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

    public Disciplina getDisciplina() {
		return disciplina;
	}
    
	public void setDisciplina(Disciplina disciplina) {
		this.disciplina = disciplina;
	}
	
	public void addPergunta(Pergunta pergunta) {
        perguntas.add(pergunta);
        pergunta.setQuiz(this);
    }
}