package com.tech.question_service.controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tech.question_service.model.Question;
import com.tech.question_service.model.QuestionWrapper;
import com.tech.question_service.model.QuizResponse;
import com.tech.question_service.service.QuestionService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController 
@RequestMapping ("questions")
public class QuestionController {

    @Autowired 
    QuestionService questionService;

    @GetMapping("getAllQuestions")
    public ResponseEntity<List<Question>> getAllQuestions()
    {
        return questionService.getAllQuestions() ;
    }
    @GetMapping("category/{category}")
    public ResponseEntity<List<Question>> getQuestions(@PathVariable("category")String category)
    {
        return questionService.getQuestionByCategory(category);
    }
    @PostMapping("addQuestion")
    public ResponseEntity<Question> addQuestion(@RequestBody Question question) {

        return questionService.addQuestion(question);
    }
    @DeleteMapping("deleteQuestion/{id}")
    public ResponseEntity<String> deleteQuestion(@PathVariable("id") int id) {
        
        return questionService.deleteQuestion(id);
    }
    @PatchMapping ("updateQuestion")
    public ResponseEntity<String> updateQuestion(@RequestBody Question question)
    {
        return questionService.updateQuestion(question);
    }

    //Generate
    @GetMapping ("generate")
    public ResponseEntity<List<Integer>> getQuestionForQuiz(@RequestParam String categoryName,@RequestParam int numberOfQuestions)
    {
        return questionService.getQuestionForQuiz(categoryName,numberOfQuestions);
    }

    //GetQuestion
    @PostMapping ("getQuestions")
    public ResponseEntity<List<QuestionWrapper>> getQuestionsFromIds(@RequestBody List<Integer> questionIds)
    {
        return questionService.getQuestionsFromIds(questionIds);
    }

    //GetScore
    @PostMapping ("GetScore")
    public ResponseEntity<Integer> getScore(@RequestBody List<QuizResponse> responses)
    {
        return questionService.getScore(responses);
    }
    
}
