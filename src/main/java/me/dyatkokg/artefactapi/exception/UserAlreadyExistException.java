package me.dyatkokg.artefactapi.exception;

public class UserAlreadyExistException extends RuntimeException {

    public UserAlreadyExistException() {
        super("User with this username already exists");
    }

}
