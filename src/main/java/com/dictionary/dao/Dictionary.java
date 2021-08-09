package com.dictionary.dao;

import com.dictionary.algorithm.RandomIntegerSetGenerator;
import com.dictionary.entity.WordPackage;

import java.util.*;

public class Dictionary implements DictionaryStorage{
    private final Map<String, String> words = new HashMap<>();

    public Dictionary(){

    }

    @Override
    public void addDictionaryRecord(WordPackage wordPackage) {
        words.put(wordPackage.getWord(), wordPackage.getTranslation());
    }

    @Override
    public String getTranslation(String word) {
        String translation = words.get(word);
        return translation;
    }

    @Override
    public ArrayList<String> getWords(String translation) {
        ArrayList<String> wordsList = new ArrayList<>();
        for (Map.Entry<String, String> entry : words.entrySet()){
            if(translation.equals(entry.getValue())){
                wordsList.add(entry.getKey());
            }
        }
        return wordsList;
    }

    @Override
    public ArrayList<WordPackage> getAllWords(){
        ArrayList<WordPackage> wordsList = new ArrayList<>();
        for (Map.Entry<String, String> entry : words.entrySet()){
            wordsList.add(new WordPackage(entry.getKey(), entry.getValue()));
        }
        return wordsList;
    }

    @Override
    public Integer getDictionarySize() {
        return words.size();
    }

    @Override
    public ArrayList<WordPackage> getSetOfRandomWords(Integer setSize) {
        ArrayList<WordPackage> set = null;
        ArrayList<Integer> indexes = RandomIntegerSetGenerator.getIntegerSet(0, words.size(), setSize);
        if(indexes != null) {
            set = new ArrayList<>();
            Map.Entry<String, String>[] mapArray = words.entrySet().toArray(new Map.Entry[0]);
            for(Integer index : indexes){
                set.add(new WordPackage(mapArray[index].getKey(),mapArray[index].getValue()));
            }
        }
        return set;
    }
}
