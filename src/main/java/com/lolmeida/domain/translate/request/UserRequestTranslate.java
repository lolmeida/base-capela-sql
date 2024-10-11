package com.lolmeida.domain.translate.request;

import java.time.Instant;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import jakarta.enterprise.context.ApplicationScoped;

import com.lolmeida.domain.entity.database.User;
import com.lolmeida.dto.request.UserRequest;


/**
 * Translates {@link UserRequest} DTO object to {@link User} DTO object.
 */
@ApplicationScoped
public class UserRequestTranslate implements Function<UserRequest, User> {

    @Override
    public User apply(final UserRequest UserRequest) {
        return Optional.ofNullable(UserRequest)
                .map(this::buildResponse)
                .orElse(null);
    }

/*    private Set<Label> applyLabelsTranslate(final Set<LabelThemeRequest> labelsList) {
        return Optional.ofNullable(labelsList)
                .map(list -> list.stream()
                        .map(labels -> translateService.translate(labels,
                                Label.class))
                        .collect(Collectors.toSet()))
                .orElseGet(Collections::emptySet);
    }*/

    private User buildResponse(final UserRequest userRequest) {
        return User.builder()
                .name(userRequest.name())
                .mail(userRequest.mail())
                .phoneNumber(userRequest.phoneNumber())
                .address(userRequest.address())
                .build();
    }
}
