package com.lolmeida.converter;

import com.fasterxml.jackson.databind.util.StdConverter;

/**
 * Integer Converter.
 */
public class IntegerConverter extends StdConverter<String, Integer> {

    @Override
    public Integer convert(final String stringValue) {
        try {
            return Integer.parseInt(stringValue);
        } catch (final NumberFormatException exception) {
            return Integer.MIN_VALUE;
        }
    }
}
