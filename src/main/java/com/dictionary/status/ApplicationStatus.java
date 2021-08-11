package com.dictionary.status;

public class ApplicationStatus {
    private Boolean isRunning;

    public ApplicationStatus() {
        this.isRunning = true;
    }

    public void setFalseIsRunning() {
        isRunning = false;
    }

    public Boolean IsRunning() {
        return isRunning;
    }
}
