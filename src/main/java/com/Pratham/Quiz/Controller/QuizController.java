package com.Pratham.Quiz.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.Pratham.Quiz.Model.QuestionModel;
import com.Pratham.Quiz.Model.QuestionWrapper;
import com.Pratham.Quiz.Model.QuizModel;
import com.Pratham.Quiz.Model.ResponseModel;
import com.Pratham.Quiz.Service.QuizService;

@RestController
@RequestMapping("/quiz")
public class QuizController {

	@Autowired
	QuizService qService;
	
	@PostMapping("/addQuiz/{category}/{numq}/{title}")
	public ResponseEntity<String> addQuiz(@PathVariable String category,@PathVariable int numq,@PathVariable String title){
		
		return qService.createQuiz(category,numq,title);
	}
	
	@GetMapping("/get")
	public ResponseEntity<List<QuestionWrapper>> getQuestion(@RequestParam int id ){
		
		//System.out.println(id);
		return qService.getQuiz(id);
	}
	
	@GetMapping("/getWrapper")
	public ResponseEntity<List<QuestionWrapper>> getQuestionWrapper(@RequestParam int id ){
		
		//System.out.println(id);
		return qService.getQuizWrapper(id);
	}
	
	@GetMapping("/submit/{id}")
	public ResponseEntity<Integer> checkAnswer(@PathVariable int id,@RequestBody List<String> sanswer){
		
		return qService.checkResult(id,sanswer);
	}
	
	@GetMapping("/submitR/{id}")
	public ResponseEntity<Integer> checkAnswers(@PathVariable int id,@RequestBody List<ResponseModel> response){
		return qService.getResult(id,response);
	}
	
}
