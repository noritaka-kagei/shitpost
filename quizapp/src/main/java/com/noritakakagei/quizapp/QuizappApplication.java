package com.noritakakagei.quizapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Optional;

import com.noritakakagei.quizapp.entity.Quiz;
import com.noritakakagei.quizapp.repository.QuizRepository;

@SpringBootApplication
public class QuizappApplication {

	public static void main(String[] args) {
		SpringApplication.run(QuizappApplication.class, args)
		.getBean(QuizappApplication.class)
		.execute();;
	}

	// Dependency Injection
	@Autowired
	QuizRepository repo;

	private void execute() {
		// init();
		showList();
		showOne();
		update();
		delete();
		showList();
	}

	private void init() {
		Quiz quiz = new Quiz(null, "Is the Spring Framework?", true, "noritaka");
		quiz = repo.save(quiz);
		System.out.println("Update Quiz item: "+quiz);

		Quiz quiz2 = new Quiz(null, "Does Spring MVC provide Batch function?", false, "noritaka");
		quiz2 = repo.save(quiz2);
		System.out.println("Register Quiz item: "+quiz2);
	}

	private void showList() {
		System.out.println("--- Getting registered quizzes ---");

		Iterable<Quiz> quizzes = repo.findAll();
		for (Quiz quiz : quizzes) {
			System.out.println(quiz);
		}
		
		System.out.println("------------------------------------");
	}

	private void showOne() {
		System.out.println("--- Getting a registered quiz ---");

		Optional<Quiz> quiz = repo.findById(1);
		if (quiz.isPresent()) {
			System.out.println(quiz.get());
		} else {
			System.out.println("Not found a matched quiz");
		}
		
		System.out.println("---------------------------------");
	}

	private void update() {
		System.out.println("--- Start to update a quiz ---");

		Quiz quiz = new Quiz(1, "Is Spring framework?", true, "me");
		quiz = repo.save(quiz);
		System.out.println("Updated a quiz: "+quiz);

		System.out.println("------------------------------");
	}

	private void delete() {
		System.out.println("--- Deleting a quiz ---");

		repo.deleteById(1);

		System.out.println("-----------------------");
	}
}
