package upt.projeto.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;

@Entity
@Table(name = "aluno")
@PrimaryKeyJoinColumn(name = "idAluno")
public class Aluno extends Utilizador {

	 public Aluno(String nome, String email, int numAluno, String senha) {
	        super.setNome(nome);
	        super.setEmail(email);
	        super.setPassword(senha); 
	        this.numAluno = numAluno;
	 }
	
	
	public Aluno() {
			
	}
		
	@Column(name = "numAluno", nullable = false)
	private int numAluno;

	@Column(name = "anoEscolaridade")
	private int anoEscolaridade;

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
