package com.permission.mpermission.exception;

public class PermissionException extends Exception{

    private static final long serialVersionUID = 1L;

    public PermissionException(String message) {
        super(message);
    }

    public static String NotFoundException(Long id) {
        return "permission with "+id+" not found!";
    }

    public static String PermissionAlreadyExists() {
        return "permission with given name already exists";
    }
}
