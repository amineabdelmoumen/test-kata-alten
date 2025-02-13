package ma.alten.testAlten.security.controller;

import ma.alten.testAlten.security.domain.User;
import ma.alten.testAlten.security.dto.AuthenticationRequest;
import ma.alten.testAlten.security.dto.AuthenticationResponse;
import ma.alten.testAlten.security.dto.RegisterRequest;
import ma.alten.testAlten.security.repository.UserRepository;
import ma.alten.testAlten.security.service.MyUserDetailsService;
import ma.alten.testAlten.security.service.UserDetailsImpl;
import ma.alten.testAlten.security.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private MyUserDetailsService userDetailsService;

    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/token")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(), authenticationRequest.getPassword())
            );
        } catch (Exception e) {
            throw new Exception("Incorrect username or password", e);
        }

        final UserDetailsImpl userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getEmail());
        final String jwt = jwtUtil.generateToken(userDetails);

        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }
    // **Register API**
    @PostMapping("/account")
    public ResponseEntity<?> registerUser(@RequestBody RegisterRequest registerRequest) {
        // Check if the user already exists
        if (userRepository.existsByEmail(registerRequest.getEmail())) {
            return ResponseEntity.badRequest().body("Error: Email is already in use!");
        }

        // Create new user
        User user = new User();
        user.setUsername(registerRequest.getUsername());
        user.setFirstName(registerRequest.getFirstname());
        user.setEmail(registerRequest.getEmail());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        userRepository.save(user);
        return ResponseEntity.ok("User registered successfully!");
    }
}