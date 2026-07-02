package com.ejercicio.jwtsecurity.controller;

import com.ejercicio.jwtsecurity.model.User;
import com.ejercicio.jwtsecurity.service.UserService;
import com.ejercicio.jwtsecurity.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class MainController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/")
    public String index() {
        return "OK";
    }

    @PostMapping("/sing-up")
    public User singUp(@RequestBody User user) {
        User created = userService.addUser(user);
        created.setPassword(null);
        return created;
    }

    @PostMapping("/login")
    public String login(@RequestBody User user) {
        User storedUser = userService.findUserByName(user.getName());
        if (storedUser == null || !passwordEncoder.matches(user.getPassword(), storedUser.getPassword())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Usuario o contraseña incorrectos");
        }
        return jwtUtil.generateToken(user.getName());
    }
}
