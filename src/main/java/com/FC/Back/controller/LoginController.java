package com.FC.Back.controller;

import com.FC.Back.entities.Usuario;
import com.FC.Back.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/")
@CrossOrigin(origins = "*")
public class LoginController {

    @Autowired
    private final UsuarioService usuarioService;

    public LoginController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping("login")
    public ResponseEntity<String> login(@RequestBody Usuario usuario) {
        if(usuarioService.login(usuario.getEmail(), usuario.getPassword())){
            return ResponseEntity.ok(usuario.getEmail());
        }
        return ResponseEntity.badRequest().body("Error de autenticación");
    }
}


