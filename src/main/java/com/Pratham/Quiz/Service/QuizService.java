package com.Pratham.Quiz.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.Pratham.Quiz.Model.QuestionModel;
import com.Pratham.Quiz.Model.QuestionWrapper;
import com.Pratham.Quiz.Model.QuizModel;
import com.Pratham.Quiz.Model.ResponseModel;
import com.Pratham.Quiz.Repo.QuestionRepo;
import com.Pratham.Quiz.Repo.QuestionWrapperRepo;
import com.Pratham.Quiz.Repo.QuizRepo;
import org.springframework.data.domain.Pageable;

@Service
public class QuizService {

	@Autowired
	QuizRepo quRepo;

	@Autowired
	QuestionRepo qRepo;
	
	@Autowired
	QuestionWrapperRepo qwRepo;

	public ResponseEntity<String> createQuiz(String category, int numq, String title) {

		Pageable pageable = PageRequest.of(0, numq);
		List<QuestionModel> qml = qRepo.findRandomQuestionByCategory(category, pageable);

		QuizModel qm = new QuizModel();
		qm.setTitle(title);
		qm.setQuestions(qml);
		quRepo.save(qm);
		return new ResponseEntity<>("success created", HttpStatus.CREATED);
	}
	
	public ResponseEntity<List<QuestionWrapper>> getQuizWrapper(int id) {
		// TODO Auto-generated method stub
		List<QuestionWrapper> showquiz = new ArrayList<>();


		List<QuestionModel> quizlistdata = quRepo.findById(id).get().getQuestions();

		for (int i = 0; i < quizlistdata.size(); i++) {
			
			QuestionWrapper qw=new QuestionWrapper();
			int lid=quizlistdata.get(i).getId();
			
		qw=qwRepo.getQuizById(lid);
//			qw=qwRepo.findById(lid).orElse(qw); not workung
			showquiz.add(qw);
			
			
		}

		return new ResponseEntity<>(showquiz, HttpStatus.OK);
	}

	public ResponseEntity<List<QuestionWrapper>> getQuiz(int id) {
		// TODO Auto-generated method stub

		Optional<QuizModel> quizdata = quRepo.findById(id);

		List<QuestionModel> quizlistdata = quizdata.get().getQuestions();

		List<QuestionWrapper> questionwrapper = new ArrayList<>();

		for (QuestionModel question : quizlistdata) {

			QuestionWrapper qwrapper = new QuestionWrapper();
			qwrapper.setId(question.getId());
			qwrapper.setDifficultyLevel(question.getDifficultyLevel());
			qwrapper.setOption1(question.getOption1());
			qwrapper.setOption2(question.getOption2());
			qwrapper.setOption3(question.getOption3());
			qwrapper.setOption4(question.getOption4());
			qwrapper.setQuestionTitle(question.getQuestionTitle());
			questionwrapper.add(qwrapper);
		}

		// Optional<QuestionWrapper> h

		return new ResponseEntity<>(questionwrapper, HttpStatus.CREATED);

	}

	public ResponseEntity<Integer> checkResult(int id, List<String> sanswer) {
		// TODO Auto-generated method stub
		int rightanswer = 0;

		Optional<QuizModel> quizdata = quRepo.findById(id);

		List<QuestionModel> quizlistdata = quizdata.get().getQuestions();

		for (int i = 0; i < quizlistdata.size(); i++) {

			if (quizlistdata.get(i).getRightAnswer().equals(sanswer.get(i))) {
				rightanswer++;
			}

		}

		return new ResponseEntity<>(rightanswer, HttpStatus.ACCEPTED);
	}

	public ResponseEntity<Integer> getResult(int id, List<ResponseModel> response) {
		// TODO Auto-generated method stub
		int answer = 0;

		Optional<QuizModel> quizdata = quRepo.findById(id);
		List<QuestionModel> ans = quizdata.get().getQuestions();

		for (int i = 0; i < ans.size(); i++) {
			if (response.get(i).getResponse().equals(ans.get(i).getRightAnswer())) {
				answer++;
			}

		}

		return new ResponseEntity<>(answer, HttpStatus.ACCEPTED);
	}



}
