package com.lolmeida.domain.translate.response;

import java.util.Optional;
import java.util.function.Function;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import com.lolmeida.domain.entity.database.User;
import com.lolmeida.domain.translate.TranslateService;
import com.lolmeida.api.dto.response.UserResponse;
import com.lolmeida.domain.service.UserService;


/**
 * Translates {@link User} DTO object to {@link UserResponse} DTO object.
 */
@ApplicationScoped
public class UserResponseTranslate implements Function<User, UserResponse> {

    /**
     * Translate Service.
     */
    @Inject
    TranslateService translateService;

    /**
     * Themes Service.
     */
    @Inject
    UserService userService;

    @Override
    public UserResponse apply(final User user) {
        return Optional.ofNullable(user)
                .map(this::buildResponse)
                .orElse(null);
    }

    private UserResponse buildResponse(final User user) {
        return UserResponse.builder()
                .id(user.getId())
                .mail(user.getMail())
                .name(user.getName())
                .phoneNumber(user.getPhoneNumber())
                .address(user.getAddress())
                .build();
    }
}
