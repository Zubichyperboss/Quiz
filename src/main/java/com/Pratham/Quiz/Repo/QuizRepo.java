package com.Pratham.Quiz.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Pratham.Quiz.Model.QuizModel;

public interface QuizRepo extends JpaRepository<QuizModel,Integer> {

	
	
}
