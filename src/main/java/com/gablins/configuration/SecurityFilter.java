package com.gablins.configuration;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Component
public class SecurityFilter extends OncePerRequestFilter
{
    private final TokenConfig tokenConfig;

    public SecurityFilter(TokenConfig tokenConfig)
    {
        this.tokenConfig = tokenConfig;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException
    {
        String authorizedHeader = request.getHeader("Authorization");

        if(authorizedHeader != null && !authorizedHeader.isBlank() && authorizedHeader.startsWith("Bearer "))
        {
            String token = authorizedHeader.substring(7);
            Optional<JWTUserData> optUser = tokenConfig.validateToken(token);
             if(optUser.isPresent())
            {
                JWTUserData userData = optUser.get();
                var authorities = List.of(new SimpleGrantedAuthority("ROLE_USER"));
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userData, null, authorities);
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }

        }
        filterChain.doFilter(request, response);
    }
}
