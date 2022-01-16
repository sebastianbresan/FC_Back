package com.FC.Back.services;

import com.FC.Back.entities.Alumno;
import com.FC.Back.entities.Usuario;
import com.FC.Back.repositories.UsuarioRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    private final UsuarioRepository usuarioRepository;

    public UsuarioServiceImpl(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public Usuario saveUsuario(Usuario usuario) {
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        return usuarioRepository.save(usuario);
    }

    @Override
    public Usuario addAlumno(String email, Alumno alumno) {
        for (Usuario usuario : usuarioRepository.findAll()) {
            if (Objects.equals(usuario.getEmail(), email)) {
                usuario.getAlumnos().add(alumno);
                usuarioRepository.save(usuario);
                return usuario;
            }
        }
        return null;
    }

    @Override
    public boolean deleteById(Long id) {
        if (id == null || !usuarioRepository.existsById(id))
            return false;
        usuarioRepository.deleteById(id);
        return true;
    }

    @Override
    public boolean deleteAll() {
        usuarioRepository.deleteAll();
        return true;
    }

    @Override
    public List<Usuario> findAll() {
        return usuarioRepository.findAll();
    }


    @Override
    public Optional<Usuario> findById(Long id) {
        if (id == null || id <= 0)
            return Optional.empty();
        return usuarioRepository.findById(id);
    }

    @Override
    public Usuario update(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    @Override
    public boolean login(String email, String password) {
        for (Usuario usuario : usuarioRepository.findAll()
        ) {

            if (Objects.equals(email, usuario.getEmail()) && passwordEncoder.matches(password, usuario.getPassword())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Usuario findByEmail(String email) {
        for (Usuario usuario : usuarioRepository.findAll()
        ) {
            if (Objects.equals(usuario.getEmail(), email)) {
                return usuario;
            }
        }
        return null;
    }
}
