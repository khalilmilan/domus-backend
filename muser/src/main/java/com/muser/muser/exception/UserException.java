package com.muser.muser.exception;

public class UserException extends Exception{

    private static final long serialVersionUID = 1L;

    public UserException(String message) {
        super(message);
    }

    public static String NotFoundException(Long id) {
        return "user with "+id+" not found!";
    }

    public static String NotFoundUserException(String email) {
        return "user with email:"+email+" not found!";
    }

    public static String UserAlreadyExists() {
        return "user with given name already exists";
    }
}
