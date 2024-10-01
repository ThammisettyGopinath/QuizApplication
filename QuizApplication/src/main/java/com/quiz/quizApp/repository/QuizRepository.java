package com.quiz.quizApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.quiz.quizApp.model.Quiz;

public interface QuizRepository extends JpaRepository<Quiz, Integer> {
	

}
