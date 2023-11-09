package com.carlocodes.clipped.exceptions;

public class ClippedException extends Exception {
    public ClippedException(String message) {
        super(message);
    }

    public ClippedException(String message, Throwable cause) {
        super(message, cause);
    }
}
