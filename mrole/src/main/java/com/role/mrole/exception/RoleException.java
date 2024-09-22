package com.role.mrole.exception;

public class RoleException extends Exception{

    private static final long serialVersionUID = 1L;

    public RoleException(String message) {
        super(message);
    }

    public static String NotFoundException(Long id) {
        return "role with "+id+" not found!";
    }

    public static String RoleAlreadyExists() {
        return "event with given name already exists";
    }
}
