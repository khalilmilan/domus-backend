package com.mproject.mproject.exception;

public class ProjectException extends Exception{
    private static final long serialVersionUID = 1L;

    public ProjectException(String message) {
        super(message);
    }

    public static String NotFoundException(Long id) {
        return "project with "+id+" not found!";
    }

    public static String EventAlreadyExists() {
        return "project with given name already exists";
    }
}
