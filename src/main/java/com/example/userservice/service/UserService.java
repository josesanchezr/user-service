package com.example.userservice.service;

import com.example.userservice.exception.EmailAlreadyRegisteredException;
import com.example.userservice.model.User;
import com.example.userservice.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User registerUser(User user) throws EmailAlreadyRegisteredException {
        Optional<User> existingUser = userRepository.findByEmail(user.getEmail());
        if (existingUser.isPresent()) {
            log.error("El correo ya existe");
            throw new EmailAlreadyRegisteredException("El correo ya está registrado");
        }

        // Generar token
        String token = UUID.randomUUID().toString();
        user.setToken(token);
        user.setLastLogin(LocalDateTime.now());
        user.setActive(true);

        return userRepository.save(user);
    }
}
