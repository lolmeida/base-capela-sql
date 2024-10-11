package com.lolmeida.dto.request;

import lombok.Builder;

@Builder
public record UserRequest(
        String mail,
        String name,
        String phoneNumber,
        String address
) {
}
