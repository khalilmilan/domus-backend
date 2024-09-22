package com.mevent_user.mevent_user.exception;

public class EventUserException extends Exception{

    private static final long serialVersionUID = 1L;

    public EventUserException(String message) {
        super(message);
    }

    public static String NotFoundException(Long id) {
        return "event_user with "+id+" not found!";
    }

    public static String EventAlreadyExists() {
        return "event_user with given name already exists";
    }
}
