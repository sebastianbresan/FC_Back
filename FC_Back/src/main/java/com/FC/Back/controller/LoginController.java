package com.FC.Back.controller;

import com.FC.Back.entities.Usuario;
import com.FC.Back.jwt.JwtUtil;
import com.FC.Back.payload.AutenticacionLogin;
import com.FC.Back.payload.AutenticacionResponse;
import com.FC.Back.services.MiUserDetailsService;
import com.FC.Back.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/")
@CrossOrigin(origins = "*")
public class LoginController {

    @Autowired
    private final UsuarioService usuarioService;
    @Autowired
    private final BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private final MiUserDetailsService miUserDetailsService;
    @Autowired
    private final JwtUtil jwtUtil;

    public LoginController(UsuarioService usuarioService, BCryptPasswordEncoder passwordEncoder, MiUserDetailsService miUserDetailsService, JwtUtil jwtUtil) {
        this.usuarioService = usuarioService;
        this.passwordEncoder = passwordEncoder;
        this.miUserDetailsService = miUserDetailsService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("login")
    public ResponseEntity<?> login(@RequestBody AutenticacionLogin autLogin) throws Exception {

        String email = autLogin.getEmail();
        String password = autLogin.getPassword();

        Usuario usuario = usuarioService.findByEmail(email);
        if (usuario == null) {
            throw new BadCredentialsException("Email no encontrado");
        }
        if (!passwordEncoder.matches(password, usuario.getPassword())) {
            throw new BadCredentialsException("Password incorrecto");
        }
        if (!usuario.isActivo()) {
            throw new DisabledException("Usuario inactivo");
        }
        new UsernamePasswordAuthenticationToken(email, null);
        final UserDetails userDetails = miUserDetailsService.loadUserByUsername(autLogin.getEmail());
        final String token = jwtUtil.creatToken(userDetails);
        return ResponseEntity.ok(new AutenticacionResponse(token, email));
    }
}



