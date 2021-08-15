package com.dictionary.service.command.commandImpl;

import com.dictionary.algorithm.LangChecker;
import com.dictionary.entity.ActivitySourcePackage;
import com.dictionary.entity.CommandPackage;
import com.dictionary.entity.response.Response;
import com.dictionary.service.command.AbstractCommand;
import com.dictionary.service.command.Command;
import com.dictionary.tags.CommandTag;

import java.util.ArrayList;

public class AddImpl extends AbstractCommand implements Command {

    public AddImpl(final CommandPackage commandPackage, final ActivitySourcePackage activitySourcePackage) {
        super(commandPackage, activitySourcePackage);
    }

    @Override
    public Response execute() throws Exception {
        validate();
        ArrayList<String> parameters = commandPackage.getParameters();
        activitySourcePackage.getDictionaryService().addWord(parameters.get(0), parameters.get(1));
        return new Response("New word pair was created.");
    }

    protected final void validate() throws Exception {
        ArrayList<String> parameters = commandPackage.getParameters();
        if (parameters.size() != CommandTag.ADD.getParameterQuantity()) {
            throw new Exception("Invalid request parameters were set. Operation denied.");
        }
        if (!LangChecker.isEngWord(parameters.get(0)) || !LangChecker.isRusWord(parameters.get(1))) {
            throw new Exception("First word should be Eng, second one - Rus");
        }
    }
}
