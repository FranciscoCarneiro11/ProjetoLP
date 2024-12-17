package upt.projeto.model;

import java.util.ArrayList;
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
    
    @ManyToOne
    @JoinColumn(name = "disciplina_id", nullable = false) 
    private Disciplina disciplina;

    @OneToMany(mappedBy = "quiz")
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
	
	
}
