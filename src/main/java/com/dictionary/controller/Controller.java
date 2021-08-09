package com.dictionary.controller;

import com.dictionary.commands.Command;
import com.dictionary.status.ApplicationStatus;
import com.dictionary.userInterface.CommandInterface;

public class Controller {
    private final CommandInterface userInterface;
    private final ApplicationStatus applicationStatus;

    public Controller(final CommandInterface userInterface, final ApplicationStatus applicationStatus){
        this.userInterface = userInterface;
        this.applicationStatus = applicationStatus;
    }

    public void run(){
        while (applicationStatus.isEndOfExecution()==false){
            Command command = userInterface.getCommand();
            userInterface.sendResponse(command.execute());
        }
    }
}
