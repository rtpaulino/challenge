package com.skip.challenge.services;

import com.skip.challenge.exception.BadRequestException;
import com.skip.challenge.model.User;
import com.skip.challenge.repositories.UserRepository;
import com.skip.challenge.util.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository repository;

    public User register(User user) {
        Optional<User> existingUser = repository.findByUsername(user.getUsername());
        if (existingUser.isPresent()) {
            throw new BadRequestException(String.format("User %s already exists", user.getUsername()));
        }

        user.setSalt(SecurityUtils.makeSalt());
        user.setPassword(SecurityUtils.encryptPassword(user.getSalt() + user.getPassword()));

        return repository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(username));
    }
}
