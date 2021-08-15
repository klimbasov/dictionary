package com.dictionary.controller;

import com.dictionary.entity.response.Response;
import com.dictionary.service.command.Command;
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
            try {
                userInterface.sendResponse(command.execute());
            } catch (Exception exception) {
                userInterface.sendResponse(new Response(exception.getMessage()));
            }

        }
    }
}
