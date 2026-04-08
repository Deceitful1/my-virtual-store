    package com.gablins.security;

    import com.gablins.configuration.SecurityFilter;
    import org.springframework.context.annotation.Bean;
    import org.springframework.context.annotation.Configuration;
    import org.springframework.http.HttpMethod;
    import org.springframework.security.config.Customizer;
    import org.springframework.security.config.annotation.web.builders.HttpSecurity;
    import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
    import org.springframework.security.config.http.SessionCreationPolicy;
    import org.springframework.security.core.userdetails.UserDetailsService;
    import org.springframework.security.web.SecurityFilterChain;
    import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

    @Configuration
    @EnableWebSecurity
    public class SecurityConfig
    {
        private final UserDetailsService userDetailsService;
        private final SecurityFilter securityFilter;

        public SecurityConfig(UserDetailsService userDetailsService, SecurityFilter securityFilter)
        {
            this.userDetailsService = userDetailsService;
            this.securityFilter = securityFilter;
        }


        @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception
        {
            http.cors(Customizer.withDefaults()).csrf(csrf -> csrf.disable()).sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                    .authorizeHttpRequests(auth -> auth.requestMatchers(HttpMethod.POST, "/auth/**")
                            .permitAll()
                            .anyRequest().
                            authenticated()).addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class);

            return http.build();
        }




    }
