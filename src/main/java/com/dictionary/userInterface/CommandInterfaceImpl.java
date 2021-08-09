package com.dictionary.userInterface;

import com.dictionary.commands.Command;
import com.dictionary.commands.CommandsContainer;
import com.dictionary.entity.CommandPackage;
import com.dictionary.response.Response;

import java.util.ArrayList;
import java.util.Scanner;

public class CommandInterfaceImpl implements CommandInterface {
    private final ConsoleIO consoleIO;
    private final CommandsContainer commandsContainer;
    private Scanner parser;

    public CommandInterfaceImpl(final CommandsContainer commandsContainer, final ConsoleIO consoleIO){
        this.commandsContainer = commandsContainer;
        this.consoleIO = consoleIO;
    }

    @Override
    public Command getCommand() {
        String consoleInputString;
        String commandName;
        ArrayList<String> parameters = new ArrayList<>();
        consoleInputString = consoleIO.getInputString();
        parser = new Scanner(consoleInputString);
        commandName = parser.next();
        while (parser.hasNext()){
            parameters.add(parser.next());
        }
        return commandsContainer.getCommand(commandName, new CommandPackage(parameters));
    }

    @Override
    public void sendResponse(final Response response) {
        consoleIO.sendToOutput(response.toString());
    }
}
