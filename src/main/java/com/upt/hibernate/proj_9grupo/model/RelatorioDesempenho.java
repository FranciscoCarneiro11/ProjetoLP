package com.upt.hibernate.proj_9grupo.model;

public class RelatorioDesempenho {
	
	private Aluno aluno;
    private int pontuacao;
    private int posicao;
	
    public RelatorioDesempenho(int pontuacao, int posicao) {
		this.aluno = null;
		this.pontuacao = pontuacao;
		this.posicao = posicao;
	}
    
    public RelatorioDesempenho() {
    	
    }

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

	public int getPontuacao() {
		return pontuacao;
	}

	public void setPontuacao(int pontuacao) {
		this.pontuacao = pontuacao;
	}

	public int getPosicao() {
		return posicao;
	}

	public void setPosicao(int posicao) {
		this.posicao = posicao;
	}

	@Override
	public String toString() {
		return "RelatorioDesempenho [aluno=" + aluno + ", pontuacao=" + pontuacao + ", posicao=" + posicao + "]";
	}
    
	
}
