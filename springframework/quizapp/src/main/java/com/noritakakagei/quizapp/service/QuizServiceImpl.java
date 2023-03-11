package com.noritakakagei.quizapp.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.noritakakagei.quizapp.entity.Quiz;
import com.noritakakagei.quizapp.repository.QuizRepository;

@Service
@Transactional
public class QuizServiceImpl implements QuizService {

    // Dependency Injection
    @Autowired
    QuizRepository repo;

    @Override
    public Iterable<Quiz> selectAll() {
        return repo.findAll();
    }

    @Override
    public Optional<Quiz> selectOneById(Integer id) {
        return repo.findById(id);
    }

    @Override
    public Optional<Quiz> selectOneRandomQuiz() {
        Integer randId = repo.getRandomId();

        if (randId == null) {
            return Optional.empty();
        }

        return repo.findById(randId);
    }

    @Override
    public Boolean checkQuiz(Integer id, Boolean expectedAnswer) {
        Boolean result = false;
        Optional<Quiz> quizOpt = repo.findById(id);

        if (quizOpt.isPresent()) {
            Quiz quiz = quizOpt.get();

            if (quiz.getAnswer().equals(expectedAnswer)) {
                result = true;
            }
        }

        return result;
    }

    @Override
    public void insertQuiz(Quiz quiz) {
        repo.save(quiz);
    }

    @Override
    public void updateQuiz(Quiz quiz) {
        repo.save(quiz);
    }

    @Override
    public void deleteQuizById(Integer id) {
        repo.deleteById(id);
    }
}