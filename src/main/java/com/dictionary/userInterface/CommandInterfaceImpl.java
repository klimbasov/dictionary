package com.dictionary.userInterface;

import com.dictionary.command.Command;
import com.dictionary.command.CommandsContainer;
import com.dictionary.entity.CommandPackage;
import com.dictionary.entity.response.Response;

import java.util.*;

public class CommandInterfaceImpl implements CommandInterface {
    private final ConsoleIO consoleIO;
    private final CommandsContainer commandsContainer;

    public CommandInterfaceImpl(final CommandsContainer commandsContainer, final ConsoleIO consoleIO) {
        this.commandsContainer = commandsContainer;
        this.consoleIO = consoleIO;
    }

    @Override
    public Command getCommand() {
        String[] inputArguments;
        ArrayList<String> parameters = new ArrayList<>();
        inputArguments = consoleIO.getInputString().split("\\s+");
        Collections.addAll(parameters, Arrays.copyOfRange(inputArguments, 1, inputArguments.length));
        return commandsContainer.getCommand(inputArguments[0], new CommandPackage(parameters));
    }

    @Override
    public void sendResponse(final Response response) {
        consoleIO.sendToOutput(response.toString());
    }
}
