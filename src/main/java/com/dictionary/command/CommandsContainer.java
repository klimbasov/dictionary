package com.dictionary.command;

import com.dictionary.entity.CommandPackage;

public interface CommandsContainer {
    Command getCommand(final String commandName, final CommandPackage commandPackage);
}
