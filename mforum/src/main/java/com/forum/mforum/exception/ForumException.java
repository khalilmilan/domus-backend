package com.forum.mforum.exception;

public class ForumException extends Exception{

    private static final long serialVersionUID = 1L;

    public ForumException(String message) {
        super(message);
    }

    public static String NotFoundException(Long id) {
        return "forum with "+id+" not found!";
    }

    public static String ForumAlreadyExists() {
        return "forum with given name already exists";
    }
        }
