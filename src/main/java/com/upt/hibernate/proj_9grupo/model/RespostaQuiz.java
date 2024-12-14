package com.upt.hibernate.proj_9grupo.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "resposta_quiz")
public class RespostaQuiz {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "fk_aluno", nullable = false)
    private Aluno aluno;
    
    @ManyToOne
    @JoinColumn(name = "fk_quiz", nullable = false)
    private Quiz quiz;

    @Column(nullable = false)
    private int pontuacao;
    
    @ElementCollection
    @CollectionTable(name = "respostas_perguntas", joinColumns = @JoinColumn(name = "resposta_quiz_id"))
    @Column(name = "resposta") 
    private List<String> respostas = new ArrayList<>();

    // Get's e set's
    public int getId() { 
    	return id; 
    }
    public void setId(int id) { 
    	this.id = id; 
    }
    public Aluno getAluno() { 
    	return aluno; 
    }
    public void setAluno(Aluno aluno) { 
    	this.aluno = aluno; 
    }

    public Quiz getQuiz() { 
    	return quiz; 
    }
    public void setQuiz(Quiz quiz) { 
    	this.quiz = quiz; 
    }

    public int getPontuacao() { 
    	return pontuacao; 
    }
    public void setPontuacao(int pontuacao) { 
    	this.pontuacao = pontuacao; 
    }
	public List<String> getRespostas() {
		return respostas;
	}
	public void setRespostas(List<String> respostas) {
		this.respostas = respostas;
	}
    
    

}
