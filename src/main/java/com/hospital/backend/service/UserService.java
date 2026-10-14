package com.hospital.backend.service;

import com.hospital.backend.dto.AuthResponse;
import com.hospital.backend.dto.LoginRequest;
import com.hospital.backend.dto.RegisterRequest;
import com.hospital.backend.entity.User;
import com.hospital.backend.repository.UserRepository;
import org.springframework.stereotype.Service;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Optional;

/**
 * UserService - Handles user registration, authentication, and profiles.
 * Designed for college project with clear, clean structure.
 */
@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Hash password with SHA-256 for secure storage.
     */
    public String hashPassword(String plainPassword) {
        if (plainPassword == null) return "";
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] encodedHash = digest.digest(plainPassword.getBytes(StandardCharsets.UTF_8));
            StringBuilder hexString = new StringBuilder();
            for (byte b : encodedHash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            return plainPassword;
        }
    }

    /**
     * Register a new user.
     */
    public AuthResponse register(RegisterRequest req) {
        if (userRepository.existsByEmail(req.getEmail())) {
            return AuthResponse.failure("Email address is already registered. Please log in.");
        }

        String role = (req.getRole() != null && !req.getRole().isBlank()) ? req.getRole().trim() : "ROLE_CUSTOMER";
        if (!role.startsWith("ROLE_")) {
            role = "ROLE_" + role.toUpperCase();
        }

        // Store hashed password
        String hashedPassword = hashPassword(req.getPassword());

        User user = new User(
                req.getFullName().trim(),
                req.getEmail().trim().toLowerCase(),
                hashedPassword,
                req.getPhone(),
                req.getAddress(),
                role
        );

        User saved = userRepository.save(user);

        return new AuthResponse(
                true,
                "Registration successful!",
                saved.getId(),
                saved.getFullName(),
                saved.getEmail(),
                saved.getRole(),
                saved.getPhone(),
                saved.getAddress()
        );
    }

    /**
     * Authenticate user with email and password.
     */
    public AuthResponse login(LoginRequest req) {
        String email = req.getEmail().trim().toLowerCase();
        Optional<User> userOpt = userRepository.findByEmail(email);

        if (userOpt.isEmpty()) {
            return AuthResponse.failure("Invalid email or password.");
        }

        User user = userOpt.get();
        String hashedInput = hashPassword(req.getPassword());

        // Check against both hashed password and raw password (for seed data compatibility)
        boolean matches = user.getPassword().equals(hashedInput) || user.getPassword().equals(req.getPassword());

        if (!matches) {
            return AuthResponse.failure("Invalid email or password.");
        }

        return new AuthResponse(
                true,
                "Login successful!",
                user.getId(),
                user.getFullName(),
                user.getEmail(),
                user.getRole(),
                user.getPhone(),
                user.getAddress()
        );
    }

    /**
     * Get user by ID.
     */
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    /**
     * Get all users (for admin management).
     */
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
