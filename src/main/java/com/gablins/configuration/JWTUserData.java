package com.gablins.configuration;

import lombok.Builder;

@Builder
public record JWTUserData(Long userId, String email)
{

}
