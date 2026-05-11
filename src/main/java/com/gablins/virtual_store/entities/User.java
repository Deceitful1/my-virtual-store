package com.gablins.virtual_store.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gablins.VO.UserDTO;
import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "users")
public class User implements UserDetails
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false)
    private String username;
    @Column(unique = false, nullable = false)
    private String password;
    @OneToMany(mappedBy = "user", orphanRemoval = true, fetch = FetchType.EAGER)
    private List<CartItem> cartItems = new ArrayList<>();


    public User()
    {
    }

    public User(String username, String password, List<CartItem> cartItems)
    {
        this.username = username;
        this.password = password;
        this.cartItems = cartItems;
    }

    public Long getId()
    {
        return id;
    }

    public String getUsername()
    {
        return username;
    }

    @Override
    public boolean isAccountNonExpired()
    {
        return true;
    }

    @Override
    public boolean isAccountNonLocked()
    {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired()
    {
        return true;
    }

    @Override
    public boolean isEnabled()
    {
        return true;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }
        public List<CartItem> getCartItems()
        {
           return cartItems;
        }

    public void setId(Long id)
    {
        this.id = id;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities()
    {
        return List.of(() -> "ROLE_USER");
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public void setCartItems(List<CartItem> cartItems)
    {
        this.cartItems = cartItems;
    }
    public void addToCart(CartItem item)
    {
        cartItems.add(item );
    }

    public User convertToEntity(UserDTO dto)
    {
        return new User(dto.getUsername(),dto.getPassword(),dto.getCartItems());
    }



    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (!(o instanceof User user)) return false;
        return Objects.equals(getId(), user.getId());
    }

    @Override
    public int hashCode()
    {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString()
    {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }


}
