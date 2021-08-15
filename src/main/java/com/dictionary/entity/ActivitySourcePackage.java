package com.dictionary.entity;

import com.dictionary.service.dictionary.DictionaryService;
import com.dictionary.service.quiz.QuizService;
import com.dictionary.status.ApplicationStatus;
import com.dictionary.userInterface.ConsoleIO;

public class ActivitySourcePackage {
    private final DictionaryService dictionaryService;
    private final QuizService quizService;
    private final ApplicationStatus applicationStatus;
    private final ConsoleIO consoleIO;

    public ActivitySourcePackage(final DictionaryService dictionaryService,
                                 final QuizService quizService,
                                 final ApplicationStatus applicationStatus,
                                 final ConsoleIO consoleIO){
        this.dictionaryService = dictionaryService;
        this.quizService = quizService;
        this.applicationStatus = applicationStatus;
        this.consoleIO = consoleIO;
    }

    public DictionaryService getDictionaryService(){
        return this.dictionaryService;
    }

    public QuizService getQuizService(){
        return this.quizService;
    }

    public ConsoleIO getConsoleIO() {
        return consoleIO;
    }

    public ApplicationStatus getApplicationStatus() {
        return applicationStatus;
    }
}
