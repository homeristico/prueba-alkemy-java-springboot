package com.rest.alkemy.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.rest.alkemy.entity.User;
import com.rest.alkemy.service.MailService;
import com.rest.alkemy.service.UserService;
import com.rest.alkemy.utils.JWTUtil;

import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import io.swagger.v3.oas.annotations.Operation;

import java.io.IOException;
import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired UserService userService;

    @Autowired JWTUtil jwtUtil;
    
    @Autowired MailService mailService;

    @Operation(summary = "recibe email y password, si es correcto returna un token")
    @PostMapping("/login")
    public String login(@RequestBody User user){
        Optional<User> usuarioLogeado = userService.obtenerUsuarioCredenciales(user);
        if(usuarioLogeado.get() != null){
            String tokenJwt = jwtUtil.create(String.valueOf(usuarioLogeado.get().getId()), usuarioLogeado.get().getEmail());
            return tokenJwt;
        }
        return "fail";
    }
    
    @Operation(summary = "almacena un usuario nuevo en la base de datos")
    @PostMapping("/register")
    public User saveUser(@RequestBody User user) throws IOException {
    	Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
    	String hash = argon2.hash(1, 1024, 1, user.getPassword());
    	user.setPassword(hash);
    	mailService.sendEmail(user.getEmail());
    	return userService.save(user);
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
