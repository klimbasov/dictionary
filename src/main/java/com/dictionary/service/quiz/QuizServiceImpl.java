package com.dictionary.service.quiz;

import com.dictionary.dao.DictionaryStorage;
import com.dictionary.entity.WordPackage;

import java.util.ArrayList;

public class QuizServiceImpl implements QuizService{
    private static DictionaryStorage dictionaryStorage;

    public QuizServiceImpl(final DictionaryStorage dictionaryStorage){
        this.dictionaryStorage = dictionaryStorage;
    }

    @Override
    public ArrayList<WordPackage> getQuizPackage(Integer quizLength) {
        return dictionaryStorage.getSetOfRandomWords(quizLength);
    }
}
