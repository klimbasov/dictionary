package com.dictionary.service.command.commandImpl;

import com.dictionary.entity.ActivitySourcePackage;
import com.dictionary.entity.CommandPackage;
import com.dictionary.entity.response.Response;
import com.dictionary.service.command.AbstractCommand;
import com.dictionary.service.command.Command;
import com.dictionary.tags.CommandTag;

public class HelpImpl extends AbstractCommand implements Command {

    public HelpImpl(final CommandPackage commandPackage, final ActivitySourcePackage activitySourcePackage) {
        super(commandPackage, activitySourcePackage);
    }

    @Override
    public Response execute() throws Exception {
        validate();
        return new Response("Commands are\n" +
                "add word translation - add word with translation to dictionary.\n" +
                "showAll - show all dictionary content\n" +
                "find mode word/translation - find words by translation(mode == word) or translation by word(mode == translation)\n" +
                "close - finish application execution\n" +
                "quiz length - if dictionary size allows, start the quiz\n");
    }

    protected final void validate() throws Exception {
        if (commandPackage.getParameters().size() != CommandTag.HELP.getParameterQuantity()) {
            throw new Exception("Invalid request parameters were set. Operation denied.");
        }
    }
}
