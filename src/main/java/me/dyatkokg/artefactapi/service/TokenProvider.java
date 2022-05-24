package me.dyatkokg.artefactapi.service;

import me.dyatkokg.artefactapi.entity.User;

public interface TokenProvider {

    String generateToken(User user);

}
