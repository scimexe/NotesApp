package com.example.Notes.auth;

import com.example.Notes.config.JwtService;
import com.example.Notes.enumeration.Role;
import com.example.Notes.user.User;
import com.example.Notes.user.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    public AuthResponse register(RegisterRequest request) throws Exception {
        Optional<User> user1 = userRepo.findByEmail(request.getEmail());
        if (user1.isPresent()){
            throw new Exception("Email already registered.");
        }
        var user =  User.builder()
                        .firstName(request.getFirstName())
                        .lastName(request.getLastName())
                        .email(request.getEmail())
                        .password(passwordEncoder.encode(request.getPassword()))
                        .role(Role.USER)
                        .build();
        userRepo.save(user);
        var jwtToken = jwtService.generateToken(user);

        return AuthResponse.builder()
                           .token(jwtToken)
                           .build();
    }

    public AuthResponse authenticate(AuthRequest request) {

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        var user = userRepo.findByEmail(request.getEmail()).orElseThrow();
        var jwtToken = jwtService.generateToken(user);

        return AuthResponse.builder()
                .token(jwtToken)
                .build();
    }
}
