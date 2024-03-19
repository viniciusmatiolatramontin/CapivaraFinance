package com.ezbudget.EzBudgetAPI.controller;

import com.ezbudget.EzBudgetAPI.exception.UserAlreadyExistsException;
import com.ezbudget.EzBudgetAPI.model.UserAuth;
import com.ezbudget.EzBudgetAPI.model.UserRequestDTO;
import com.ezbudget.EzBudgetAPI.model.UserResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.ezbudget.EzBudgetAPI.service.AuthService;

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
