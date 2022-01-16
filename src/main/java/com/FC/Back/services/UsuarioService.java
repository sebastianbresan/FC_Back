package com.FC.Back.services;

import com.FC.Back.entities.Usuario;

import java.util.List;
import java.util.Optional;

public interface UsuarioService {

    Usuario saveUsuario(Usuario usuario);

    Usuario findByEmail(String email);

    Usuario update(Usuario usuario);

    List<Usuario> findAll();

    Optional<Usuario> findById(Long id);

    boolean deleteById(Long id);

    boolean deleteAll();

    boolean login(String email, String password);




}
