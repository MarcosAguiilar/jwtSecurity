package com.ejercicio.jwtsecurity;

import com.ejercicio.jwtsecurity.model.User;
import com.ejercicio.jwtsecurity.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootApplication
public class JwtSecurityApplication {

    @Autowired
    private UserRepository userRepository;

    @PostConstruct
    public void initUsers() {
        List<User> users = Stream.of(
                new User(101, "dms", "password", "dms@gmail.com"),
                new User(102, "jorge", "password", "jorge@gmail.com"),
                new User(103, "pedro", "password", "pedro@gmail.com"),
                new User(104, "antonio", "password", "antonio@gmail.com")
        ).collect(Collectors.toList());

        userRepository.saveAll(users);
    }

    public static void main(String[] args) {
        SpringApplication.run(JwtSecurityApplication.class, args);
    }

}
