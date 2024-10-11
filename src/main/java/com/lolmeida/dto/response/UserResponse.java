package com.lolmeida.dto.response;

import lombok.Builder;


@Builder
public record UserResponse(
        String mail,
        String name,
        String phoneNumber,
        String address,

        // BaseEntity
        String id
) {

}