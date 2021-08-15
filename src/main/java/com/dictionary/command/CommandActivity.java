package com.dictionary.command;

import com.dictionary.algorithm.LangChecker;
import com.dictionary.entity.ActivitySourcePackage;
import com.dictionary.entity.CommandPackage;
import com.dictionary.entity.WordPackage;
import com.dictionary.entity.response.Response;
import com.dictionary.service.dictionary.DictionaryService;
import com.dictionary.service.quiz.QuizService;
import com.dictionary.status.ApplicationStatus;
import com.dictionary.tags.CommandParametersTags.FindCommandModeTag;
import com.dictionary.tags.CommandTag;
import com.dictionary.userInterface.ConsoleIO;

import java.util.ArrayList;
import java.util.List;

public class CommandActivity {
    private final DictionaryService dictionaryService;
    private final QuizService quizService;
    private final ApplicationStatus applicationStatus;
    private final ConsoleIO consoleIO;

    public CommandActivity(final ActivitySourcePackage activitySourcePackage) {
        this.dictionaryService = activitySourcePackage.getDictionaryService();
        this.quizService = activitySourcePackage.getQuizService();
        this.applicationStatus = activitySourcePackage.getApplicationStatus();
        this.consoleIO = activitySourcePackage.getConsoleIO();
    }

    public Response addActivity(CommandPackage commandPackage) {
        Response response = new Response();
        ArrayList<String> parameters = commandPackage.getParameters();
        if (parameters.size() == CommandTag.ADD.getParameterQuantity()) {
            if (!LangChecker.isEngWord(parameters.get(0)) || !LangChecker.isRusWord(parameters.get(1))) {
                response.setMessage("First word should be Eng, second one - Rus");
            } else {
                try {
                    dictionaryService.addWord(parameters.get(0), parameters.get(1));
                } catch (Exception exception) {
                    response.setMessage(exception.getMessage());
                }
                response.setMessage("New word pair was created.");
            }
        } else {
            response.setMessage("Invalid request parameters were set. Operation denied.");
        }
        return response;
    }

    public Response findActivity(CommandPackage commandPackage){
        Response response = new Response();
        ArrayList<String> parameters = commandPackage.getParameters();
        FindCommandModeTag modeTag = FindCommandModeTag.INVALID;
        if (parameters.size() == CommandTag.FIND.getParameterQuantity()) {
            for (FindCommandModeTag findCommandModeTag : FindCommandModeTag.values()) {
                for (String alias : findCommandModeTag.getAliases()) {
                    if (parameters.get(0).equals(alias)) {
                        modeTag = findCommandModeTag;
                    }
                }
            }
            switch (modeTag) {
                case WORD:
                    List<String> words = new ArrayList<>();
                    try {
                        words = dictionaryService.getWord(parameters.get(1));
                    } catch (Exception exception) {
                        response.setMessage(exception.getMessage());
                    }
                    if (words.isEmpty()) {
                        response.setMessage("there is no such word in dictionary");
                    } else {
                        response.setMessage(words.toString());
                    }
                    break;
                case TRANSLATION:
                    String translation = "";
                    try {
                        translation = dictionaryService.getTranslation(parameters.get(1));
                    } catch (Exception exception) {
                        response.setMessage(exception.getMessage());
                    }
                    if (translation == null) {
                        response.setMessage("there is no such word in dictionary");
                    } else {
                        response.setMessage(translation);
                    }
                    break;
                default:
                    response.setMessage("Invalid command mode.");
            }

        } else {
            response.setMessage("Invalid command parameters set.");
        }
        return response;
    }

    public Response showAllActivity(CommandPackage commandPackage) {
        Response response = new Response();
        ArrayList<String> parameters = commandPackage.getParameters();
        List<WordPackage> wordPackageArrayList = new ArrayList<>();

        if (parameters.size() == CommandTag.SHOW_ALL.getParameterQuantity()) {
            try {
                wordPackageArrayList = dictionaryService.getAllWords();
            } catch (Exception exception) {
                response.setMessage(exception.getMessage());
            }
            if (wordPackageArrayList.isEmpty()) {
                response.setMessage("Dictionary is empty");
            } else {
                response.setMessage(wordPackageArrayList.toString());
            }
        } else {
            response.setMessage("Invalid request parameters were set. Operation denied.");
        }
        return response;
    }

    public Response closeActivity(CommandPackage commandPackage) {
        Response response = new Response();
        ArrayList<String> parameters = commandPackage.getParameters();
        List<WordPackage> wordPackageArrayList = new ArrayList<>();

        if (parameters.size() == CommandTag.CLOSE.getParameterQuantity()) {
            this.applicationStatus.setFalseIsRunning();
            response.setMessage("Execution finished.");
        } else {
            response.setMessage("Invalid request parameters were set. Operation denied.");
        }
        return response;
    }

    public Response helpActivity(CommandPackage commandPackage) {
        Response response = new Response();
        ArrayList<String> parameters = commandPackage.getParameters();
        List<WordPackage> wordPackageArrayList = new ArrayList<>();

        if (parameters.size() == CommandTag.CLOSE.getParameterQuantity()) {
            response.setMessage("Commands are\n" +
                    "add word translation - add word with translation to dictionary.\n" +
                    "showall - show all dictionary content\n" +
                    "find mode smth - find words by translation(mode == word) or translation by word(mode == translation)\n" +
                    "close - finish application execution\n" +
                    "quiz length - if dictionary size allows, start the quiz\n");
        } else {
            response.setMessage("Invalid request parameters were set. Operation denied.");
        }
        return response;
    }

    public Response quizActivity(CommandPackage commandPackage) {
        Response response = new Response();
        ArrayList<String> parameters = commandPackage.getParameters();
        ArrayList<WordPackage> wordPackages;
        int quizLength,
                guesses = 0;

        if (parameters.size() == CommandTag.QUIZ.getParameterQuantity()) {
            try {
                quizLength = Integer.parseInt(parameters.get(0));
                if (quizLength <= 0) {
                    response.setMessage("Invalid quiz size. Operation denied.");
                }
                wordPackages = quizService.getQuizPackage(quizLength);
                if (wordPackages != null) {
                    consoleIO.sendToOutput("Quiz started. Questions quantity is " + quizLength);
                    for (int progress = 0; progress < quizLength; progress++) {
                        consoleIO.sendToOutput("The word " + wordPackages.get(progress).getWord() + " translates as:");
                        if (wordPackages.get(progress).getTranslation().equals(consoleIO.getInputString())) {
                            ++guesses;
                        }
                    }
                    response.setMessage(guesses + " of " + quizLength + " were guess.");
                } else {
                    response.setMessage("Size of dictionary is smaller than quiz parameter required. Operation denied.");
                }


            } catch (NumberFormatException exception) {
                response.setMessage("Invalid request parameters were set. Operation denied.");
            }
        } else {
            response.setMessage("Invalid request parameters were set. Operation denied.");
        }
        return response;
    }

    public Response invalidActivity(CommandPackage commandPackage) {
        Response response = new Response();
        response.setMessage("Invalid command called. Operation denied.");
        return response;
    }
}
