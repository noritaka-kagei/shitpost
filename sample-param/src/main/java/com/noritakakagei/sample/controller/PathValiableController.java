package com.noritakakagei.sample.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class PathValiableController {

    @GetMapping("/parameter/{param}")
    public String showParam(Model model, @PathVariable String param) {
        String result = "error";
        switch (param) {
            case "index":
                result = "index";
                break;
            default:
                model.addAttribute("param", param);
        }
        return result;
    }
}