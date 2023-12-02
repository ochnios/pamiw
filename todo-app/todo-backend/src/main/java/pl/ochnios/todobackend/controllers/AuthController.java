package pl.ochnios.todobackend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.ochnios.todobackend.dtos.LoginDto;
import pl.ochnios.todobackend.dtos.RegisterDto;
import pl.ochnios.todobackend.models.User;
import pl.ochnios.todobackend.repositories.RoleRepository;
import pl.ochnios.todobackend.repositories.UserRepository;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager, UserRepository userRepository,
                          RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginDto loginDto) {
        System.out.println(loginDto.getPassword() + " " + passwordEncoder.encode(loginDto.getPassword()));

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword())
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        return new ResponseEntity<>("User logged in successfully!", HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterDto registerDto) {
        String email = registerDto.getEmail();
        if (userRepository.existsByEmail(email)) {
            return new ResponseEntity<>("Email is taken!", HttpStatus.BAD_REQUEST);
        }

        String encodedPassword = passwordEncoder.encode(registerDto.getPassword());

        User user = User.builder()
                .name(registerDto.getName())
                .surname(registerDto.getSurname())
                .email(email)
                .password(encodedPassword)
                .role(roleRepository.findByName("USER").get())
                .build();

        User saved = userRepository.save(user);
        System.out.println(registerDto.getPassword() + " " + saved.getPassword());

        return new ResponseEntity<>("User registered successfully", HttpStatus.OK);
    }
}
