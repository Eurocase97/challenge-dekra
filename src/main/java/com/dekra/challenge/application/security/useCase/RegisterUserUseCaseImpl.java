package com.dekra.challenge.application.security.useCase;

import com.dekra.challenge.domain.security.exception.UserAlreadyExistsException;
import com.dekra.challenge.domain.security.modelo.User;
import com.dekra.challenge.domain.security.modelo.UserRole;
import com.dekra.challenge.domain.security.ports.in.usercase.AuthenticationUseCase;
import com.dekra.challenge.domain.security.ports.in.usercase.PasswordEncoderUseCase;
import com.dekra.challenge.domain.security.ports.in.usercase.RegisterUserUseCase;
import com.dekra.challenge.domain.security.ports.out.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegisterUserUseCaseImpl implements RegisterUserUseCase {


    private final UserRepository userRepository;
    private final PasswordEncoderUseCase passwordEncoder;
    private final AuthenticationUseCase authentication;


    @Override
    public String register(User user) {
        boolean existsByEmail = userRepository.existsByEmail(user.getEmail());

        if (existsByEmail) {
            throw new UserAlreadyExistsException("Email not available");
        }

        String encoded = passwordEncoder.encode(user.getPassword());

        User userSaved = User.builder()
                .email(user.getEmail())
                .password(encoded)
                .role(UserRole.USER)
                .build();

        userRepository.save(userSaved);

        return authentication.authenticate(user.getEmail(), user.getPassword());
    }
}




