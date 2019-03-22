package com.training.model.entity;

import java.util.Objects;

/**
 * Class describing user role entity
 *
 * @author Oleg Dibrov
 */

public class UserRole {

    /**
     * user role id field
     */
    private Integer id;

    /**
     * user role name field
     */
    private String roleName;

    /**
     * constructor without parameters
     */
    public UserRole() {
    }

    /**
     * constructor with parameters, sets all fields
     */
    public UserRole(Integer id, String roleName) {
        this.id = id;
        this.roleName = roleName;
    }

    /**
     * @return {@link #id}
     */
    public Integer getId() {
        return id;
    }

    /**
     * sets user role id
     *
     * @param id {@link #id}
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return {@link #roleName}
     */
    public String getRoleName() {
        return roleName;
    }

    /**
     * sets user role name
     *
     * @param roleName {@link #roleName}
     */
    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserRole userRole = (UserRole) o;
        return Objects.equals(id, userRole.id) &&
                Objects.equals(roleName, userRole.roleName);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return Objects.hash(id, roleName);
    }

}
