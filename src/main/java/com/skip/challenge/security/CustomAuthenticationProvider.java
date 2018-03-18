package com.skip.challenge.security;

import com.skip.challenge.model.User;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CustomAuthenticationProvider extends DaoAuthenticationProvider {

    public CustomAuthenticationProvider() {
        super();
    }

    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails,
                                                  UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
        String newCredentials = Optional.ofNullable(authentication.getCredentials())
                .map(credentials -> ((User) userDetails).getSalt() + credentials)
                .orElse(null);

        super.additionalAuthenticationChecks(userDetails,new UsernamePasswordAuthenticationToken(authentication.getPrincipal(), newCredentials, authentication.getAuthorities()));
    }

}