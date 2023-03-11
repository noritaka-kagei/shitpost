package com.noritakakagei.quizapp.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    public String insert(
        @Validated QuizForm form, 
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

    @GetMapping("/{id}")
    public String update(QuizForm form, @PathVariable Integer id, Model model) {
        Optional<Quiz> quizOpt = service.selectOneById(id);
        Optional<QuizForm> formOpt = quizOpt.map(t -> makeQuizForm(t));

        if (formOpt.isPresent()) {
            form = formOpt.get();
        }

        makeUpdateModel(form, model);

        return "crud";
    }

    @PostMapping("/update")
    public String update(
        @Validated QuizForm form, 
        BindingResult bindingResult,
        Model model,
        RedirectAttributes redirectAttributes) {

            if (bindingResult.hasErrors()) {
                makeUpdateModel(form, model);
                return "crud";
            }

            Quiz quiz = makeQuiz(form);
            service.updateQuiz(quiz);
            
            redirectAttributes.addFlashAttribute("complete", "Successfully updated a quiz!!");
            return "redirect:/quiz/"+quiz.getId();
    }

    @PostMapping("/delete")
    public String delete(
        @RequestParam("id") String id,
        Model model,
        RedirectAttributes redirectAttributes) {
            service.deleteQuizById(Integer.parseInt(id));
            redirectAttributes.addFlashAttribute("delcomplete", "Successfully deleted a quiz");
            return "redirect:/quiz";
    }

    @GetMapping("/play")
    public String play(QuizForm form, Model model) {
        Optional<Quiz> quizOpt = service.selectOneRandomQuiz();
        if (! quizOpt.isPresent()) {
            model.addAttribute("msg", "no quizzes in Database");
            return "play";
        }

        Optional<QuizForm> formOpt = quizOpt.map(t -> makeQuizForm(t));
        form = formOpt.get();
        model.addAttribute("quizForm", form);
        return "play";
    }

    @PostMapping("/check")
    public String check(QuizForm form, @RequestParam Boolean answer, Model model) {
        if (Boolean.TRUE.equals(service.checkQuiz(form.getId(), answer))) {
            model.addAttribute("msg", "Correct!!!");
        } else {
            model.addAttribute("msg", "Wrong...");
        }
        return "answer";
    }

    private void makeUpdateModel(QuizForm form, Model model) {
        form.setNewQuiz(false);
        model.addAttribute("id", form.getId());
        model.addAttribute("quizForm", form);
        model.addAttribute("title", "Updating Form");
    }

    /** Convert QuizForm to Quiz */
    private Quiz makeQuiz(QuizForm form) {
        Quiz quiz = new Quiz();
        quiz.setId(form.getId());
        quiz.setQuestion(form.getQuestion());
        quiz.setAnswer(form.getAnswer());
        quiz.setAuthor(form.getAuthor());
        return quiz;
    }

    /** Convert Quiz to QuizForm */
    private QuizForm makeQuizForm(Quiz t) {
        QuizForm form = new QuizForm();
        form.setId(t.getId());
        form.setQuestion(t.getQuestion());
        form.setAnswer(t.getAnswer());
        form.setAuthor(t.getAuthor());
        form.setNewQuiz(false);
        return form;
    }
}