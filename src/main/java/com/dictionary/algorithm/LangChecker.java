package com.dictionary.algorithm;

public class LangChecker {
    public static Boolean isEngWord(String word){
        for(char symbol : word.toCharArray()){
            if(symbol<'a'||symbol > 'z'){
                return false;
            }
        }
        return true;
    }

    public static Boolean isRusWord(String word){
        for(char symbol : word.toCharArray()){
            if(symbol<'а'||symbol > 'я'){
                return false;
            }
        }
        return true;
    }
}
