package com.dictionary.service.command.commandImpl;

import com.dictionary.entity.ActivitySourcePackage;
import com.dictionary.entity.CommandPackage;
import com.dictionary.entity.response.Response;
import com.dictionary.service.command.AbstractCommand;
import com.dictionary.service.command.Command;

public class InvalidImpl extends AbstractCommand implements Command {

    public InvalidImpl(final CommandPackage commandPackage, final ActivitySourcePackage activitySourcePackage) {
        super(commandPackage, activitySourcePackage);
    }

    @Override
    public Response execute() throws Exception {
        validate();
        return new Response();
    }

    protected final void validate() throws Exception {
        throw new Exception("Invalid command called. Operation denied.");
    }
}
