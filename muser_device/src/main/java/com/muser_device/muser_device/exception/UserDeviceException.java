package com.muser_device.muser_device.exception;

public class UserDeviceException extends Exception{

    private static final long serialVersionUID = 1L;

    public UserDeviceException(String message) {
        super(message);
    }

    public static String NotFoundException(Long id) {
        return "userDevice with "+id+" not found!";
    }

    public static String GroupeAlreadyExists() {
        return "userDevice with given name already exists";
    }
}
