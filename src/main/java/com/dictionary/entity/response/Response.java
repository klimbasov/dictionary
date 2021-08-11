package com.dictionary.entity.response;

public class Response {
    private String message;

    public Response() {

    }

    public Response(final String message) {
        this.message = message;
    }

    public void setMessage(final String message) {
        this.message = message;
    }

    public String toString() {
        return message;
    }
}
