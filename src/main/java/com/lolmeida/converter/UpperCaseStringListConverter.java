package com.lolmeida.converter;

import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.databind.util.StdConverter;

/**
 * Converts Set of Strings to UpperCase.
 */
public class UpperCaseStringListConverter extends StdConverter<Set<String>, Set<String>> {

    @Override
    public Set<String> convert(final Set<String> strings) {
        return strings.stream()
                .map(String::toUpperCase)
                .collect(Collectors.toSet());
    }
}
