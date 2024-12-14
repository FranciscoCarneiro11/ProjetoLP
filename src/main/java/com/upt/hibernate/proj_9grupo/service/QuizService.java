package com.upt.hibernate.proj_9grupo.service;

import com.upt.hibernate.proj_9grupo.model.Professor;
import com.upt.hibernate.proj_9grupo.model.Quiz;
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
	
	@Autowired
	public QuizService(QuizRepository quizRepository, ProfessorRepository professorRepository) {
		this.quizRepository = quizRepository;
		this.professorRepository = professorRepository;
	}
	
	public List<Quiz> getAllQuizzes(){
		return quizRepository.findAll();
	}
	
	public Optional<Quiz> getQuizById(Long id) {
		return quizRepository.findById(id);
		}

	public Quiz criarQuiz(Quiz quiz) {
		if(quiz.getTitulo() == null || quiz.getTitulo().isEmpty()) {
			throw new RuntimeException("O título do quiz não pode ser vazio!");
		}
		
		if (quiz.getProfessor() == null || !professorRepository.existsById((long) quiz.getProfessor().getId())) {
	        throw new RuntimeException("Apenas professores registrados podem criar quizzes!");
	    }
		
		if(quiz.getProfessor() == null) {
			throw new RuntimeException("O professor não pode ser nulo||");
		}
		
		Optional<Professor> professorExistente = professorRepository.findById((long) quiz.getProfessor().getId());
		if (!professorExistente.isPresent()) {
		    throw new RuntimeException("Professor não encontrado!");
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
	
	
	
	

