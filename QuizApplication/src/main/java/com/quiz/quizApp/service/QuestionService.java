package com.quiz.quizApp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quiz.quizApp.QuizApplication;
import com.quiz.quizApp.model.Question;
import com.quiz.quizApp.repository.QuestionRepository;
import com.quiz.quizApp.repository.QuizRepository;

@Service
public class QuestionService {
	
	@Autowired
	private QuestionRepository questionRepository;
	
	
	public String saveQuestion(Question question) {
		questionRepository.save(question);
		return "Question Added Successfully";
	}
	
	public List<Question> getAll(){
		return questionRepository.findAll();
	}
	
	public List<Question> getByCategory(String category){
		return questionRepository.findByCategory(category);
	}

}
