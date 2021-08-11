package com.dictionary;

import com.dictionary.command.CommandActivity;
import com.dictionary.command.CommandContainerImpl;
import com.dictionary.command.CommandsContainer;
import com.dictionary.controller.Controller;
import com.dictionary.dao.Dictionary;
import com.dictionary.dao.DictionaryStorage;
import com.dictionary.service.dictionary.DictionaryService;
import com.dictionary.service.dictionary.DictionaryServiceImpl;
import com.dictionary.service.quiz.QuizService;
import com.dictionary.service.quiz.QuizServiceImpl;
import com.dictionary.status.ApplicationStatus;
import com.dictionary.userInterface.CommandInterface;
import com.dictionary.userInterface.CommandInterfaceImpl;
import com.dictionary.userInterface.ConsoleIO;
import com.dictionary.userInterface.ConsoleIOImpl;

public class Main {
    public static void main(String[] args) {
        ApplicationStatus applicationStatus = new ApplicationStatus();
        DictionaryStorage dictionaryStorage = new Dictionary();
        DictionaryService dictionaryService = new DictionaryServiceImpl(dictionaryStorage);
        QuizService quizService = new QuizServiceImpl(dictionaryStorage);
        ConsoleIO consoleIO = new ConsoleIOImpl();
        CommandActivity commandActivity = new CommandActivity(dictionaryService, quizService, applicationStatus, consoleIO);
        CommandsContainer commandsContainer = new CommandContainerImpl(commandActivity);
        CommandInterface userInterface = new CommandInterfaceImpl(commandsContainer, consoleIO);
        Controller controller = new Controller(userInterface, applicationStatus);

        controller.run();
    }
}


/*

 */
