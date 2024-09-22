package com.message.mmessage.exception;

public class MessageException extends Exception{

    private static final long serialVersionUID = 1L;

    public MessageException(String message) {
        super(message);
    }

    public static String NotFoundException(Long id) {
        return "message with "+id+" not found!";
    }

    public static String MessageAlreadyExists() {
        return "message with given name already exists";
    }
}
