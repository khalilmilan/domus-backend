package com.mgroupe_user.mgroupe_user.exception;

public class GroupeUserException extends Exception{

    private static final long serialVersionUID = 1L;

    public GroupeUserException(String message) {
        super(message);
    }

    public static String NotFoundException(Long id) {
        return "groupe_user with "+id+" not found!";
    }

    public static String GroupeAlreadyExists() {
        return "groupe_user with given name already exists";
    }
}
