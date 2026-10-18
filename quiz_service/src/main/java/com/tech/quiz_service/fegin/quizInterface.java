package com.tech.quiz_service.fegin;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.tech.quiz_service.model.QuestionWrapper;
import com.tech.quiz_service.model.QuizResponse;

@FeignClient ("QUESTION-SERVICE")
public interface quizInterface {
    //Generate
    @GetMapping ("generate")
    public ResponseEntity<List<Integer>> getQuestionForQuiz(@RequestParam String categoryName,@RequestParam int numberOfQuestions);
    

    //GetQuestion
    @PostMapping ("getQuestions")
    public ResponseEntity<List<QuestionWrapper>> getQuestionsFromIds(@RequestBody List<Integer> questionIds);
   

    //GetScore
    @PostMapping ("GetScore")
    public ResponseEntity<Integer> getScore(@RequestBody List<QuizResponse> responses);
   
    
}
