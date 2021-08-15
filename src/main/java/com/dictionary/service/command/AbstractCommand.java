package com.dictionary.service.command;

import com.dictionary.entity.ActivitySourcePackage;
import com.dictionary.entity.CommandPackage;

public abstract class AbstractCommand {
    protected final CommandPackage commandPackage;
    protected final ActivitySourcePackage activitySourcePackage;

    protected AbstractCommand(final CommandPackage commandPackage, final ActivitySourcePackage activitySourcePackage) {
        this.commandPackage = commandPackage;
        this.activitySourcePackage = activitySourcePackage;
    }

    //protected abstract void validate() throws Exception;

}
