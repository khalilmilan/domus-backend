package com.commentaire.mcommentaire.exception;

public class CommentaireException extends Exception{

    private static final long serialVersionUID = 1L;

    public CommentaireException(String message) {
        super(message);
    }

    public static String NotFoundException(Long id) {
        return "commentaire with "+id+" not found!";
    }

    public static String CommentaireAlreadyExists() {
        return "commentaire with given name already exists";
    }
}
