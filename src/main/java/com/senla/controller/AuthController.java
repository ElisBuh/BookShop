package com.senla.controller;

import com.senla.api.service.IUserService;
import com.senla.model.User;
import com.senla.model.dto.UserDto;
import com.senla.util.jwt.JwtProvider;
import com.senla.util.jwt.JwtRequest;
import com.senla.util.jwt.JwtResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class AuthController {
    private final IUserService userService;
    private final JwtProvider jwtProvider;

    public AuthController(IUserService userService, JwtProvider jwtProvider) {
        this.userService = userService;
        this.jwtProvider = jwtProvider;
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody @Valid UserDto userDto) {
        User u = new User();
        u.setPassword(userDto.getPassword());
        u.setLogin(userDto.getLogin());
        userService.saveUser(u, 100001);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/auth")
    public ResponseEntity<JwtResponse> auth(@RequestBody JwtRequest request) {
        User user = userService.findByUserLoginAndPassword(request.getLogin(), request.getPassword());
        String token = jwtProvider.generateToken(user.getLogin());
        return new ResponseEntity<>(new JwtResponse(token), HttpStatus.OK);
    }

    @PostMapping("/controlPanel/{id}")
    public ResponseEntity<?> changeRole(@PathVariable(name = "id") Integer id,
                                        @RequestParam(name = "idRole") Integer idRole) {
        User user = userService.get(id);
        userService.changeRole(user.getLogin(), idRole);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
