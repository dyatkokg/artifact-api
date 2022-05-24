package me.dyatkokg.artefactapi.controller;

import lombok.RequiredArgsConstructor;
import me.dyatkokg.artefactapi.dto.LoginDTO;
import me.dyatkokg.artefactapi.entity.User;
import me.dyatkokg.artefactapi.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;

@RestController
@RequestMapping("auth")
@RequiredArgsConstructor
public class LoginController {

    private final UserService service;

    @PostMapping("register")
    public User registerUser(@RequestBody LoginDTO user) {
        return service.register(user);
    }

    @PostMapping("login")
    public Map<String,String> login(@RequestBody LoginDTO login) {
        return Collections.singletonMap("token",service.login(login));
    }




}
