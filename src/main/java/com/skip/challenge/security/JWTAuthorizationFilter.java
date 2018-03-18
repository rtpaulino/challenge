package com.skip.challenge.security;

import io.jsonwebtoken.Jwts;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.stereotype.Component;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

public class JWTAuthorizationFilter extends BasicAuthenticationFilter {

    public JWTAuthorizationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest req,
                                    HttpServletResponse res,
                                    FilterChain chain) throws IOException, ServletException {

        getAuthentication(req)
                .ifPresent(auth -> SecurityContextHolder.getContext().setAuthentication(auth));

        chain.doFilter(req, res);
    }

    private Optional<UsernamePasswordAuthenticationToken> getAuthentication(HttpServletRequest request) {
        return Optional.ofNullable(request.getHeader(SecurityConstants.HEADER_STRING))
                .filter(h -> h.startsWith(SecurityConstants.TOKEN_PREFIX))
                .flatMap(token -> {
                    return Optional.ofNullable(
                            Jwts.parser()
                                .setSigningKey(SecurityConstants.SECRET.getBytes())
                                .parseClaimsJws(token.replace(SecurityConstants.TOKEN_PREFIX, ""))
                                .getBody());
                })
                .map(jwtBody -> new CustomUsernamePasswordAuthenticationToken(jwtBody));
    }
}
