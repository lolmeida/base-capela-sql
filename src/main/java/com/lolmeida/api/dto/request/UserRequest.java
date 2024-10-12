package com.lolmeida.api.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

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
