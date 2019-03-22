package com.training.util.constants;

public enum PageKey {

    HOME_PAGE("WEB-INF/jsp/home.jsp"),
    ERROR_PAGE("WEB-INF/jsp/error.jsp"),
    LOGIN_PAGE("WEB-INF/jsp/login.jsp"),
    REGISTRATION_PAGE("WEB-INF/jsp/registration.jsp"),
    ADMIN_PAGE("WEB-INF/jsp/admin.jsp"),
    BILL_PAGE("WEB-INF/jsp/bill.jsp");

    /**
     * page's path value field
     */
    private String path;

    /**
     * Constructor assigns path field with passed parameter
     */
    PageKey(String path) {
        this.path = path;
    }

    /**
     * @return path field value
     */
    @Override
    public String toString() {
        return path;
    }
}
