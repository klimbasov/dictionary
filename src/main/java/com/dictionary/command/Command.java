package com.dictionary.command;

import com.dictionary.entity.response.Response;

public interface Command {
    Response execute();
}
