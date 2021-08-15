package com.dictionary.service.dictionary;

import com.dictionary.dao.DictionaryStorage;
import com.dictionary.entity.WordPackage;

import java.util.List;
import java.util.Objects;

public class DictionaryServiceImpl implements DictionaryService {
    private final DictionaryStorage dictionaryStorage;

    public DictionaryServiceImpl(final DictionaryStorage dictionaryStorage) {
        this.dictionaryStorage = dictionaryStorage;
    }

    @Override
    public void addWord(String word, String translation) throws Exception {
        if (word == null || translation == null) {
            throw new Exception("Either Word or translation were not exist.");
        }
        dictionaryStorage.addDictionaryRecord(new WordPackage(word, translation));
    }

    @Override
    public List<String> getWord(String translation) throws Exception {
        List<String> wordsList;
        if (Objects.isNull(translation)) {
            throw new Exception("Translation was not exist.");
        }
        wordsList = dictionaryStorage.getWords(translation);
        return wordsList;
    }

    @Override
    public String getTranslation(String word) throws Exception {
        String translation;
        if (Objects.isNull(word)) {
            throw new Exception("Word was not exist.");
        }
        translation = dictionaryStorage.getTranslation(word);
        return translation;
    }

    @Override
    public List<WordPackage> getAllWords() {
        return dictionaryStorage.getAllWords();
    }

    @Override
    public Integer getDictionarySize() {
        return dictionaryStorage.getDictionarySize();
    }
}
