package com.tech.question_service.model;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor  
public class QuizResponse {

    private int id;
    private String response;
    
}
