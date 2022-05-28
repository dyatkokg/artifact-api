package me.dyatkokg.artefactapi.exception;

public class CommentNotFoundException extends RuntimeException{

    public CommentNotFoundException() {
        super("Comment with this id was not found");
    }
}
