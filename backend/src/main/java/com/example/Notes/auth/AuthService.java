package com.example.Notes.auth;

import com.example.Notes.Log.Log;
import com.example.Notes.config.JwtService;
import com.example.Notes.enumeration.LOG;
import com.example.Notes.enumeration.Role;
import com.example.Notes.exception.NotesException;
import com.example.Notes.user.User;
import com.example.Notes.user.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    public AuthResponse register(RegisterRequest request) throws NotesException {
        Optional<User> user1 = userRepo.findByEmail(request.getEmail());
        if (user1.isPresent()){
            String msg = request.getEmail() + " already registered.";
            Log.WriteLog(LOG.ERROR, msg);
            throw new NotesException(msg);
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

        Optional<User> user = userRepo.findByEmail(request.getEmail());
        if(user.isEmpty()){
            String msg = "User" + request.getEmail() + " is not found.";
            Log.WriteLog(LOG.ERROR, msg);
            throw new NotesException(msg);
        }
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        var user1 = User.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();

        var jwtToken = jwtService.generateToken(user1);

        return AuthResponse.builder()
                .token(jwtToken)
                .build();
    }
}
