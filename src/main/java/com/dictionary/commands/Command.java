package com.dictionary.commands;

import com.dictionary.response.Response;

public interface Command {
    Response execute();
}
