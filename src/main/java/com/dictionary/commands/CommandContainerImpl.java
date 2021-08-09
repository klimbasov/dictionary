
package com.dictionary.commands;

import com.dictionary.entity.CommandPackage;
import com.dictionary.response.Response;
import com.dictionary.tags.CommandTag;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class CommandContainerImpl implements CommandsContainer{
    private HashMap<String[], Function<CommandPackage, Response>> stringCommandHashMap;
    private final CommandActivity commandActivity;

    public CommandContainerImpl(final CommandActivity commandActivity){
        this.stringCommandHashMap = new HashMap<>();
        this.commandActivity = commandActivity;
        this.createStringCommandHashMap();
    }
    @Override
    public Command getCommand(final String commandName, final CommandPackage commandPackage) {
        CommandImpl command = new CommandImpl(commandActivity.invalidActivity, commandPackage);
        for (Map.Entry<String[], Function<CommandPackage, Response>> entry : stringCommandHashMap.entrySet()){
            for(String commandAlias : entry.getKey()){
                if(commandName.equals(commandAlias)){
                    command = new CommandImpl(entry.getValue(), commandPackage);
                }
            }
        }
        return command;
    }

    private void createStringCommandHashMap(){
        stringCommandHashMap.put(CommandTag.ADD.getAliases(), commandActivity.addActivity);
        stringCommandHashMap.put(CommandTag.FIND.getAliases(), commandActivity.findActivity);
        stringCommandHashMap.put(CommandTag.SHOW_ALL.getAliases(), commandActivity.showAllActivity);
        stringCommandHashMap.put(CommandTag.CLOSE.getAliases(), commandActivity.closeActivity);
        stringCommandHashMap.put(CommandTag.HELP.getAliases(), commandActivity.helpActivity);
        stringCommandHashMap.put(CommandTag.QUIZ.getAliases(), commandActivity.quizActivity);
    }
}