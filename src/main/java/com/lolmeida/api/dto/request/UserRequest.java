package com.lolmeida.api.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import lombok.Builder;

@Builder
public record UserRequest(
        @Email
        @NotBlank
        String mail,
        @NotBlank
        String name,
        String phoneNumber,
        String address
) {
}
