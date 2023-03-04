package com.noritakakagei.sample.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.noritakakagei.sample.form.CalcForm;
import com.noritakakagei.sample.validator.CalcValidator;

@Controller
public class ValidationController {

    // Initialize form-backing bean
    @ModelAttribute
    public CalcForm setUpForm() {
        return new CalcForm();
    }

    // Dependency Injection
    @Autowired
    CalcValidator validator;

    // Register relative validation
    @InitBinder("calcForm")
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(validator);
    }

    @GetMapping("input")
    public String showInput() {
        return "input";
    }

    @PostMapping("calc")
    public String calc(@Validated CalcForm form, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()){
            return "input";
        }

        Integer result = form.getLeftNum() + form.getRightNum();
        model.addAttribute("result", result);
        return "output";
    }
}