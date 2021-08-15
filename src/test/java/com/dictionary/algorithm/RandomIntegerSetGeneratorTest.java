package com.dictionary.algorithm;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class RandomIntegerSetGeneratorTest {
    int[] sizesArray = new int[]{2, 0, -1, 10000000, RandomIntegerSetGenerator.highestSizeValue};
    Borders[] bordersArray = new Borders[]{new Borders(1,10),
            new Borders(1,2),
            new Borders(1,1),
            new Borders(3,1),
            new Borders(10000000, 1000000000)};

    @Test
    void getIntegerSetTest(){
        for (int size : sizesArray){
            for(Borders borders : bordersArray){
                ArrayList<Integer> resultList;
                assertDoesNotThrow(() -> {RandomIntegerSetGenerator.getIntegerSet(borders.lowBorder, borders.highBorder, size);}, "\nsize: " +
                        size +
                        "\nlow border: " +
                        borders.lowBorder +
                        "\nhigh border: " +
                        borders.highBorder +
                        "\n");
                assertNotNull(RandomIntegerSetGenerator.getIntegerSet(borders.lowBorder, borders.highBorder, size), "\nsize: " +
                        size +
                        "\nlow border: " +
                        borders.lowBorder +
                        "\nhigh border: " +
                        borders.highBorder +
                        "\n");
            }
        }
    }

}

class Borders{
    int lowBorder;
    int highBorder;

    Borders(int lowBorder, int highBorder){
        this.lowBorder = lowBorder;
        this.highBorder = highBorder;
    }
}