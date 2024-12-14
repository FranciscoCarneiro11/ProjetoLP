package com.upt.hibernate.proj_9grupo.service;

import com.upt.hibernate.proj_9grupo.model.Aluno;
import com.upt.hibernate.proj_9grupo.model.Utilizador;
import com.upt.hibernate.proj_9grupo.repository.AlunosRepository;
import com.upt.hibernate.proj_9grupo.repository.UtilizadorRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;


@Service
public class AlunoService {

    private final AlunosRepository alunosRepository;
    private final UtilizadorRepository utilizadorRepository;

    @Autowired
    public AlunoService(AlunosRepository alunosRepository, UtilizadorRepository utilizadorRepository) {
        this.alunosRepository = alunosRepository;
        this.utilizadorRepository = utilizadorRepository;
    }

    public List<Aluno> getAllAlunos() {
        return alunosRepository.findAll();
    }

    public Optional<Aluno> getAlunoById(Long id) {
    	if (id == null || id < 0) {
            throw new IllegalArgumentException("O ID não pode ser nulo ou negativo.");
        }
    	return alunosRepository.findById(id);
    }

    public Aluno criarAluno(Aluno aluno) {
    	 if (utilizadorRepository.existsByEmail(aluno.getEmail())) {
    	        throw new RuntimeException("Email já registrado!");
    	    }
    	
    	if(aluno.getNumAluno() <= 0) {
    		throw new RuntimeException("O nº do aluno deve ser maior que 0!!!");
    	}
    	
    	if(alunosRepository.existsByNumAluno(aluno.getNumAluno())) {
    		throw new RuntimeException("Nº de aluno já existente. Por favor escolha outro!!");
    	}
    	
    	aluno.setTipoUtilizador(Utilizador.TipoUtilizador.aluno); 
        return alunosRepository.save(aluno);
    }

    public Aluno updateAluno(Long id, Aluno detalhesAluno) {
        if (id == null) {
            throw new IllegalArgumentException("O ID não pode ser nulo.");
        }
        
        if (detalhesAluno.getNome() == null || detalhesAluno.getNome().isEmpty()) {
            throw new IllegalArgumentException("O nome do aluno não pode ser vazio.");
        }

        if (detalhesAluno.getEmail() == null || detalhesAluno.getEmail().isEmpty()) {
            throw new IllegalArgumentException("O email do aluno não pode ser vazio.");
        }

        if (detalhesAluno.getNumAluno() <= 0) {
            throw new IllegalArgumentException("O nº do aluno deve ser maior que 0.");
        }
        System.out.println("Atualizando aluno com ID: " + id);
        System.out.println("Detalhes do Aluno: " + detalhesAluno);

        Aluno aluno = alunosRepository.findById(id).orElse(null);
        if (aluno != null) {
        	if (utilizadorRepository.existsByEmail(detalhesAluno.getEmail())) {
                throw new RuntimeException("Email já registrado por outro aluno!");
            }
        	aluno.setNome(detalhesAluno.getNome());
            aluno.setEmail(detalhesAluno.getEmail());
            aluno.setAnoEscolaridade(detalhesAluno.getAnoEscolaridade());
            aluno.setNumAluno(detalhesAluno.getNumAluno());

            return alunosRepository.save(aluno);
        } else {
            System.out.println("Aluno não encontrado com id: " + id);
        }
        return null; 
    }
    
    
    public void eliminarAluno(Long id) {
        if (alunosRepository.existsById(id)) {
            alunosRepository.deleteById(id);
        } else {
            throw new RuntimeException("Aluno não encontrado com o id: " + id);
        }
    }
}


