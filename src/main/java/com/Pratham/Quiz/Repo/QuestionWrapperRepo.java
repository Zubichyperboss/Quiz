package com.Pratham.Quiz.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.Pratham.Quiz.Model.QuestionWrapper;

public interface QuestionWrapperRepo  extends JpaRepository<QuestionWrapper,Integer> {


	@Query(value="select id,question_title,difficulty_level,option1,option2,option3,option4 from question where id= :lid",nativeQuery=true)
	QuestionWrapper  getQuizById(@Param("lid")int lid);
	
}
