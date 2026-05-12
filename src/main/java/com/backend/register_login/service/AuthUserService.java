package com.backend.register_login.service;

import com.backend.register_login.model.User;
import com.backend.register_login.repository.AuthUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class AuthUserService implements UserDetailsService {

    private AuthUserRepository authUserRepository;

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {

        Optional<User> authUser = authUserRepository.findByName(name);

        if (authUser.isPresent()) {
            var userObj = authUser.get();
            return org.springframework.security.core.userdetails.User.builder()
                    .username(userObj.getName())
                    .password(userObj.getPassword())
                    .build();
        }else{
            throw new UsernameNotFoundException(name);
        }
    }
}
