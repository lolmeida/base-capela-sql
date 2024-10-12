package com.lolmeida.domain.translate;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.BiPredicate;
import java.util.function.Function;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;

import com.lolmeida.domain.entity.database.User;
import com.lolmeida.domain.translate.request.UserRequestTranslate;
import com.lolmeida.domain.translate.response.UserResponseTranslate;
import com.lolmeida.api.dto.request.UserRequest;
import com.lolmeida.api.dto.response.UserResponse;

/**
 * Service to convert cache objects to our DTOs. This class holds a register for all the possible translations. According to the
 * class of the origin object, the correct registered translate instance will be used to handle the conversion.
 */
@ApplicationScoped
public class TranslateService {

    /**
     * Injected instance of userResponseTranslate.
     */
    @Inject
    UserResponseTranslate userResponseTranslate;

    /**
     * Injected instance of userRequestTranslate.
     */
    @Inject
    UserRequestTranslate userRequestTranslate;

    @SuppressWarnings("rawtypes")
    private Map<BiPredicate<Object, Class<?>>, Function> translatorCatalogue;

    @SuppressWarnings("rawtypes")
    @PostConstruct
    void init() {
        final Map<BiPredicate<Object, Class<?>>, Function> translatorsMap = new HashMap<>();
        //USER:
        translatorsMap.put(getTranslatePredicate(UserRequest.class, User.class), userRequestTranslate);
        translatorsMap.put(getTranslatePredicate(User.class, UserResponse.class), userResponseTranslate);

        translatorCatalogue = Collections.unmodifiableMap(translatorsMap);
    }

    /**
     * Converts an object to the desired class.
     *
     * <p>
     * <b>NOTE:</b> If the class of {@code fromObject} is not registered, an
     * {@link UnsupportedOperationException} will be thrown.
     * </p>
     *
     * @param fromObject
     *         Object to be converted.
     * @param toType
     *         Class for the returned object.
     * @param <T>
     *         Type of return object.
     *
     * @return Translated DTO.
     */
    public <T> T translate(final Object fromObject, final Class<T> toType) {
        return toType.cast(Optional.ofNullable(fromObject)
                .map(fromObjectClass -> getTranslatorForObjectIntoClass(fromObjectClass, toType).apply(fromObject))
                .orElse(null));
    }

    private BiPredicate<Object, Class<?>> getTranslatePredicate(final Class<?> fromClass, final Class<?> toClass) {
        return (o, c) -> o.getClass().equals(fromClass) && c.equals(toClass);
    }

    @SuppressWarnings("unchecked")
    private Function<Object, Object> getTranslatorForObjectIntoClass(final Object object, final Class<?> c) {
        return translatorCatalogue.entrySet()
                .stream()
                .filter(e -> e.getKey().test(object, c))
                .map(Map.Entry::getValue)
                .findFirst()
                .orElseThrow(() -> new UnsupportedOperationException("No suitable translator for object with class " + c));
    }

    public <T, R> Response createResponse(List<T> data, Class<R> responseType) {
        final List<R> list = data
                .stream()
                .map(entity -> this.translate(entity, responseType))
                .toList();
        return Response
                .ok(list)
                .build();
    }
}
