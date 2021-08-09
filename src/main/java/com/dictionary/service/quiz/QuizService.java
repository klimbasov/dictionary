package com.dictionary.service.quiz;

import com.dictionary.entity.WordPackage;

import java.util.ArrayList;

public interface QuizService {
    ArrayList<WordPackage> getQuizPackage(Integer quizLength);
}
