package com.dictionary.algorithm;

import java.util.Objects;

public class LangChecker {
    public static boolean isEngWord(String word) {
        if(Objects.isNull(word)){
            return false;
        }
        if(word.length() == 0){
            return false;
        }
        for (char symbol : word.toCharArray()) {
            if (symbol < 'a' || symbol > 'z') {
                return false;
            }
        }
        return true;
    }

    public static boolean isRusWord(String word) {
        if(Objects.isNull(word)){
            return false;
        }
        if(word.length() == 0){
            return false;
        }
        for (char symbol : word.toCharArray()) {
            if (symbol < 'а' || symbol > 'я') {
                return false;
            }
        }
        return true;
    }
}
