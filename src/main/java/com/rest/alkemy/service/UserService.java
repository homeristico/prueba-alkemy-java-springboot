package com.rest.alkemy.service;



import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rest.alkemy.entity.User;
import com.rest.alkemy.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired private UserRepository userRepository;

    public User save(User user){
        return userRepository.save(user);
    }

    public List<User> getAll(){
        return userRepository.findAll();
    }

    public Optional<User> obtenerUsuarioCredenciales(User user){

        Optional<User> obtenerUsuario = userRepository.findByEmail(user.getEmail());

        if(obtenerUsuario.isEmpty()){
            return null;
        }

        String passwordHashed = obtenerUsuario.get().getPassword();

        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);

        if(argon2.verify(passwordHashed, user.getPassword())){
            return obtenerUsuario;
        }

        return null;
    }








}





