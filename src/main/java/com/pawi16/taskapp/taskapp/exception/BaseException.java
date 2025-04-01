package com.pawi16.taskapp.taskapp.exception;

public abstract class BaseException extends Exception{

    public BaseException(String code) {
        super(code);
    }
}
