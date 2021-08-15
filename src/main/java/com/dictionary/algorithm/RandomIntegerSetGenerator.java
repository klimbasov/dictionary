package com.dictionary.algorithm;

import java.util.ArrayList;
import java.util.Collections;

public class RandomIntegerSetGenerator {
    static Integer lowestBorderValue = -9999;
    static Integer highestBorderValue = 9999;
    static Integer highestSizeValue = 9999;

    public static ArrayList<Integer> getIntegerSet(Integer lowBorder, Integer highBorder, Integer setSize) {
        ArrayList<Integer> resultSet = new ArrayList<>();
        if (isValid(lowBorder, highBorder, setSize)) {
            int[] availableIntegers = new int[highBorder - lowBorder + 1];
            for (int counter = 0; counter < availableIntegers.length; counter++) {
                availableIntegers[counter] = counter + lowBorder;
            }
            Collections.shuffle(Collections.singletonList(availableIntegers));
            for (int counter = 0; counter < setSize; counter++) {
                resultSet.add(availableIntegers[counter]);
            }
        }
        return resultSet;
    }

    private static boolean isValid(Integer lowBorder, Integer highBorder, Integer setSize){
        return (setSize <= (highBorder - lowBorder + 1)) &&
                (setSize > 0) &&
                (highBorder > lowBorder) &&
                (lowBorder >= lowestBorderValue) &&
                (lowBorder <= highestBorderValue) &&
                (highBorder <= highestBorderValue) &&
                (setSize <= highestSizeValue);

    }
}
