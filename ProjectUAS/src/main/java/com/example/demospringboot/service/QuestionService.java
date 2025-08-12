package com.example.demospringboot.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demospringboot.entity.Question;

@Service
public class QuestionService {
    public List<Question> getQuestions() {
        List<Question> questions = new ArrayList<>();
        questions.add(new Question("What is the correct past tense of 'go'?",
                List.of("A) Goed", "B) Went", "C) Going"), "B"));
        questions.add(new Question("Which one is the synonym of 'happy'?",
                List.of("A) Sad", "B) Joyful", "C) Angry"), "B"));
        questions.add(new Question("What is the opposite of 'fast'?",
                List.of("A) Slow", "B) Quick", "C) Swift"), "A"));
        questions.add(new Question("Choose the correct sentence:",
                List.of("A) She don't like apples", "B) She doesn't like apples.", "C) She doesn't likes apples."), "B"));
        questions.add(new Question("I ___ to the park every weekend.",
                List.of("A) goes", "B) Go", "C) gone"), "B"));
        // Tambahkan soal lain sesuai kebutuhan
        return questions;
    }
}

