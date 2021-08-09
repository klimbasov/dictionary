package com.dictionary.algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

public class RandomIntegerSetGenerator {
    public static ArrayList<Integer> getIntegerSet(Integer lowBorder, Integer highBorder, Integer setSize){
        ArrayList<Integer> resultSet=null;
        if(setSize <= (highBorder - lowBorder)){
            resultSet = new ArrayList<>();
            int[] availableIntegers = new int[highBorder - lowBorder];
            for (int counter = 0; counter < setSize; counter++){
                availableIntegers[counter]= counter + lowBorder;
            }
            Collections.shuffle(Arrays.asList(availableIntegers));
            for(int counter = 0; counter < setSize; counter++){
                resultSet.add(availableIntegers[counter]);
            }
        }
        return  resultSet;
    }
}
