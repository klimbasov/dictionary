package com.dictionary.commands;

import com.dictionary.entity.CommandPackage;
import com.dictionary.response.Response;

import java.util.function.Function;

public class CommandImpl implements Command {
    private final Function<CommandPackage, Response> function;
    private final CommandPackage commandPackage;

    public CommandImpl(final Function<CommandPackage, Response> function, final CommandPackage commandPackage){
        this.function = function;
        this.commandPackage = commandPackage;
    }

    @Override
    public Response execute() {
        return function.apply(commandPackage);
    }
}
