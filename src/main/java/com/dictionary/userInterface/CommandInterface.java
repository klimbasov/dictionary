package com.dictionary.userInterface;

import com.dictionary.entity.response.Response;
import com.dictionary.service.command.Command;

public interface CommandInterface {

    Command getCommand();

    void sendResponse(final Response response);
}
