package com.capivarafinance.CapivaraFinance.service;

import com.capivarafinance.CapivaraFinance.model.UserRequestDTO;
import com.capivarafinance.CapivaraFinance.model.UserResponseDTO;
import com.capivarafinance.CapivaraFinance.repository.UserRepository;
import com.capivarafinance.CapivaraFinance.exception.UserAlreadyExistsException;
import com.capivarafinance.CapivaraFinance.model.UserAuth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UserRepository repo;

    @Autowired
    private BCryptPasswordEncoder bCrypt;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JWTService jwtService;

    public UserAuth userRegister(UserAuth user) throws UserAlreadyExistsException {
        if (repo.findByUsername(user.getUsername()) != null) {
            throw new UserAlreadyExistsException("Erro: O nome de usuário já  está em uso");
        }

        String encryptedPassword = bCrypt.encode(user.getPassword());
        UserAuth newUser = new UserAuth(user.getUsername(), encryptedPassword, user.getEmail(), user.getBalance());

        return repo.save(newUser);
    }

    public UserResponseDTO userLogin(UserRequestDTO userRequestDTO) {
        UsernamePasswordAuthenticationToken userAndPassword = new UsernamePasswordAuthenticationToken(userRequestDTO.getUsername(), userRequestDTO.getPassword());
        Authentication auth = authenticationManager.authenticate(userAndPassword);

        String token = jwtService.generateToken((UserAuth) auth.getPrincipal());

        return new UserResponseDTO(userRequestDTO.getUsername(), token);
    }

}
