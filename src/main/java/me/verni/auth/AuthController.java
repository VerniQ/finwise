package me.verni.auth;

import me.verni.auth.dto.LoginRequestDto;
import me.verni.auth.dto.RegistrationRequestDto;
import me.verni.exception.UserLoginException;
import me.verni.exception.UserRegistrationException;
import me.verni.user.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/signup") // Fixed typo in endpoint name
    public ResponseEntity<String> register(@RequestBody RegistrationRequestDto request) {
        try {
            userService.createUser(request.getUsername(), request.getEmail(), request.getPassword());
            return ResponseEntity.ok("User registered successfully");
        } catch (UserRegistrationException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequestDto request) {
        try {
            userService.login(request.getEmail(), request.getPassword());
            return ResponseEntity.ok("Login successful");
        } catch (UserLoginException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}

