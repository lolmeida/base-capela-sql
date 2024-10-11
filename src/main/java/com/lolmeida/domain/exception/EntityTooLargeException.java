package com.lolmeida.domain.exception;

/**
 * Exception thrown when a file is too large to be accepted by the service.
 */
public class EntityTooLargeException extends RuntimeException {

    private static final long serialVersionUID = 6080964649430823733L;

    private final int maxEntitySize;

    private final int entitySize;

    /**
     * Constructor of file too large exception.
     *
     * @param maxEntitySize
     *          Max size of entity in bytes
     * @param entitySize
     *          Current entity size in bytes
     * */
    public EntityTooLargeException(final int maxEntitySize, final int entitySize) {
        super();
        this.maxEntitySize = maxEntitySize;
        this.entitySize = entitySize;
    }

    public int getEntitySize() {
        return entitySize;
    }

    public int getMaxEntitySize() {
        return maxEntitySize;
    }
}
