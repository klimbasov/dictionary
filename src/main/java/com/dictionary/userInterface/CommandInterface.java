package com.dictionary.userInterface;

import com.dictionary.commands.Command;
import com.dictionary.response.Response;

public interface CommandInterface {

    Command getCommand();

    void sendResponse(final Response response);
}
