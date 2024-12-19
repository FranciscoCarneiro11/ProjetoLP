package upt.projeto.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "respostas_perguntas")
public class RespostaPergunta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "resposta_quiz_id", nullable = false)
    private RespostaQuiz respostaQuiz; 

    @Column(name = "resposta", nullable = false)
    private String resposta; 
   
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public RespostaQuiz getRespostaQuiz() {
        return respostaQuiz;
    }

    public void setRespostaQuiz(RespostaQuiz respostaQuiz) {
        this.respostaQuiz = respostaQuiz;
    }

    public String getResposta() {
        return resposta;
    }

    public void setResposta(String resposta) {
        this.resposta = resposta;
    }


}
