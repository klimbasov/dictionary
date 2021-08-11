package com.dictionary.tags.CommandParametersTags;

import java.util.ArrayList;

public enum FindCommandModeTag {
    WORD(Aliases.wordAliases),
    TRANSLATION(Aliases.translationAliases),
    INVALID(Aliases.invalidAliases);

    private final String[] aliases;

    FindCommandModeTag(final String[] aliases) {
        this.aliases = aliases;
    }

    public String[] getAliases() {
        return aliases.clone();
    }
}

class Aliases {
    public static final String[] wordAliases = new String[]{"WORD", "word", "Word"};
    public static final String[] translationAliases = new String[]{"TRANSLATION", "translation", "Translation"};
    public static final String[] invalidAliases = new String[]{};
}


