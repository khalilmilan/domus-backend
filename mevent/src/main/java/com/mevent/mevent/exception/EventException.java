package com.mevent.mevent.exception;

public class EventException extends Exception{
    private static final long serialVersionUID = 1L;

    public EventException(String message) {
        super(message);
    }

    public static String NotFoundException(Long id) {
        return "event with "+id+" not found!";
    }

    public static String EventAlreadyExists() {
        return "event with given name already exists";
    }
}
