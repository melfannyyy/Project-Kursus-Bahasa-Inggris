package com.example.demospringboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demospringboot.entity.Question;
import com.example.demospringboot.service.QuestionService;

@Controller
public class AssessmentController {

    @Autowired
    private QuestionService questionService;

    @GetMapping("/test")
    public String showTestPage(Model model) {
        List<Question> questions = questionService.getQuestions();
        model.addAttribute("questions", questions);
        return "assessment/english-test";
    }
}
