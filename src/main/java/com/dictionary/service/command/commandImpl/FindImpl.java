package com.dictionary.service.command.commandImpl;

import com.dictionary.entity.ActivitySourcePackage;
import com.dictionary.entity.CommandPackage;
import com.dictionary.entity.response.Response;
import com.dictionary.service.command.AbstractCommand;
import com.dictionary.service.command.Command;
import com.dictionary.tags.CommandTag;
import com.dictionary.tags.commandParametersTags.FindCommandModeTag;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class FindImpl extends AbstractCommand implements Command {

    public FindImpl(final CommandPackage commandPackage, final ActivitySourcePackage activitySourcePackage) {
        super(commandPackage, activitySourcePackage);
    }

    @Override
    public Response execute() throws Exception {
        Response response = new Response();
        ArrayList<String> parameters = commandPackage.getParameters();
        validate();
        FindCommandModeTag modeTag = defineModeTag(parameters.get(0));
        switch (modeTag) {
            case WORD:
                List<String> words = activitySourcePackage.getDictionaryService().getWord(parameters.get(1));
                if (words.isEmpty()) {
                    response.setMessage("there is no such word in dictionary");
                } else {
                    response.setMessage(words.toString());
                }
                break;
            case TRANSLATION:
                String translation = activitySourcePackage.getDictionaryService().getTranslation(parameters.get(1));
                if (Objects.isNull(translation)) {
                    response.setMessage("there is no such word in dictionary");
                } else {
                    response.setMessage(translation);
                }
                break;
            default:
                response.setMessage("Invalid command mode.");
        }
        return response;
    }

    protected final void validate() throws Exception {
        if (commandPackage.getParameters().size() == CommandTag.FIND.getParameterQuantity()) {
            throw new Exception("Invalid command parameters set.");
        }

    }

    private FindCommandModeTag defineModeTag(String tagName) {
        FindCommandModeTag findTag = FindCommandModeTag.INVALID;
        for (FindCommandModeTag findCommandModeTag : FindCommandModeTag.values()) {
            for (String alias : findCommandModeTag.getAliases()) {
                if (tagName.equals(alias)) {
                    findTag = findCommandModeTag;
                    break;
                }
            }
        }
        return findTag;
    }
}
