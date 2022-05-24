package me.dyatkokg.artefactapi.service;

import me.dyatkokg.artefactapi.dto.LoginDTO;
import me.dyatkokg.artefactapi.entity.User;

public interface UserService {

    User register(LoginDTO user);

    String login(LoginDTO login);
}
