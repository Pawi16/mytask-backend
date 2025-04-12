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

    //login
    public static UserException loginRequestNull() {
        return new UserException("login.request.null");
    }

    public static UserException loginEmailNull() {
        return new UserException("login.email.null");
    }

    public static UserException loginPasswordNull() {
        return new UserException("login.password.null");
    }

    public static UserException loginUserNotFound() {
        return new UserException("login.user.not.found");
    }

    public static UserException loginPasswordNotMatch() {
        return new UserException("login.password.not.match");
    }

    //get profile by id
    public static UserException getProfileIdNull() {
        return new UserException("get.profile.id.null");
    }

    public static UserException getProfileNotFound() {
        return new UserException("get.profile.not.found");
    }

    //get current uer
    public static UserException getCurrentUserUnauthenticated() {
        return new UserException("get.current.user.unauthenticated");
    }
    public static UserException getCurrentUserInvalidPrincipalTypeCast() {
        return new UserException("get.current.user.invalid.principal.type.cast");
    }
    public static UserException getCurrentUserUserNotFound() {
        return new UserException("get.current.user.user.not.found");
    }

    //edit my profile
    public static UserException editMyProfileRequestNull() {
        return new UserException("edit.my.profile.request.null");
    }

    //edit profile
    public static UserException editProfileUserIdNull() {
        return new UserException("edit.profile.user.id.null");
    }
    public static UserException editProfileUserNotFound() {
        return new UserException("edit.profile.user.not.found");
    }

}
