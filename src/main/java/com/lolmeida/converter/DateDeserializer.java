package com.lolmeida.converter;

import java.io.IOException;
import java.text.MessageFormat;
import java.time.Instant;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;

/**
 * Date Deserializer. Convert string date to ${@link Instant} object.
 */
public class DateDeserializer extends JsonDeserializer<Instant> {

    private static final String MESSAGE_ERROR = "The {0} is not a valid date.";
    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME.withZone(ZoneOffset.UTC);

    @Override
    public Instant deserialize(final JsonParser parser, final DeserializationContext context) throws IOException {
        final String value = parser.getText();
        Instant date = null;

        try {
            date = Instant.from(dateTimeFormatter.parse(value));
        } catch (final Exception e) {
            throw MismatchedInputException.from(parser, context.getContextualType(), MessageFormat.format(MESSAGE_ERROR, value));
        }

        return date;
    }
}
