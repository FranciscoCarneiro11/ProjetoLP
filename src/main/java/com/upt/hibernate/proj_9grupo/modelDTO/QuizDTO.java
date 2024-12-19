package com.upt.hibernate.proj_9grupo.modelDTO;

public class QuizDTO {
    private String titulo;
    private Long professorId;
    private Long disciplinaId;

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Long getProfessorId() {
        return professorId;
    }

    public void setProfessorId(Number professorId) { 
        if (professorId != null) {
            this.professorId = professorId.longValue();
        }
    }

    public Long getDisciplinaId() {
        return disciplinaId;
    }

    public void setDisciplinaId(Number disciplinaId) { 
        if (disciplinaId != null) {
            this.disciplinaId = disciplinaId.longValue();
        }
    }
}
