package com.noritakakagei.quizapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.noritakakagei.quizapp.entity.Quiz;
import com.noritakakagei.quizapp.form.QuizForm;
import com.noritakakagei.quizapp.service.QuizService;

@Controller
@RequestMapping("/quiz")
public class QuizController {
    @Autowired
    QuizService service;

    @ModelAttribute
    public QuizForm setupForm() {
        QuizForm form = new QuizForm();
        form.setAnswer(true);
        return form;
    }

    @GetMapping
    public String index(QuizForm form, Model model) {
        form.setNewQuiz(true);

        Iterable<Quiz> list = service.selectAll();
        model.addAttribute("list", list);
        model.addAttribute("title", "Register Form");

        return "crud";
    }

    @PostMapping("/insert")
    public String insert(@Validated QuizForm form, 
        BindingResult bindingResult, 
        Model model, 
        RedirectAttributes redirectAttributes) {

            if (bindingResult.hasErrors()) {
                return index(form, model);
            }

            // Form -> Quiz (instance)
            Quiz quiz = new Quiz();
            quiz.setQuestion(form.getQuestion());
            quiz.setAnswer(form.getAnswer());
            quiz.setAuthor(form.getAuthor());

            // register the quiz to Database
            service.insertQuiz(quiz);
            redirectAttributes.addFlashAttribute("complete", "Successfully registered the quiz.");
            return "redirect:/quiz";
    }
}