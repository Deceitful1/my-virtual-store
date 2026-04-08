package com.gablins.configuration;

import com.gablins.virtual_store.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthConfig implements UserDetailsService
{
    @Autowired
    private final UserRepository userRepository;

    public AuthConfig(UserRepository userRepository)
    {
        this.userRepository = userRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
        return userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(username));
    }
}
