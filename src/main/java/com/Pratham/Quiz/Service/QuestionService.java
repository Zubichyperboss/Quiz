package com.Pratham.Quiz.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.Pratham.Quiz.Model.QuestionModel;


import com.Pratham.Quiz.Repo.QuestionRepo;

@Service
public class QuestionService {

	@Autowired
	QuestionRepo qRepo;
	
	public List<QuestionModel> getAllQuestions(){
		return qRepo.findAll();
	}
	
	public List<QuestionModel> getAllByCategory(String Category){
		return qRepo.findBycategory(Category);
	}
	
	public ResponseEntity<String> saveQuestion(QuestionModel question) {
		 qRepo.save(question);
		 return new ResponseEntity<>("cretedd",HttpStatus.CREATED);
	}
	
	
}
