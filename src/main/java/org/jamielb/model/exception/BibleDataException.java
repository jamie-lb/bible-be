package org.jamielb.model.exception;

public class BibleDataException extends RuntimeException {

    public BibleDataException(String message) {
        super(message);
    }

    public BibleDataException(Throwable cause) {
        super(cause);
    }

}
