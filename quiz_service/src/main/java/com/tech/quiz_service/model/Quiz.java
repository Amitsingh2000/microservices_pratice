package com.tech.quiz_service.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.Data;

@Entity 
@Data 
public class Quiz {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    int id;
    String title;
    @ManyToMany 
    List<Question> questions; 
    
}
