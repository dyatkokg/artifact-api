package me.dyatkokg.artefactapi.exception.handler;

import lombok.extern.slf4j.Slf4j;
import me.dyatkokg.artefactapi.exception.ArtifactNotFoundException;
import me.dyatkokg.artefactapi.exception.PasswordInvalidException;
import me.dyatkokg.artefactapi.exception.UserAlreadyExistException;
import me.dyatkokg.artefactapi.exception.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class APIExceptionHandler {

    @ExceptionHandler({ArtifactNotFoundException.class})
    public ResponseEntity<Object> handleArtifactNotFound(ArtifactNotFoundException exception) {
        log.error(exception.getMessage());
        return ResponseEntity.status(HttpStatus.NO_CONTENT.ordinal()).body("Artifact was not found. Try again!");
    }

    @ExceptionHandler({PasswordInvalidException.class})
    public ResponseEntity<Object> handleInvalidPassword(PasswordInvalidException exception) {
        log.error(exception.getMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid password. Try again!");
    }
    @ExceptionHandler({UserNotFoundException.class})
    public ResponseEntity<Object> handleUserNotFound(UserNotFoundException exception) {
        log.error(exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User with this username was not found. Try again!");
    }

    @ExceptionHandler({UserAlreadyExistException.class})
    public ResponseEntity<Object> handleUserExist(UserAlreadyExistException exception) {
        log.error(exception.getMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User with this username already exists");
    }
}
