package com.dictionary.service.dictionary;

import com.dictionary.dao.DictionaryStorage;
import com.dictionary.entity.WordPackage;

import java.util.List;

public class DictionaryServiceImpl implements DictionaryService{
    private final DictionaryStorage dictionaryStorage;

    public DictionaryServiceImpl(final DictionaryStorage dictionaryStorage){
        this.dictionaryStorage = dictionaryStorage;
    }

    @Override
    public void addWord(String word, String translation)throws Exception {
        if(word == null || translation == null){
            throw new Exception("Either Word or translation were not exist.");
        }
        dictionaryStorage.addDictionaryRecord(new WordPackage(word, translation));
    }

    @Override
    public List<String> getWord(String translation)throws Exception {
        List<String> wordsList;
        if(translation == null){
            throw new Exception("Translation was not exist.");
        }
        wordsList = dictionaryStorage.getWords(translation);
        return wordsList;
    }

    @Override
    public String getTranslation(String word)throws Exception {
        String translation;
        if(word == null){
            throw new Exception("Word was not exist.");
        }
        translation = dictionaryStorage.getTranslation(word);
        return translation;
    }

    @Override
    public List<WordPackage> getAllWords() throws Exception{
        return dictionaryStorage.getAllWords();
    }

    @Override
    public Integer getDictionarySize() throws Exception{
        return dictionaryStorage.getDictionarySize();
    }
}
