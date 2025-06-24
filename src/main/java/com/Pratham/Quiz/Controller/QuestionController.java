package com.Pratham.Quiz.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Pratham.Quiz.Model.QuestionModel;
import com.Pratham.Quiz.Service.QuestionService;

@RestController
@RequestMapping("/question")
public class QuestionController {
	
	@Autowired
	QuestionService qService;
	
	@GetMapping("/findAllQuestions")
	public List<QuestionModel> findAllQuestion(){
		return qService.getAllQuestions();
	}
	//check this change
	/*
    @GetMapping("/rika")
//	@RequestMapping(path="/tola")
	public String seka() {
		return "hisi";
	}
*/
	
	@GetMapping("/category/{category}")
	public List<QuestionModel> findCategory(@PathVariable String category){
		return qService.getAllByCategory(category);
	}
	
	@PostMapping("/addQuestion")
	public ResponseEntity<String> addQuestiob(@RequestBody QuestionModel question) {
		return qService.saveQuestion(question);
	}
}
