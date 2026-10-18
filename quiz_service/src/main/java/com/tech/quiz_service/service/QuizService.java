package com.tech.quiz_service.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.tech.quiz_service.fegin.quizInterface;
import com.tech.quiz_service.model.QuestionWrapper;
import com.tech.quiz_service.model.QuizResponse;
import com.tech.quiz_service.model.Quiz;
import com.tech.quiz_service.repository.QuizRepository;

@Service 
public class QuizService {
    @Autowired 
    QuizRepository quizDao;
    
    @Autowired 
    quizInterface quizInterface;

    public ResponseEntity<String> createQuiz(String category, int numQ, String title)
    {
       List<Integer> questions = quizInterface.getQuestionForQuiz(category, numQ).getBody();
        
        Quiz quiz=new Quiz();
        quiz.setTitle(title);
        quiz.setQuestions(questions);
        quizDao.save(quiz);
        return new ResponseEntity<>("Quiz Created Succesfully...",HttpStatus.CREATED);
    }
    public ResponseEntity<List<QuestionWrapper>> getQuiz(int id)
    {
        Quiz quiz = quizDao.findById(id).get();
        List<Integer> questionFromDb = quiz.getQuestions();
        ResponseEntity<List<QuestionWrapper>> questionsForUsers = quizInterface.getQuestionsFromIds(questionFromDb);
        return questionsForUsers; 
    }
    public ResponseEntity<String> getQuizResult(int id, List<QuizResponse> quizResponse)
    {
        Quiz quiz =quizDao.findById(id).get();
        // List<Question> questions = quiz.getQuestions();
        int right =0;
        // int i=0;
        // for(QuizResponse response: quizResponse)
        // {
        //     //here i is using for referring index of quiz question not to id of question...
        //     if(response.getResponse().equals(questions.get(i).getRightAnswer()))
        //     {
        //         right ++;
        //     }  
        //     i++;
        // }
        return new ResponseEntity<>("The Right Answers - "+right,HttpStatus.OK);
    }
    
}

