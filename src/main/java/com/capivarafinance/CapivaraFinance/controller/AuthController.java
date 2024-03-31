package com.capivarafinance.CapivaraFinance.controller;

import com.capivarafinance.CapivaraFinance.model.UserRequestDTO;
import com.capivarafinance.CapivaraFinance.model.UserResponseDTO;
import com.capivarafinance.CapivaraFinance.service.AuthService;
import com.capivarafinance.CapivaraFinance.exception.UserAlreadyExistsException;
import com.capivarafinance.CapivaraFinance.model.UserAuth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService service;

    @PostMapping("/register")
    public ResponseEntity<UserAuth> userRegister(@RequestBody UserAuth user) throws UserAlreadyExistsException {
        return new ResponseEntity<UserAuth>(service.userRegister(user), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<UserResponseDTO> userLogin(@RequestBody UserRequestDTO userRequestDTO) {
        return new ResponseEntity<UserResponseDTO>(service.userLogin(userRequestDTO), HttpStatus.OK);
    }

}
