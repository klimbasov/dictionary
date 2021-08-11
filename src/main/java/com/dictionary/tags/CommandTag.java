package com.dictionary.tags;

public enum CommandTag {
    ADD(2, Aliases.addAliases),
    FIND(2, Aliases.findAliases),
    SHOW_ALL(0, Aliases.showAllAliases),
    CLOSE(0, Aliases.closeAliases),
    HELP(0, Aliases.helpAliases),
    QUIZ(1, Aliases.quizAliases),
    TEST(0, Aliases.testAliases),
    INVALID(0, Aliases.invalidAliases);

    private final Integer parameterQuantity;
    private final String[] aliases;

    CommandTag(final Integer parameterQuantity, String[] aliases) {
        this.parameterQuantity = parameterQuantity;
        this.aliases = aliases;
    }

    public Integer getParameterQuantity() {
        return parameterQuantity;
    }

    public String[] getAliases() {
        return aliases;
    }
}

class Aliases {
    public static final String[] addAliases = new String[]{"ADD", "add", "Add"};
    public static final String[] findAliases = new String[]{"FIND", "find", "Find"};
    public static final String[] showAllAliases = new String[]{"SHOW_ALL", "showAll", "show_all"};
    public static final String[] closeAliases = new String[]{"CLOSE", "close", "Close"};
    public static final String[] helpAliases = new String[]{"HELP", "help", "Help"};
    public static final String[] quizAliases = new String[]{"QUIZ", "quiz", "Quiz"};
    public static final String[] testAliases = new String[]{"TEST", "test", "test"};
    public static final String[] invalidAliases = new String[]{};

}
