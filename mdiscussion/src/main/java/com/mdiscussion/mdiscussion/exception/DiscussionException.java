package com.mdiscussion.mdiscussion.exception;

public class DiscussionException extends Exception{
    private static final long serialVersionUID = 1L;

    public DiscussionException(String message) {
        super(message);
    }

    public static String NotFoundException(Long id) {
        return "discussion with "+id+" not found!";
    }

    public static String EventAlreadyExists() {
        return "discussion with given name already exists";
    }
}
