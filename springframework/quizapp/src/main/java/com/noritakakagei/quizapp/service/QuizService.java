package com.noritakakagei.quizapp.service;

import java.util.Optional;

import com.noritakakagei.quizapp.entity.Quiz;

public interface QuizService {
    Iterable<Quiz> selectAll();
    Optional<Quiz> selectOneById(Integer id);
    Optional<Quiz> selectOneRandomQuiz();
    Boolean checkQuiz(Integer id, Boolean myAnswer);
    void insertQuiz(Quiz quiz);
    void updateQuiz(Quiz quiz);
    void deleteQuizById(Integer id);
}