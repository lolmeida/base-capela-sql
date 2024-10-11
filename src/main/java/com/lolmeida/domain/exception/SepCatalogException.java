package com.lolmeida.domain.exception;

/**
 * ID Already Exists exception.
 */
public class SepCatalogException extends RuntimeException {

    private final Throwable innerException;

    /**
     * Constructor of sep catalog error exception.
     *
     * @param innerException
     *          Exception thrown by SEP Catalog communication layer.
     */
    public SepCatalogException(final Throwable innerException) {
        super();
        this.innerException = innerException;
    }

    public Throwable getInnerException() {
        return innerException;
    }
}
