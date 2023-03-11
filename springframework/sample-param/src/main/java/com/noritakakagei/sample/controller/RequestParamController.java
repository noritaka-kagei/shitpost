package com.noritakakagei.sample.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.noritakakagei.sample.form.Form;

@Controller
public class RequestParamController {

    @GetMapping("index*")
    public String showIndex(){
        return "index";
    }

    @PostMapping("confirm")
    public String confirmView(Form form) {
        return "confirm";
    }
}