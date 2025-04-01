package com.pawi16.taskapp.taskapp.exception;

public class UserException extends BaseException {
    public UserException(String code) {
        super("user." + code);
    }

    //authentication

    public static UserException requestNull() {
        return new UserException("register.request.null");
    }

    public static UserException createEmailDuplicated() {
        return new UserException("create.email.duplicated");
    }

    public static UserException registerEmailNull() {
        return new UserException("register.email.null");
    }

    public static UserException registerPasswordNull() {
        return new UserException("register.password.null");
    }

    public static UserException registerFirstNameNull() {
        return new UserException("register.firstname.null");
    }

    public static UserException registerLastNameNull() {
        return new UserException("register.lastname.null");
    }
}
