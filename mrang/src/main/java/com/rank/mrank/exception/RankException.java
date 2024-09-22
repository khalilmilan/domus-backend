package com.rank.mrank.exception;

public class RankException extends Exception{
    private static final long serialVersionUID = 1L;

    public RankException(String message) {
        super(message);
    }

    public static String NotFoundException(Long id) {
        return "rank with "+id+" not found!";
    }

    public static String EventAlreadyExists() {
        return "rank with given name already exists";
    }
        }
