package com.Pratham.Quiz.Repo;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.Pratham.Quiz.Model.QuestionModel;
import com.Pratham.Quiz.Model.QuestionWrapper;


public interface QuestionRepo extends JpaRepository<QuestionModel,Integer> {

	List<QuestionModel> findBycategory(String category);

	@Query(value="select * from question where category=:category order by rand()",nativeQuery=true)
	List<QuestionModel> findRandomQuestionByCategory(@Param("category")String category,Pageable pageable);

	

}
