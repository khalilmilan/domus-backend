package com.vote.mvote.exception;

public class VoteException extends Exception{

    private static final long serialVersionUID = 1L;

    public VoteException(String message) {
        super(message);
    }

    public static String NotFoundException(Long id) {
        return "vote with "+id+" not found!";
    }

    public static String VoteAlreadyExists() {
        return "vote with given name already exists";
    }
}
