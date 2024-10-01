package com.quiz.quizApp.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.quiz.quizApp.model.Question;
import com.quiz.quizApp.model.QuestionWrapper;
import com.quiz.quizApp.model.Quiz;
import com.quiz.quizApp.model.Response;
import com.quiz.quizApp.repository.QuestionRepository;
import com.quiz.quizApp.repository.QuizRepository;

@Service
public class QuizService {

	@Autowired
	private QuizRepository quizRepository;
	@Autowired
	private QuestionRepository questionRepository;
	
	public ResponseEntity<String> createQuiz(String category, int numQ, String title) {
		
		List<Question> questions  = questionRepository.findRandomQuestiionsByCategory(category, numQ);
		
		Quiz quiz = new Quiz();
		quiz.setTitle(title);
		quiz.setQuestion(questions);
		quizRepository.save(quiz);
		
		return new ResponseEntity<>("SUCCESS",HttpStatus.CREATED);
		
		
	}

	public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(Integer id) {
		Optional<Quiz> quiz=quizRepository.findById(id);
		List<Question> questionsFromDB = quiz.get().getQuestion();
		List<QuestionWrapper>  questionsForUser = new ArrayList<>();
		for(Question q:questionsFromDB) {
			QuestionWrapper qw=new QuestionWrapper(q.getId(), q.getQuestionTitle(), q.getCategory(), q.getOption1(), q.getOption2(), q.getOption3(), q.getOption4());
			questionsForUser.add(qw);
			
		}
		return new ResponseEntity<>(questionsForUser,HttpStatus.OK);
	}

	public ResponseEntity<Integer> calculateResult(Integer id, List<Response> responses) {
		
		Optional<Quiz> quiz = quizRepository.findById(id);
		List<Question> questions=quiz.get().getQuestion();
		int right=0;
		int i=0;
		for(Response response:responses) {
			if(response.getResponse().equals(questions.get(i).getRightAnswer()))
				right++;
			i++;
			
			
		}
		
		return new ResponseEntity<>(right,HttpStatus.OK);
	}
	

}
