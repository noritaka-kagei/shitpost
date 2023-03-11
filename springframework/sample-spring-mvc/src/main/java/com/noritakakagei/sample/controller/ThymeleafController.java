package com.noritakakagei.sample.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.noritakakagei.sample.entity.Member;

@Controller
public class ThymeleafController {

    @GetMapping("index*")
    public String showIndex(Model model) {
        Member member1 = new Member(1, "Noritaka Kagei");
        Member member2 = new Member(10, "SpringFramework");
        Member member3 = new Member(11, "SpringBoot");

        Map<String, Member> memberMap = new HashMap<>();
        memberMap.put("contributor", member1);
        memberMap.put("user1", member2);
        memberMap.put("user2", member3);

        List<Member> memberList = new ArrayList<>();
        memberList.add(member1);
        memberList.add(member2);
        memberList.add(member3);

        model.addAttribute("map", memberMap);
        model.addAttribute("list", memberList);
        model.addAttribute("name", "noritaka");
        return "index";
    }

    @GetMapping("welcome*")
    public String showWelcome() {
        return "welcome";
    }
}