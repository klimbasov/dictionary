package com.dictionary.status;

public class ApplicationStatus {
    private Boolean isEndOfExecutionFlag;

    public ApplicationStatus(){
        this.isEndOfExecutionFlag = false;
    }

    public void setEndOfExecution() {
        isEndOfExecutionFlag = true;
    }

    public Boolean isEndOfExecution(){
        return isEndOfExecutionFlag;
    }
}
