package com.crio.codingame.services;


import java.util.Collections;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import com.crio.codingame.entities.Level;
import com.crio.codingame.entities.Question;
import com.crio.codingame.repositories.IQuestionRepository;

public class QuestionService implements IQuestionService{
    private final IQuestionRepository questionRepository;

    public QuestionService(IQuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }


    @Override
    public Question create(String title, Level level, Integer difficultyScore) {
     final Question question = new Question(title,level, difficultyScore);
        return questionRepository.save(question);
    }

    // TODO: CRIO_TASK_MODULE_SERVICES
    // Get All Questions if level is not specified.
    // Or
    // Get List of Question which matches the level provided.

    @Override
    public List<Question> getAllQuestionLevelWise(Level level) {

        if(level==null){
            return questionRepository.findAll().stream().collect(Collectors.toList());
        }
       switch(level){

        case LOW:
        return questionRepository.findAllQuestionLevelWise(Level.LOW).stream().collect(Collectors.toList());
        case MEDIUM:
        return questionRepository.findAllQuestionLevelWise(Level.MEDIUM).stream().collect(Collectors.toList());
        case HIGH:
        return questionRepository.findAllQuestionLevelWise(Level.HIGH).stream().collect(Collectors.toList());
        default:
        return Collections.emptyList();

       }
    
    }
}
