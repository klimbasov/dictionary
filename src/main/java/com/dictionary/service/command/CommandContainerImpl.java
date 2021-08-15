package com.dictionary.service.command;

import com.dictionary.entity.ActivitySourcePackage;
import com.dictionary.entity.CommandPackage;
import com.dictionary.service.command.commandImpl.*;
import com.dictionary.tags.CommandTag;

import java.util.HashMap;
import java.util.Objects;

public class CommandContainerImpl implements CommandsContainer {
    private final HashMap<String, CommandTag> stringCommandTagHashMap;
    private final ActivitySourcePackage activitySourcePackage;

    public CommandContainerImpl(final ActivitySourcePackage activitySourcePackage) {
        this.activitySourcePackage = activitySourcePackage;
        this.stringCommandTagHashMap = new HashMap<>();
        for (CommandTag commandTag : CommandTag.values()) {
            for (String alias : commandTag.getAliases()) {
                this.stringCommandTagHashMap.put(alias, commandTag);
            }
        }
    }

    @Override
    public Command getCommand(final String commandName, final CommandPackage commandPackage) {
        Command command;
        CommandTag commandTag = getCommandTag(commandName);
        switch (commandTag) {
            case ADD:
                command = new AddImpl(commandPackage, activitySourcePackage);
                break;
            case FIND:
                command = new FindImpl(commandPackage, activitySourcePackage);
                break;
            case SHOW_ALL:
                command = new ShowAllImpl(commandPackage, activitySourcePackage);
                break;
            case HELP:
                command = new HelpImpl(commandPackage, activitySourcePackage);
                break;
            case CLOSE:
                command = new CloseImpl(commandPackage, activitySourcePackage);
                break;
            case QUIZ:
                command = new QuizImpl(commandPackage, activitySourcePackage);
                break;
            case SHOW_SIZE:
                command = new ShowSizeImpl(commandPackage, activitySourcePackage);
                break;
            default:
                command = new InvalidImpl(commandPackage, activitySourcePackage);
                break;
        }
        return command;
    }

    private CommandTag getCommandTag(final String commandName) {
        CommandTag commandTag = stringCommandTagHashMap.get(commandName);
        return Objects.nonNull(commandTag) ? commandTag : CommandTag.INVALID;
    }
}