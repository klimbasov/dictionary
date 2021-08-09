package com.dictionary.commands;

import com.dictionary.algorithm.LangChecker;
import com.dictionary.entity.CommandPackage;
import com.dictionary.entity.WordPackage;
import com.dictionary.response.Response;
import com.dictionary.response.ResponseImpl;
import com.dictionary.service.dictionary.DictionaryService;
import com.dictionary.service.quiz.QuizService;
import com.dictionary.status.ApplicationStatus;
import com.dictionary.tags.CommandParametersTags.FindCommandModeTag;
import com.dictionary.tags.CommandTag;
import com.dictionary.userInterface.ConsoleIO;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class CommandActivity {
    private final DictionaryService dictionaryService;
    private final QuizService quizService;
    private final ApplicationStatus applicationStatus;
    private final ConsoleIO consoleIO;
    public final Function<CommandPackage, Response> addActivity;
    public final Function<CommandPackage, Response> findActivity;
    public final Function<CommandPackage, Response> showAllActivity;
    public final Function<CommandPackage, Response> closeActivity;
    public final Function<CommandPackage, Response> helpActivity;
    public final Function<CommandPackage, Response> quizActivity;
    public final Function<CommandPackage, Response> invalidActivity;

    public CommandActivity(final DictionaryService dictionaryService, final QuizService quizService, final ApplicationStatus applicationStatus, final ConsoleIO consoleIO) {
        this.dictionaryService = dictionaryService;
        this.quizService = quizService;
        this.applicationStatus = applicationStatus;
        this.consoleIO = consoleIO;
        this.addActivity = commandPackage -> {
            ResponseImpl response = new ResponseImpl();
            ArrayList<String> parameters = commandPackage.getParameters();
            if (parameters.size() == CommandTag.ADD.getParameterQuantity()) {
                if(!LangChecker.isEngWord(parameters.get(0))|| !LangChecker.isRusWord(parameters.get(1))){
                    response.setMessage("First word should be Eng, second one - Rus");
                }
                else {
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
        };
        this.findActivity = commandPackage -> {
            ResponseImpl response = new ResponseImpl();
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
                        if(words.isEmpty()){
                            response.setMessage("there is no such word in dictionary");
                        }else{
                            response.setMessage(words.toString());
                        }
                        break;
                    case TRANSLATION:
                        String translation = new String();
                        try {
                            translation = dictionaryService.getTranslation(parameters.get(1));
                        } catch (Exception exception) {
                            response.setMessage(exception.getMessage());
                        }
                        if (translation==null){
                            response.setMessage("there is no such word in dictionary");
                        }else{
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
        };
        this.showAllActivity = commandPackage -> {
            ResponseImpl response = new ResponseImpl();
            ArrayList<String> parameters = commandPackage.getParameters();
            List<WordPackage> wordPackageArrayList = new ArrayList<>();

            if (parameters.size() == CommandTag.SHOW_ALL.getParameterQuantity()) {
                try {
                    wordPackageArrayList = dictionaryService.getAllWords();
                } catch (Exception exception) {
                    response.setMessage(exception.getMessage());
                }
                if (wordPackageArrayList.isEmpty()){
                    response.setMessage("Dictionary is empty");
                }else{
                    response.setMessage(wordPackageArrayList.toString());
                }
            } else {
                response.setMessage("Invalid request parameters were set. Operation denied.");
            }
            return response;
        };
        this.closeActivity = commandPackage -> {
            ResponseImpl response = new ResponseImpl();
            ArrayList<String> parameters = commandPackage.getParameters();
            List<WordPackage> wordPackageArrayList = new ArrayList<>();

            if (parameters.size() == CommandTag.CLOSE.getParameterQuantity()) {
                this.applicationStatus.setEndOfExecution();
                response.setMessage("Execution finished.");
            } else {
                response.setMessage("Invalid request parameters were set. Operation denied.");
            }
            return response;
        };
        this.helpActivity = commandPackage -> {
            ResponseImpl response = new ResponseImpl();
            ArrayList<String> parameters = commandPackage.getParameters();
            List<WordPackage> wordPackageArrayList = new ArrayList<>();

            if (parameters.size() == CommandTag.CLOSE.getParameterQuantity()) {
                response.setMessage("Help yourself.");
            } else {
                response.setMessage("Invalid request parameters were set. Operation denied.");
            }
            return response;
        };
        this.quizActivity = commandPackage -> {
            ResponseImpl response = new ResponseImpl();
            ArrayList<String> parameters = commandPackage.getParameters();
            ArrayList<WordPackage> wordPackages;
            Integer quizLength,
                    guesses = 0;

            if (parameters.size() == CommandTag.QUIZ.getParameterQuantity()) {
            try {
                quizLength = Integer.parseInt(parameters.get(0));
                wordPackages = quizService.getQuizPackage(quizLength);
                if(wordPackages != null){
                    consoleIO.sendToOutput("Quiz started. Questions quantity is " + quizLength);
                    for(Integer progress = 0; progress < quizLength; progress++){
                        consoleIO.sendToOutput("The word " + wordPackages.get(progress).getWord() + " translates as:");
                        if(wordPackages.get(progress).getTranslation().equals(consoleIO.getInputString())){
                            ++guesses;
                        }
                }
                    response.setMessage(guesses + " of " + quizLength + " were guess.");
                }else {
                    response.setMessage("Size of dictionary is smaller than quiz parameter required. Operation denied.");
                }


            }catch (NumberFormatException exception){
                response.setMessage("Invalid request parameters were set. Operation denied.");
            }
            } else {
                response.setMessage("Invalid request parameters were set. Operation denied.");
            }
            return response;
        };
        this.invalidActivity = commandPackage -> {
            ResponseImpl response = new ResponseImpl();
            response.setMessage("Invalid command called. Operation denied.");
            return response;
        };
    }
}
