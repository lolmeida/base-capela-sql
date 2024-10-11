package com.lolmeida.domain.exception;

import java.util.List;

/**
 * Exception thrown when the extension for a file doesn't match its contents.
 */
public class FileExtensionMismatchException extends RuntimeException {

    private static final long serialVersionUID = -7044843891776390394L;

    private static final String ERROR_MESSAGE_PLACEHOLDER = "File extension does not match the file contents. "
            + "Sent file extension: %s Acceptable file extensions: %s";

    /**
     * Constructor of file too large exception.
     *
     * @param expectedFileExtensions
     *          File extensions that are acceptable for the file.
     * @param actualFileExtension
     *          File extension that was sent.
     * */
    public FileExtensionMismatchException(final List<String> expectedFileExtensions, final String actualFileExtension) {
        super(String.format(ERROR_MESSAGE_PLACEHOLDER, actualFileExtension,
                            String.join(", ", expectedFileExtensions)));
    }
}
