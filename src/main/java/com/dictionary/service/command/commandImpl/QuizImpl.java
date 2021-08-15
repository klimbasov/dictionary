package com.dictionary.service.command.commandImpl;

import com.dictionary.entity.ActivitySourcePackage;
import com.dictionary.entity.CommandPackage;
import com.dictionary.entity.WordPackage;
import com.dictionary.entity.response.Response;
import com.dictionary.service.command.AbstractCommand;
import com.dictionary.service.command.Command;
import com.dictionary.tags.CommandTag;
import com.dictionary.userInterface.ConsoleIO;

import java.util.ArrayList;
import java.util.Objects;

public class QuizImpl extends AbstractCommand implements Command {
    private int quizLength;
    ArrayList<WordPackage> wordPackages;

    public QuizImpl(final CommandPackage commandPackage, final ActivitySourcePackage activitySourcePackage) {
        super(commandPackage, activitySourcePackage);
        quizLength = 0;
        wordPackages = null;
    }

    @Override
    public Response execute() throws Exception {
        validate();
        ConsoleIO consoleIO = activitySourcePackage.getConsoleIO();
        int guesses = 0;
        consoleIO.sendToOutput("Quiz started. Questions quantity is " + quizLength);
        for (int progress = 0; progress < quizLength; progress++) {
            consoleIO.sendToOutput("The word " + wordPackages.get(progress).getWord() + " translates as:");
            if (wordPackages.get(progress).getTranslation().equals(consoleIO.getInputString())) {
                ++guesses;
            }
        }
        return new Response(guesses + " of " + quizLength + " were guess.");
    }

    protected final void validate() throws Exception {
        if (commandPackage.getParameters().size() != CommandTag.QUIZ.getParameterQuantity()) {
            throw new Exception("Invalid request parameters were set. Operation denied.");
        }
        quizLength = Integer.parseInt(commandPackage.getParameters().get(0));
        if (quizLength <= 0) {
            throw new Exception("Invalid quiz size. Operation denied.");
        }
        wordPackages = activitySourcePackage.getQuizService().getQuizPackage(quizLength);
        if (Objects.isNull(wordPackages)) {
            throw new Exception("Size of dictionary is smaller than quiz parameter required. Operation denied.");
        }
    }
}

