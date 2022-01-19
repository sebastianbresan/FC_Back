package com.FC.Back.dto;


import com.FC.Back.entities.Usuario;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Esta clase proporciona la implementacion para establecer informacion del usuario.
 * Almacenando información del usuario que más tarde se encapsula en objetos de autenticación
 */
public class MiUserDetails implements UserDetails {
    /* ~ Propiedades
    ==================================== */
    private final String email;
    private final String password;
    private final boolean active;

    /* ~ Metodos
    ==================================== */
    public MiUserDetails(Usuario usuario){
        this.email = usuario.getEmail();
        this.password = usuario.getPassword();
        this.active = usuario.isActivo();
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return this.active;
    }
}