package com.noritakakagei.sample.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PathValiableController {

    @GetMapping("/parameter")
    @ResponseBody
    public String showParamResponse(@RequestParam String response) {
        // HTTP Access to /parameter?response=hoge -> return "hoge"
        return response;
    }

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