package com.skip.challenge.vo;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;

public class AuthUser {

    @JsonProperty("username")
    @NotBlank
    private String username;

    @JsonProperty(value = "password", access = JsonProperty.Access.WRITE_ONLY)
    @NotBlank
    private String password;

    public AuthUser() {}

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
