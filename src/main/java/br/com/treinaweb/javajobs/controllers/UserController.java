package br.com.treinaweb.javajobs.controllers;

import br.com.treinaweb.javajobs.dto.JwtResponse;
import br.com.treinaweb.javajobs.dto.UserDTO;
import br.com.treinaweb.javajobs.models.User;
import br.com.treinaweb.javajobs.services.AuthenticationService;
import br.com.treinaweb.javajobs.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationService authenticationService;


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User create(@RequestBody @Valid UserDTO userDTO) {
        return userService.create(userDTO);

    }

    @PostMapping("/auth")
    public JwtResponse auth(@RequestBody @Valid UserDTO userDTO) {

        return authenticationService.createJwtResponse(userDTO);

    }

}
