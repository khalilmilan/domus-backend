package com.msurvey_values.msurvey_values.exception;

public class SurveyValueException extends Exception{
    private static final long serialVersionUID = 1L;

    public SurveyValueException(String message) {
        super(message);
    }

    public static String NotFoundException(Long id) {
        return "survey with "+id+" not found!";
    }

    public static String EventAlreadyExists() {
        return "survey with given name already exists";
    }
}
