package com.dictionary.algorithm;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LangCheckerTest {
    final String[] TestArray = new String[]{"sad", "", "морковь", "0", "groo t", "дре вень"};
    final boolean[] engAnswersExpected = new boolean[]{true, false, false, false, false, false};
    final boolean[] rusAnswersExpected = new boolean[]{false, false, true, false, false, false};


    @Test
    void isRusWordTest() {
        for (int counter = 0; counter < TestArray.length; counter++) {
            assertEquals(LangChecker.isRusWord(TestArray[counter]), rusAnswersExpected[counter], "ASSETR: in LengChecker.isRusWord: result on \"" + TestArray[counter] + "\"");
        }
    }

    @Test
    void isEngWordTest() {
        for (int counter = 0; counter < TestArray.length; counter++) {
            assertEquals(LangChecker.isEngWord(TestArray[counter]), engAnswersExpected[counter], "ASSETR: in LengChecker.isEngWord: result on \"" + TestArray[counter] + "\"");
        }
    }
}