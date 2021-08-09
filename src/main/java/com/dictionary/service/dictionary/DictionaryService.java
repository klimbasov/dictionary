package com.dictionary.service.dictionary;

import com.dictionary.entity.WordPackage;

import java.util.List;

public interface DictionaryService {
    void addWord(final String word, final String translation)throws Exception;

    List<String> getWord(final String translation)throws Exception;

    String getTranslation(final String word)throws Exception;

    List<WordPackage> getAllWords()throws Exception;

    Integer getDictionarySize()throws Exception;

}
