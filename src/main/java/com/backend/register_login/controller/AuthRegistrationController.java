package com.backend.register_login.controller;

import com.backend.register_login.dto.RegisterRequest;
import com.backend.register_login.model.User;
import com.backend.register_login.repository.AuthUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthRegistrationController {

    private final AuthUserRepository authUserRepository;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterRequest request) {

        if (authUserRepository.existsByName(request.getName())) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("Bu kullanıcı adı zaten kullanılıyor.");
        }

        if (authUserRepository.existsByEmail(request.getEmail())) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("Bu email zaten kayıtlı.");
        }

        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(
                passwordEncoder.encode(request.getPassword())
        );



        authUserRepository.save(user);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body("Kullanıcı başarıyla oluşturuldu");
    }
}