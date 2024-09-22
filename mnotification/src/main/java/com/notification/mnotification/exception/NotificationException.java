package com.notification.mnotification.exception;

public class NotificationException extends Exception{

    private static final long serialVersionUID = 1L;

    public NotificationException(String message) {
        super(message);
    }

    public static String NotFoundException(Long id) {
        return "notification with "+id+" not found!";
    }

    public static String NotificationAlreadyExists() {
        return "notification with given name already exists";
    }
}
