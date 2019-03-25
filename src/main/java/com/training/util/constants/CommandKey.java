package com.training.util.constants;

public enum CommandKey {
    COMMAND_VIEW_HOME_PAGE("view_home_page"),
    COMMAND_VIEW_LOGIN_PAGE("view_login_page"),
    COMMAND_VIEW_REGISTRATION_PAGE("view_registration_page"),
    COMMAND_VIEW_ERROR_PAGE("view_error_page"),
    COMMAND_VIEW_ADMIN_PAGE("view_admin_page"),
    COMMAND_LOGOUT("logout"),
    COMMAND_CHANGE_LOCALIZATION("change_localization"),
    COMMAND_VIEW_BILL_PAGE("view_bill_page"),
    COMMAND_ADD_PRODUCTS_TO_BILL("add_products_to_bill"),
    COMMAND_VIEW_STOCK_PAGE("view_stock_page"),
    COMMAND_UPDATE_PRODUCT_INFO("update_product_info"),
    COMMAND_VIEW_PRODUCTS_IN_BILL("view_products_in_bill"),
    COMMAND_CREATE_PRODUCT("create_product"),
    COMMAND_CLOSE_BILL("close_bill"),
    COMMAND_OPEN_BILL("open_bill"),
    COMMAND_REMOVE_PRODUCT_FROM_BILL("remove_product_from_bill");

    /**
     * command's key value field
     */
    private String key;

    /**
     * Constructor assigns key field with passed parameter
     */
    CommandKey(String key) {
        this.key = key;
    }

    /**
     * @return path field value
     */
    @Override
    public String toString() {
        return key;
    }

    /**
     * defines command's key
     *
     * @param id enum key
     * @return command's key enum
     * @throws IllegalArgumentException if no matches
     */
    public static CommandKey get(String id) {
        for (CommandKey c : CommandKey.values()) {
            if (c.key.equalsIgnoreCase(id)) {
                return c;
            }
        }
        throw new IllegalArgumentException("No such command \""+id+"\" found.");
    }

}
