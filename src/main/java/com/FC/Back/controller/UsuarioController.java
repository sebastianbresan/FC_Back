package com.FC.Back.controller;

import com.FC.Back.entities.Usuario;
import com.FC.Back.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/usuario/")
@CrossOrigin(origins = "*")
public class UsuarioController {

    @Autowired
    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping("find/findbyid/{id}")
    public Optional<Usuario> findUsuario(@PathVariable("id") Long id) {
        return usuarioService.findById(id);
    }

    @GetMapping("find/findbyemail/{email}")
    public Usuario findUsuario(@PathVariable("email") String email) {
        return usuarioService.findByEmail(email);
    }

    @GetMapping("/find/findall")
    public List<Usuario> findUsuarios() {
        return usuarioService.findAll();
    }

    @PostMapping("/save")
    public Usuario saveUsuario(@RequestBody Usuario usuario) {
        usuarioService.saveUsuario(usuario);
        return usuario;
    }

    @PutMapping("/update")
    public Usuario updateUsuario(@RequestBody Usuario usuario) {
        usuarioService.update(usuario);
        return usuario;
    }

    @DeleteMapping("/delete/deletebyid/{id}")
    public void deleteUsuario(@PathVariable("id") Long id) {
        usuarioService.deleteById(id);
    }

    @DeleteMapping("/delete/deleteall")
    public void deleteUsuarios() {
        usuarioService.deleteAll();
    }
}