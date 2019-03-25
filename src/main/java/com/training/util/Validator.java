package com.training.util;

import java.util.regex.Pattern;

public class Validator {

    /**
     * login regular expression field
     */
    private static final String LOGIN_VALIDATION_REGEX = "[A-Za-z0-9_]{5,20}";


    /**
     * string regular expression field
     */
    private static final String STRING_VALIDATION = "[A-Za-z0-9_А-Яа-я ]{2,45}";


    /**
     * password regular expression field
     */
    private static final String PASSWORD_VALIDATION_REGEX = "[A-Za-z0-9_]{5,20}";

    /**
     * validates login input
     *
     * @return true if input is correct
     */
    public static boolean validateLoginInput(String login) {
        return Pattern.matches(LOGIN_VALIDATION_REGEX, login);
    }

    /**
     * validates password input
     *
     * @return true if input is correct
     */
    public static boolean validatePasswordInput(String pass) {
        return Pattern.matches(PASSWORD_VALIDATION_REGEX, pass);
    }

    /**
     * validates string input
     *
     * @return true if input is correct
     */
    public static boolean validateString(String string) {
        return Pattern.matches(STRING_VALIDATION, string);
    }

}
