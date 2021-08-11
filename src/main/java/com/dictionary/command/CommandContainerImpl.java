package com.dictionary.command;

import com.dictionary.entity.CommandPackage;
import com.dictionary.entity.response.Response;
import com.dictionary.tags.CommandTag;

import java.util.HashMap;
import java.util.Objects;
import java.util.function.Function;

public class CommandContainerImpl implements CommandsContainer {
    private final HashMap<String, Function<CommandPackage, Response>> stringCommandHashMap;
    private final CommandActivity commandActivity;

    public CommandContainerImpl(final CommandActivity commandActivity) {
        this.stringCommandHashMap = new HashMap<>();
        this.commandActivity = commandActivity;
        this.createStringCommandHashMap();
    }

    @Override
    public Command getCommand(final String commandName, final CommandPackage commandPackage) {
        CommandImpl command;
        Function<CommandPackage, Response> activity = stringCommandHashMap.get(commandName);
        if (Objects.isNull(activity)) {
            command = new CommandImpl(commandActivity::invalidActivity, commandPackage);
        } else {
            command = new CommandImpl(activity, commandPackage);
        }
        return command;
    }

    private void createStringCommandHashMap() {
        for (final String alias : CommandTag.ADD.getAliases()) {
            stringCommandHashMap.put(alias, commandActivity::addActivity);
        }
        for (final String alias : CommandTag.FIND.getAliases()) {
            stringCommandHashMap.put(alias, commandActivity::findActivity);
        }
        for (final String alias : CommandTag.SHOW_ALL.getAliases()) {
            stringCommandHashMap.put(alias, commandActivity::showAllActivity);
        }
        for (final String alias : CommandTag.HELP.getAliases()) {
            stringCommandHashMap.put(alias, commandActivity::helpActivity);
        }
        for (final String alias : CommandTag.CLOSE.getAliases()) {
            stringCommandHashMap.put(alias, commandActivity::closeActivity);
        }
        for (final String alias : CommandTag.QUIZ.getAliases()) {
            stringCommandHashMap.put(alias, commandActivity::quizActivity);
        }
    }
}