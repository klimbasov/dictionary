package com.dictionary.response;

public class ResponseImpl implements Response{
    private String message;

    public ResponseImpl(){

    }

    public ResponseImpl(final String message){
        this.message = message;
    }

    public void setMessage(final String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return new String(message);
    }
}
