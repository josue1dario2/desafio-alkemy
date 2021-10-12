package com.challenge.alkemy.auth.controller;


import com.challenge.alkemy.auth.dto.AuthenticationRequest;
import com.challenge.alkemy.auth.dto.AuthenticationResponse;
import com.challenge.alkemy.auth.dto.UsuarioDto;
import com.challenge.alkemy.auth.service.JwtUtils;
import com.challenge.alkemy.auth.service.UserDetailsCustomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping(path = "/auth")
public class UserAuthController {
    @Autowired
    private UserDetailsCustomService userDetailsService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtUtils jwtTokenUtil;

    @PostMapping("/singup")
    public ResponseEntity<?> singUp( @RequestBody UsuarioDto user) throws Exception {

        this.userDetailsService.save(user);

        return ResponseEntity.status(CREATED).build();
    }
    @PostMapping("/singin")
    public ResponseEntity<?> singIn(@RequestBody AuthenticationRequest authRequest) throws Exception {

        UserDetails userDetails;
        try {
            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
            );
            userDetails = (UserDetails) auth.getPrincipal();
        } catch (BadCredentialsException e) {
            throw new Exception("Incorrect username or password", e);
        }

        final String jwt = jwtTokenUtil.generateToken(userDetails);

        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }
}
