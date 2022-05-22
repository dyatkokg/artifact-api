package me.dyatkokg.artefactapi.exception;

public class ArtifactNotFoundException extends RuntimeException {

    public ArtifactNotFoundException() {
        super("Artifact was not found");
    }
}
