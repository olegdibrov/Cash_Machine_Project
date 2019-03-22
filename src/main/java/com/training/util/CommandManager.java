package com.training.util;

public class CommandManager {
    /**
     * gets location for redirect
     *
     * @param commandName command name for redirect
     * @return direction for redirect
     */
    public static String getRedirect(String commandName) {
        return "home?command=" + commandName;
    }
}
