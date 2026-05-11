package com.gablins.VO;

import com.gablins.virtual_store.entities.CartItem;
import com.gablins.virtual_store.entities.User;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDTO
{
    private Long id;
    private String username;
    private String password;
    private List<CartItem> cartItems = new ArrayList<>();

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (!(o instanceof UserDTO userDTO)) return false;
        return Objects.equals(getId(), userDTO.getId()) && Objects.equals(getUsername(), userDTO.getUsername());
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(getId(), getUsername());
    }

    public UserDTO convertToDTO(User user)
    {
        return UserDTO.builder().id(user.getId()).
                username(user.getUsername()).
                password(user.getPassword()).
                cartItems(user.getCartItems()).
                build();

    }

}
