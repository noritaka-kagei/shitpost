package com.noritakakagei.quizapp.repository;

import org.springframework.data.repository.CrudRepository;

import com.noritakakagei.quizapp.entity.Quiz;

public interface QuizRepository extends CrudRepository<Quiz, Integer> {}