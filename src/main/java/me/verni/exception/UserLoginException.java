package me.verni.exception;

public class UserLoginException extends RuntimeException{
    public UserLoginException(String message){
        super(message);
    }
}
