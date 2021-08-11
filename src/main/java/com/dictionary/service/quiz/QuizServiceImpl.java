package com.dictionary.service.quiz;

import com.dictionary.dao.DictionaryStorage;
import com.dictionary.entity.WordPackage;

import java.util.ArrayList;

public class QuizServiceImpl implements QuizService {
    private static DictionaryStorage dictionaryStorage;

    public QuizServiceImpl(final DictionaryStorage dictionaryStorage) {
        QuizServiceImpl.dictionaryStorage = dictionaryStorage;
    }

    @Override
    public ArrayList<WordPackage> getQuizPackage(Integer quizLength) {
        ArrayList<WordPackage> wordPackages = null;
        if (quizLength > 0) {
            wordPackages = dictionaryStorage.getSetOfRandomWords(quizLength);
        }
        return wordPackages;

    }
}
