package com.FC.Back.services;

import com.FC.Back.entities.Usuario;
import com.FC.Back.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Permite implementar el metodo para cargar los datos de un usuario especifico atraves de una BD.
 */
@Service
public class MiUserDetailsService implements UserDetailsService {

    @Autowired
    private final UsuarioRepository usuarioRepository;

    public MiUserDetailsService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    /**
     * Cargamos los datos obtenidos de la consulta hacia la BD y retornamos un objeto <b>UserDetails</b>
     * como nuestra clase <b>MiUserDetails</b> lo implementa la podemos usar, en su constructor le pasamos el
     * usuario de la BD para poblarlo.
     * @param email nombre del usuario a buscar
     * @return UserDetails que poblara por medio de <b>Usuario</b>
     * @throws UsernameNotFoundException Si no encuentra el registro en la BD.
     */
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Usuario> usuario = usuarioRepository.findByEmail(email);
        usuario.orElseThrow(() -> new UsernameNotFoundException("No se encontro el usuario "+ email
                +" en la BD"));

        return usuario.map(MiUserDetails::new).get();

    }

}
