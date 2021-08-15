package com.dictionary.service.command.commandImpl;

import com.dictionary.entity.ActivitySourcePackage;
import com.dictionary.entity.CommandPackage;
import com.dictionary.entity.WordPackage;
import com.dictionary.entity.response.Response;
import com.dictionary.service.command.AbstractCommand;
import com.dictionary.service.command.Command;
import com.dictionary.tags.CommandTag;

import java.util.ArrayList;
import java.util.List;

public class ShowAllImpl extends AbstractCommand implements Command {

    public ShowAllImpl(final CommandPackage commandPackage, final ActivitySourcePackage activitySourcePackage) {
        super(commandPackage, activitySourcePackage);
    }


    @Override
    public Response execute() throws Exception {
        validate();
        List<WordPackage> wordPackageArrayList = activitySourcePackage.getDictionaryService().getAllWords();
        return wordPackageArrayList.isEmpty() ? new Response("Dictionary is empty") : new Response(wordPackageArrayList.toString());
    }

    protected final void validate() throws Exception {
        ArrayList<String> parameters = commandPackage.getParameters();
        if (parameters.size() != CommandTag.SHOW_ALL.getParameterQuantity()) {
            throw new Exception("Invalid request parameters were set. Operation denied.");
        }
    }
}
