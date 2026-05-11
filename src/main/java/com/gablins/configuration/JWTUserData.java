package com.gablins.configuration;

import com.gablins.virtual_store.entities.CartItem;
import lombok.Builder;

import java.util.List;

@Builder
public record JWTUserData(Long userId, String email)
{

}
