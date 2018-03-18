package com.skip.challenge.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import java.util.Collection;

@Entity
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty(value = "id", access = JsonProperty.Access.READ_ONLY)
    private Long id;

    @Column(name = "username", nullable = false, unique = true)
    @JsonProperty(value = "username", required = true)
    @NotBlank
    private String username;

    @Column(name = "password", nullable = false, length = 255)
    @JsonProperty(value = "password", required = true, access = JsonProperty.Access.WRITE_ONLY)
    @NotBlank
    private String password;

    @Column(name = "salt", nullable = false, length = 255)
    @JsonIgnore
    private String salt;

    @Column(name = "fullname", nullable = false, length = 255)
    @JsonProperty(value = "fullname", required = true)
    @NotBlank
    private String fullname;

    @Column(name = "account_non_expired", nullable = false)
    @JsonProperty(value = "accountNonExpired", access = JsonProperty.Access.READ_ONLY)
    private Boolean accountNonExpired = true;

    @Column(name = "account_non_locked", nullable = false)
    @JsonProperty(value = "accountNonLocked", access = JsonProperty.Access.READ_ONLY)
    private Boolean accountNonLocked = true;

    @Column(name = "credentials_non_expired", nullable = false)
    @JsonProperty(value = "credentialsNonExpired", access = JsonProperty.Access.READ_ONLY)
    private Boolean credentialsNonExpired = true;

    @Column(name = "enabled", nullable = false)
    @JsonProperty(value = "enabled", access = JsonProperty.Access.READ_ONLY)
    private Boolean enabled = true;

    public User() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getFullname() {
        return fullname;
    }

    @Override
    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

}
