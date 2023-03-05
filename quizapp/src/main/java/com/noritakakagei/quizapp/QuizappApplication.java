package com.noritakakagei.quizapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import com.noritakakagei.quizapp.entity.Quiz;
import com.noritakakagei.quizapp.service.QuizService;

@SpringBootApplication
public class QuizappApplication {

	public static void main(String[] args) {
		SpringApplication.run(QuizappApplication.class, args);
	}

	// Dependency Injection
	@Autowired
	QuizService service;

	private void execute() {
		init();
		showList();
		showOne();
		update();
		// delete();
		doQuiz();
	}

	private void init() {
		System.out.println("--- Initialize Quiz Database ---");

		Quiz quiz1 = new Quiz(null, "Spring is Framework.", true, "me");
		Quiz quiz2 = new Quiz(null, "Spring MVC provides Batch function.", false, "me");
		Quiz quiz3 = new Quiz(null, "Java is object oriented programming model.", true, "me");
		Quiz quiz4 = new Quiz(null, "@Component is annotation for create instance of attached class.", true, "me");
		Quiz quiz5 = new Quiz(null, "Single Controller Pattern is a part of design pattern implemented by Spring MVC for handling all requests by one controller.", false, "me");

		List<Quiz> list = new ArrayList<>();
		Collections.addAll(list, quiz1, quiz2, quiz3, quiz4, quiz5);

		for (Quiz quiz : list) {
			service.insertQuiz(quiz);
		}
		
		System.out.println("--------------------------------");
	}

	private void showList() {
		System.out.println("--- Getting all registered quizzes ---");

		Iterable<Quiz> quizzes = service.selectAll();
		for (Quiz quiz : quizzes) {
			System.out.println(quiz);
		}
		
		System.out.println("--------------------------------------");
	}

	private void showOne() {
		System.out.println("--- Getting a registered quiz ---");

		Optional<Quiz> quizOpt = service.selectOneById(1);
		if (quizOpt.isPresent()) {
			System.out.println(quizOpt.get());
		} else {
			System.out.println("[FAIL] Not found a matched quiz");
		}
		
		System.out.println("---------------------------------");
	}

	private void update() {
		System.out.println("--- Updating a quiz ---");

		Quiz quiz = new Quiz(1, "Spring(Boot, Web, MVC, Data, and so on) are frameworks", true, "me");
		service.updateQuiz(quiz);
		System.out.println("Updated a quiz: "+quiz);

		System.out.println("-----------------------");
	}

	private void delete() {
		System.out.println("--- Deleting a quiz ---");

		service.deleteQuizById(1);
		showList();

		System.out.println("-----------------------");
	}

	private void doQuiz() {
		System.out.println("--- Start Quiz Application ---");

		Optional<Quiz> quizOpt = service.selectOneRandomQuiz();
		if (quizOpt.isPresent()) {
			System.out.println(quizOpt.get());
		} else {
			System.out.println("[FAIL] not found quiz in database");
		}

		Boolean expected = false;
		Integer id = quizOpt.get().getId();

		if (Boolean.TRUE.equals(service.checkQuiz(id, expected))) {
			System.out.println("Correct!!");
		} else {
			System.out.println("Wrong....");
		}
	}
}
