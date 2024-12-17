package upt.projeto.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "associacao_aluno")
public class AssociacaoAluno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "aluno_id", nullable = false)
    private Aluno aluno;

    @ManyToOne
    @JoinColumn(name = "curso_id", nullable = false)
    private Curso curso;

    @ManyToOne
    @JoinColumn(name = "ano_escolaridade_id", nullable = false)
    private AnoEscolaridade anoEscolaridade;

    @ManyToMany
    @JoinTable(
        name = "associacao_aluno_disciplina",
        joinColumns = @JoinColumn(name = "associacao_aluno_id"),
        inverseJoinColumns = @JoinColumn(name = "disciplina_id")
    )
    private List<Disciplina> disciplinas;


    public AssociacaoAluno() {
    }

    public AssociacaoAluno(Aluno aluno, Curso curso, AnoEscolaridade anoEscolaridade, List<Disciplina> disciplinas) {
        this.aluno = aluno;
        this.curso = curso;
        this.anoEscolaridade = anoEscolaridade;
        this.disciplinas = disciplinas;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public AnoEscolaridade getAnoEscolaridade() {
        return anoEscolaridade;
    }

    public void setAnoEscolaridade(AnoEscolaridade anoEscolaridade) {
        this.anoEscolaridade = anoEscolaridade;
    }

    public List<Disciplina> getDisciplinas() {
        return disciplinas;
    }

    public void setDisciplinas(List<Disciplina> disciplinas) {
        this.disciplinas = disciplinas;
    }
}
