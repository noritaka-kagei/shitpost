package com.noritakakagei.sample.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
// @RequestMapping(value = {"hello", "hellospring"}, method = {RequestMethod.GET, RequestMethod.POST})
@RequestMapping
public class HelloViewController {

    @GetMapping(value = {"view", "index"})
    public String helloView() {
        return "hello";
    }
}