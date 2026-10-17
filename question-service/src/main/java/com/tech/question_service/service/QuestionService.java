package com.tech.question_service.service;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.tech.question_service.model.Question;
import com.tech.question_service.model.QuestionWrapper;
import com.tech.question_service.model.QuizResponse;
import com.tech.question_service.repository.QuestionRepository;


@Service 
public class QuestionService {

    @Autowired 
    QuestionRepository questionRepo;
    public ResponseEntity<List<Question>> getAllQuestions()
    {
        try{
            return new ResponseEntity<>(questionRepo.findAll(),HttpStatus.OK);
        }catch(Exception e)
        {
            e.printStackTrace();
        }
        return  new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    public ResponseEntity<List<Question>> getQuestionByCategory(String category)
    {
        try{
            return new ResponseEntity<>(questionRepo.findByCategory(category),HttpStatus.OK);
        }catch(Exception e)
        {
            e.printStackTrace();
        }
        return  new ResponseEntity<>(HttpStatus.BAD_REQUEST);    
    }
    public ResponseEntity<Question> addQuestion(Question question)
    {  
        try{
            return new ResponseEntity<>(questionRepo.save(question),HttpStatus.CREATED);
        }catch(Exception e)
        {
            e.printStackTrace();
        }
        return  new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    public ResponseEntity<String> deleteQuestion(int id)
    {
        questionRepo.deleteById(id);
        return new ResponseEntity<>("Question ID - "+id+"Delete Question Sucessfully..",HttpStatus.OK);
    }
    public ResponseEntity<String> updateQuestion(Question question)
    {
        questionRepo.save(question);
          try{
            return new ResponseEntity<>("Question Updated Succesfully",HttpStatus.OK);
        }catch(Exception e)
        {
            e.printStackTrace();
        }
        return  new ResponseEntity<>("Question Updation Failed.",HttpStatus.BAD_REQUEST);    
}
    public ResponseEntity<List<Integer>> getQuestionForQuiz(String categoryName, int numberOfQuestions) 
    {
      return new ResponseEntity<>(questionRepo.findRandomQuestionByCategory(categoryName, numberOfQuestions),HttpStatus.OK);
    }
    public ResponseEntity<List<QuestionWrapper>> getQuestionsFromIds(List<Integer> questionIds) {
      List<QuestionWrapper> wrappers = new ArrayList<>();
      List<Question> questions =new ArrayList<>();
      for(Integer questionId : questionIds)
      {
        questions.add(questionRepo.findById(questionId).get());
      }
      for(Question question : questions)
      {
        QuestionWrapper qw =new  QuestionWrapper();
        qw.setId(question.getId());
        qw.setQuestionTitle(question.getQuestionTitle());
        qw.setOption1(question.getOption1());
        qw.setOption2(question.getOption2());
        qw.setOption3(question.getOption3());
        qw.setOption4(question.getOption4());
        wrappers.add(qw);
        }
        
        return new ResponseEntity<>(wrappers,HttpStatus.OK);
    }
    public ResponseEntity<Integer> getScore(List<QuizResponse> responses) {
       
        int right =0;
        for(QuizResponse response: responses)
        {
            Question question = questionRepo.findById(response.getId()).get();
            //here i is using for referring index of quiz question not to id of question...
            if(response.getResponse().equals(question.getRightAnswer()))
            {
                right ++;
            }  
        }
        return new ResponseEntity<>(right,HttpStatus.OK);
    }
}