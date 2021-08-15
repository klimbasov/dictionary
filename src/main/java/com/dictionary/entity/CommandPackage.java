package com.dictionary.entity;

import java.util.ArrayList;

public class CommandPackage {
    private final ArrayList<String> parameters;

    public CommandPackage(final ArrayList<String> parameters) {
        this.parameters = parameters;
    }

    public ArrayList<String> getParameters() {
        return parameters;
    }
}
