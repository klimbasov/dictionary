package com.dictionary.dao;

import com.dictionary.entity.WordPackage;

import java.util.ArrayList;

public interface DictionaryStorage {
    void addDictionaryRecord(final WordPackage wordPackage);

    String getTranslation(final String word);

    ArrayList<String> getWords(final String translation);

    ArrayList<WordPackage> getAllWords();

    Integer getDictionarySize();

    ArrayList<WordPackage> getSetOfRandomWords(final Integer setSize);
}
