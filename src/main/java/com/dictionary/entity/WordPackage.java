package com.dictionary.entity;

public class WordPackage {
    private final String word;
    private final String translation;

    public WordPackage(final String word, final String translation) {
        this.word = word;
        this.translation = translation;
    }

    public String getWord() {
        return word;
    }

    public String getTranslation() {
        return translation;
    }

    @Override
    public String toString() {
        return this.word + " - " + this.translation;
    }

}
