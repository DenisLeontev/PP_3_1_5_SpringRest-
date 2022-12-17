package ru.kata.spring.boot_security.demo.exception_handlers;

public class UserNotFoundException extends RuntimeException{

    public UserNotFoundException(String message) {
        super(message);
    }
}
