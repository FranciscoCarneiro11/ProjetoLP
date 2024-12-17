package upt.projeto.model;

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
    @JoinColumn(name = "curso_id") 
    private Curso curso;


    public Aluno() {
        super(); 
    }

    public Aluno(String nome, String email, int numAluno, String senha) {
        super.setNome(nome);
        super.setEmail(email);
        super.setPassword(senha); 
        this.numAluno = numAluno;
    }

	public int getNumAluno() {
		return numAluno;
	}

	public void setNumAluno(int numAluno) {
	    this.numAluno = numAluno;
	}

	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}

	@Override
	public String toString() {
		return "Aluno [numAluno=" + numAluno + ", getId()=" + getId()
					+ ", getNome()=" + getNome() + ", getTipoUtilizador()=" + getTipoUtilizador() + ", getEmail()="
					+ getEmail() + "]";
	}
}
