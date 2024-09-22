package com.msurvey.msurvey.exception;

public class SurveyException extends Exception{
    private static final long serialVersionUID = 1L;

    public SurveyException(String message) {
        super(message);
    }

    public static String NotFoundException(Long id) {
        return "survey with "+id+" not found!";
    }

    public static String EventAlreadyExists() {
        return "survey with given name already exists";
    }
}
