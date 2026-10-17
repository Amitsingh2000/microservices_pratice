package com.tech.question_service.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.tech.question_service.model.Question;

import java.util.List;


@Repository 
public interface QuestionRepository extends JpaRepository<Question, Integer>
{
    List<Question> findByCategory(String category);
    public void deleteById(Integer id);
    @Query (value = "Select q.id from question q where q.category=:category order by random() limit :numQ",nativeQuery = true)
    public List<Integer>findRandomQuestionByCategory(String category,int numQ);
}


