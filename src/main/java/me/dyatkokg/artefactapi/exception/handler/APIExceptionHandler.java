package me.dyatkokg.artefactapi.exception.handler;

import lombok.extern.slf4j.Slf4j;
import me.dyatkokg.artefactapi.exception.ArtifactNotFoundException;
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
}
