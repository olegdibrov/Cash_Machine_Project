package com.training.util;

import java.util.Locale;
import java.util.ResourceBundle;

public enum ResourceManager {
    /**
     * localization control
     */
    INSTANCE;

    /**
     * resource bundle field
     */
    private ResourceBundle resourceBundle;

    /**
     * constructor without parameters
     * sets default locale
     */
    ResourceManager() {
        Locale.setDefault(Locale.US);
        resourceBundle = ResourceBundle.getBundle("localization.msg", Locale.getDefault());
    }

    /**
     * changes locale
     *
     * @param locale locale
     */
    public void changeLocale(Locale locale) {
        resourceBundle = ResourceBundle.getBundle("localization.msg", locale);
    }

    /**
     * gets value by key
     *
     * @param key resource key
     */
    public String getValue(String key) {
        return resourceBundle.getString(key);
    }
}
