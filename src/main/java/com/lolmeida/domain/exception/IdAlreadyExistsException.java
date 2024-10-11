package com.lolmeida.domain.exception;

/**
 * ID Already Exists exception.
 */
public class IdAlreadyExistsException extends RuntimeException {

    private final String id;

    /**
     * Constructor of id already exists exception.
     *
     * @param id
     *          The id.
     */
    public IdAlreadyExistsException(final String id) {
        super();
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
