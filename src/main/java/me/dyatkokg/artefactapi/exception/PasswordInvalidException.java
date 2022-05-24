package me.dyatkokg.artefactapi.exception;

public class PasswordInvalidException extends RuntimeException {
    public PasswordInvalidException() {
        super("Invalid password");
    }

}
