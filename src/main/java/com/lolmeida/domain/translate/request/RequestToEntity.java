package com.lolmeida.domain.translate.request;

import java.util.Optional;
import java.util.function.Function;

import jakarta.enterprise.context.ApplicationScoped;

import com.lolmeida.domain.entity.database.User;
import com.lolmeida.api.dto.request.UserRequest;


/**
 * Translates {@link UserRequest} DTO object to {@link User} DTO object.
 */
@ApplicationScoped
public class RequestToEntity implements Function<UserRequest, User> {

    @Override
    public User apply(final UserRequest request) {
        return Optional.ofNullable(request)
                .map(this::buildResponse)
                .orElse(null);
    }

    private User buildResponse(final UserRequest request) {
        return User.builder()
                .name(request.name())
                .mail(request.mail())
                .phoneNumber(request.phoneNumber())
                .address(request.address())
                .build();
    }



    /*    private Set<Label> applyLabelsTranslate(final Set<LabelThemeRequest> labelsList) {
        return Optional.ofNullable(labelsList)
                .map(list -> list.stream()
                        .map(labels -> translateService.translate(labels,
                                Label.class))
                        .collect(Collectors.toSet()))
                .orElseGet(Collections::emptySet);
    }*/
}
