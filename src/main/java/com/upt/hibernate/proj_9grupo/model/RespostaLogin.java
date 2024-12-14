package com.upt.hibernate.proj_9grupo.model;

public class RespostaLogin {
	private boolean sucesso;
    private String mensagem; 
    
    public RespostaLogin() {
    	
    }
    
    public RespostaLogin(boolean sucesso, String mensagem) {
        this.sucesso = sucesso;
        this.mensagem = mensagem;
    }
    
    public boolean isSuccess() {
        return sucesso;
    }

    public void setSuccess(boolean sucesso) {
        this.sucesso = sucesso;
    }

    public String getMessage() {
        return mensagem;
    }

    public void setMessage(String mensagem) {
        this.mensagem = mensagem;
    }
}
