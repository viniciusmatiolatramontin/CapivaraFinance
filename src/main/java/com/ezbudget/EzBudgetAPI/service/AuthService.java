package com.ezbudget.EzBudgetAPI.service;

import com.ezbudget.EzBudgetAPI.exception.UserAlreadyExistsException;
import com.ezbudget.EzBudgetAPI.model.UserAuth;
import com.ezbudget.EzBudgetAPI.model.UserRequestDTO;
import com.ezbudget.EzBudgetAPI.model.UserResponseDTO;
import com.ezbudget.EzBudgetAPI.repository.UserRepository;
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
            throw new UserAlreadyExistsException("Error: User already exists");
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
