package com.tech.quiz_service.model;

import lombok.Data;

@Data 
public class QuizDto {
   
    String title; 
    int numQ;
    String category;
}
