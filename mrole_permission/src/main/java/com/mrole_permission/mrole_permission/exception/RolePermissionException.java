package com.mrole_permission.mrole_permission.exception;

public class RolePermissionException extends Exception{

    private static final long serialVersionUID = 1L;

    public RolePermissionException(String message) {
        super(message);
    }

    public static String NotFoundException(Long id) {
        return "role_permission with "+id+" not found!";
    }

    public static String GroupeAlreadyExists() {
        return "role_permission with given name already exists";
    }
}
