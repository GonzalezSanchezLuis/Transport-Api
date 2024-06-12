package com.transportapi.controller;

import java.security.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.transportapi.model.entity.User;
import com.transportapi.model.jwt.JwtRequest;
import com.transportapi.model.jwt.JwtResponse;
import com.transportapi.model.jwt.JwtUtils;
import com.transportapi.services.impl.UserDetailsImpl;
import com.transportapi.cofig.Documentation.ApiDocumentationUser;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/")
@Tag(name = "Authetication", description = "Controlador de autenticación")
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsImpl userDetailImpl;

    @Autowired
    private JwtUtils jwtUtils;

    @PostMapping("login")
    @Operation( summary = "Genera el token de autenticación", description = "Genaración de token")
    @ApiDocumentationUser.UserApiResponses

    public ResponseEntity<?> generateToken(@RequestBody JwtRequest jwtRequest) throws Exception {
        try {
            // Autenticar utilizando el correo electrónico como nombre de usuario
            authenticate(jwtRequest.getUserEmail(), jwtRequest.getPassword());
        } catch (Exception exception) {
            exception.printStackTrace();
            throw new Exception("CREDENCIALES INCORRECTAS: " + exception.getMessage());
        }

        UserDetails userDetails = this.userDetailImpl.loadUserByUsername(jwtRequest.getUserEmail());
        String token = this.jwtUtils.generateToken(userDetails);
        return ResponseEntity.ok(new JwtResponse(token));
    }

    private void authenticate(String email, String password) throws Exception {
        try {
            // Autenticar utilizando el correo electrónico como nombre de usuario
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
        } catch (DisabledException disabledException) {
            throw new Exception("USUARIO DESHABILITADO: " + disabledException.getMessage());
        } catch (BadCredentialsException badCredentialsException) {
            throw new Exception("CREDENCIALES INCORRECTAS: " + badCredentialsException.getMessage());
        }
    }

    @GetMapping("/current-user")
    @Operation( summary = "Encuentra el usuario con la sesion actual", description = "Usuario con una sesion activa")
    @ApiDocumentationUser.UserApiResponses

    public User currentUser(Principal principal){
        return (User) this.userDetailImpl.loadUserByUsername(principal.getName());
    }
}
