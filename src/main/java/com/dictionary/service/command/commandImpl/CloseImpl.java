package com.dictionary.service.command.commandImpl;

import com.dictionary.entity.ActivitySourcePackage;
import com.dictionary.entity.CommandPackage;
import com.dictionary.entity.response.Response;
import com.dictionary.service.command.AbstractCommand;
import com.dictionary.service.command.Command;
import com.dictionary.tags.CommandTag;

public class CloseImpl extends AbstractCommand implements Command {

    public CloseImpl(final CommandPackage commandPackage, final ActivitySourcePackage activitySourcePackage) {
        super(commandPackage, activitySourcePackage);
    }

    @Override
    public Response execute() throws Exception {
        validate();
        activitySourcePackage.getApplicationStatus().setFalseIsRunning();
        return new Response("Execution finished.");
    }

    protected final void validate() throws Exception {
        if (commandPackage.getParameters().size() != CommandTag.CLOSE.getParameterQuantity()) {
            throw new Exception("Invalid request parameters were set. Operation denied.");
        }
    }
}

