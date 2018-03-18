package com.skip.challenge.security;

import com.skip.challenge.model.User;
import org.springframework.security.core.Authentication;

import java.util.Optional;

public interface IAuthenticationFacade {
    Optional<Authentication> getAuthentication();
    Optional<String> getUsername();
    Optional<Long> getUserId();
}
