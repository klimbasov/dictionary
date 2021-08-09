package com.dictionary.userInterface;

import java.util.Scanner;

public class ConsoleIOImpl implements  ConsoleIO{
    private final Scanner scanner;

    public ConsoleIOImpl(){
        this.scanner = new Scanner(System.in);
    }

    public String getInputString(){
        return scanner.nextLine();
    }

    public void sendToOutput(final String message){
        System.out.println(message);
    }
}
