package com.upt.hibernate.proj_9grupo.service;

import com.upt.hibernate.proj_9grupo.model.Disciplina;
import com.upt.hibernate.proj_9grupo.model.Professor;
import com.upt.hibernate.proj_9grupo.model.Quiz;
import com.upt.hibernate.proj_9grupo.repository.DisciplinaRepository;
import com.upt.hibernate.proj_9grupo.repository.ProfessorRepository;
import com.upt.hibernate.proj_9grupo.repository.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService {

	private final QuizRepository quizRepository;
	private final ProfessorRepository professorRepository;
	private final DisciplinaRepository disciplinaRepository;
	
	@Autowired
	public QuizService(QuizRepository quizRepository, ProfessorRepository professorRepository, DisciplinaRepository disciplinaRepository) {
		this.quizRepository = quizRepository;
		this.professorRepository = professorRepository;
		this.disciplinaRepository =  disciplinaRepository;
	}
	
	public List<Quiz> getAllQuizzes(){
		return quizRepository.findAll();
	}
	
	public Optional<Quiz> getQuizById(Long id) {
		return quizRepository.findById(id);
		}

	public Quiz criarQuiz(Quiz quiz, int professorId, Long disciplinaId) {
		if(quiz.getTitulo() == null || quiz.getTitulo().isEmpty()) {
			throw new RuntimeException("O título do quiz não pode ser vazio!");
		}
		
		if (quiz.getProfessor() == null || !professorRepository.existsById((long) quiz.getProfessor().getId())) {
	        throw new RuntimeException("Apenas professores registrados podem criar quizzes!");
	    }
		
		if(quiz.getProfessor() == null) {
			throw new RuntimeException("O professor não pode ser nulo!!");
		}
		
		if(quiz.getDisciplina() == null) {
			throw new RuntimeException("A disciplina não pode ser nula!!");
		}
		
		Optional<Professor> professorExistente = professorRepository.findById((long) quiz.getProfessor().getId());
		if (!professorExistente.isPresent()) {
		    throw new RuntimeException("Professor não encontrado!");
		}
		
		Optional<Disciplina> disciplinaExistente = disciplinaRepository.findById((long) quiz.getDisciplina().getId());
		if (!disciplinaExistente.isPresent()) {
		    throw new RuntimeException("Disciplina não encontrada!");
		}
		
		return quizRepository.save(quiz);
	}

	public void eliminarQuiz(Long id) {
		if (quizRepository.existsById(id)) {
				quizRepository.deleteById(id);
		} else {
				throw new RuntimeException("Quiz não encontrado com o id: "+ id);
		}
	}
	
}
	
	
	
	

