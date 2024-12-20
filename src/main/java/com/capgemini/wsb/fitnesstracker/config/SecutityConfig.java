
package com.capgemini.wsb.fitnesstracker.config;

import com.capgemini.wsb.fitnesstracker.user.api.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.web.util.matcher.AntPathRequestMatcher.antMatcher;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
class SecurityConfig {

    private final UserService userService;
    private final BCryptPasswordEncoder passwordEncoder;

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService((UserDetailsService) userService);
        provider.setPasswordEncoder(passwordEncoder);
        return provider;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(
                        (auth) -> auth
                                .requestMatchers(antMatcher(HttpMethod.POST, "/v1/users")).hasRole("ADMIN")
                                .requestMatchers(antMatcher(HttpMethod.PUT, "/v1/users/**")).hasRole("ADMIN")
                                .requestMatchers(antMatcher(HttpMethod.DELETE, "/v1/users/**")).hasRole("ADMIN")
                                .requestMatchers(antMatcher("/v1/users/**")).hasAnyRole("ADMIN", "USER")
                                .anyRequest().permitAll()
                )
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .httpBasic(Customizer.withDefaults())
                .formLogin(Customizer.withDefaults());

        return http.build();
    }
}
