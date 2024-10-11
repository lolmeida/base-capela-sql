package com.lolmeida.domain.exception;

/**
 * Invalid Operation exception.
 */
public class InvalidOperationException extends RuntimeException {

    private final String message;

    /**
     * Constructor to invalid operation exception.
     *
     * @param message
     *          The message exception.
     */
    public InvalidOperationException(final String message) {
        super();
        this.message = message;
    }

    public String getMessage() { return message; }
}
