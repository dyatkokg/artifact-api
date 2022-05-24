package me.dyatkokg.artefactapi.exception;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException() {
        super("User with this username was not found");
    }

}
