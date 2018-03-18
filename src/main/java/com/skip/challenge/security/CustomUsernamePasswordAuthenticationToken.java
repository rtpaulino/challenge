package com.skip.challenge.security;

import io.jsonwebtoken.Claims;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.ArrayList;
import java.util.Collection;

public class CustomUsernamePasswordAuthenticationToken extends org.springframework.security.authentication.UsernamePasswordAuthenticationToken {

    private Claims jwtBody;

    public CustomUsernamePasswordAuthenticationToken(Claims jwtBody) {
        this(jwtBody.getSubject(), null, new ArrayList<>());
        this.jwtBody = jwtBody;
    }

    public CustomUsernamePasswordAuthenticationToken(Object principal, Object credentials) {
        super(principal, credentials);
    }

    public CustomUsernamePasswordAuthenticationToken(Object principal, Object credentials, Collection<? extends GrantedAuthority> authorities) {
        super(principal, credentials, authorities);
    }

    public Claims getJwtBody() {
        return jwtBody;
    }
}
