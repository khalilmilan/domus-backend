package com.groupe.mgroupe.exception;

public class GroupeException extends Exception{

    private static final long serialVersionUID = 1L;

    public GroupeException(String message) {
        super(message);
    }

    public static String NotFoundException(Long id) {
        return "groupe with "+id+" not found!";
    }

    public static String GroupeAlreadyExists() {
        return "groupe with given name already exists";
    }
}
