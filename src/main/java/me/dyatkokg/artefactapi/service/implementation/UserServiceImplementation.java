package me.dyatkokg.artefactapi.service.implementation;

import lombok.RequiredArgsConstructor;
import me.dyatkokg.artefactapi.dto.LoginDTO;
import me.dyatkokg.artefactapi.entity.User;
import me.dyatkokg.artefactapi.exception.UserAlreadyExistException;
import me.dyatkokg.artefactapi.repository.UserRepository;
import me.dyatkokg.artefactapi.service.TokenProvider;
import me.dyatkokg.artefactapi.service.UserService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImplementation implements UserService {

    private final UserRepository repository;

    private final BCryptPasswordEncoder passwordEncoder;


    @Override
    public User register(LoginDTO user) {
        final String rawPassword = user.getPassword();
        final String encoded = passwordEncoder.encode(rawPassword);

        if (repository.existsByUsername(user.getUsername())) {
            throw new UserAlreadyExistException();
        }

        return repository.save(User.builder()
                .username(user.getUsername())
                .password(encoded)
                .build());
    }

    @Override
    public String login(LoginDTO login) {
        return null;
    }
}
