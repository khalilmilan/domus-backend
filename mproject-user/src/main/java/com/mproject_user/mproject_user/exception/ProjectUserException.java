package com.mproject_user.mproject_user.exception;

public class ProjectUserException extends Exception{
    private static final long serialVersionUID = 1L;

    public ProjectUserException(String message) {
        super(message);
    }

    public static String NotFoundException(Long id) {
        return "event with "+id+" not found!";
    }

    public static String EventAlreadyExists() {
        return "event with given name already exists";
    }
}
