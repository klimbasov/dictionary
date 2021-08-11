package com.dictionary.userInterface;

import com.dictionary.command.Command;
import com.dictionary.entity.response.Response;

public interface CommandInterface {

    Command getCommand();

    void sendResponse(final Response response);
}
