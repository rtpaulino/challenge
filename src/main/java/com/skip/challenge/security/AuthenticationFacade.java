package com.skip.challenge.security;

import com.skip.challenge.model.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AuthenticationFacade implements IAuthenticationFacade {

    @Override
    public Optional<Authentication> getAuthentication() {
        return Optional.ofNullable(SecurityContextHolder.getContext().getAuthentication());
    }

    @Override
    public Optional<String> getUsername() {
        return getAuthentication().map(authentication -> (String)authentication.getPrincipal());
    }

    @Override
    public Optional<Long> getUserId() {
        return getAuthentication().map(authentication -> {
            CustomUsernamePasswordAuthenticationToken token = (CustomUsernamePasswordAuthenticationToken)authentication;
            return Long.valueOf(token.getJwtBody().get("userId", String.class));
        });
    }

}
