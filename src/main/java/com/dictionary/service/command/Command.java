package com.dictionary.service.command;

import com.dictionary.entity.response.Response;

public interface Command {
    Response execute() throws Exception;
}
