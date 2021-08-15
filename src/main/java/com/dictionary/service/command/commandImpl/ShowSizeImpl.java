package com.dictionary.service.command.commandImpl;

import com.dictionary.entity.ActivitySourcePackage;
import com.dictionary.entity.CommandPackage;
import com.dictionary.entity.response.Response;
import com.dictionary.service.command.AbstractCommand;
import com.dictionary.service.command.Command;
import com.dictionary.tags.CommandTag;

import java.util.ArrayList;

public class ShowSizeImpl extends AbstractCommand implements Command {

    public ShowSizeImpl(final CommandPackage commandPackage, final ActivitySourcePackage activitySourcePackage) {
        super(commandPackage, activitySourcePackage);
    }

    protected final void validate() throws Exception {
        ArrayList<String> parameters = commandPackage.getParameters();
        if (parameters.size() != CommandTag.SHOW_SIZE.getParameterQuantity()) {
            throw new Exception("Invalid request parameters were set. Operation denied.");
        }
    }

    @Override
    public Response execute() throws Exception {
        validate();
        Integer dictionarySize = activitySourcePackage.getDictionaryService().getDictionarySize();
        return new Response("Dictionary has " + dictionarySize + "words");
    }
}
