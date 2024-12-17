package upt.projeto.model;

import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.CascadeType;
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
@Table(name = "curso")
public class Curso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @ManyToOne
    @JoinColumn(name = "administrador_id", nullable = false)
    private Administrador administrador;

    @OneToMany(mappedBy = "curso", cascade = CascadeType.ALL)
    private List<AnoEscolaridade> anosEscolaridade = new ArrayList<>();

    public Curso(String nome, Administrador administrador) {
        this.nome = nome;
        this.administrador = administrador;
    }
    
    public Curso() {
    	
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Administrador getAdministrador() {
        return administrador;
    }

    public void setAdministrador(Administrador administrador) {
        this.administrador = administrador;
    }

    public List<AnoEscolaridade> getAnosEscolaridade() {
        return anosEscolaridade;
    }

    public void setAnosEscolaridade(List<AnoEscolaridade> anosEscolaridade) {
        this.anosEscolaridade = anosEscolaridade;
    }

    public void addAnoEscolaridade(AnoEscolaridade ano) {
        anosEscolaridade.add(ano);
        ano.setCurso(this);
    }
	
	
}
