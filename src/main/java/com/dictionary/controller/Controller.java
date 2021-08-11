package com.dictionary.controller;

import com.dictionary.command.Command;
import com.dictionary.status.ApplicationStatus;
import com.dictionary.userInterface.CommandInterface;

public class Controller {
    private final CommandInterface userInterface;
    private final ApplicationStatus applicationStatus;

    public Controller(final CommandInterface userInterface, final ApplicationStatus applicationStatus) {
        this.userInterface = userInterface;
        this.applicationStatus = applicationStatus;
    }

    public void run() {
        while (applicationStatus.IsRunning()) {
            Command command = userInterface.getCommand();
            userInterface.sendResponse(command.execute());
        }
    }
}
