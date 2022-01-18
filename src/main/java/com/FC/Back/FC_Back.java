package com.FC.Back;

import com.FC.Back.entities.Alumno;
import com.FC.Back.entities.Etiqueta;
import com.FC.Back.entities.Presencialidad;
import com.FC.Back.entities.Usuario;
import com.FC.Back.repositories.AlumnoRepository;
import com.FC.Back.repositories.UsuarioRepository;
import com.FC.Back.services.UsuarioService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class FC_Back {

    public static void main(String[] args) {

        ApplicationContext context = SpringApplication.run(FC_Back.class, args);

        AlumnoRepository alumnoRepository = context.getBean(AlumnoRepository.class);

        UsuarioRepository usuarioRepository = context.getBean(UsuarioRepository.class);

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        Set<Etiqueta> etiquetas1 = new HashSet<>();

        Set<Etiqueta> etiquetas2= new HashSet<>();

        etiquetas1.add(new Etiqueta(null, "REACT"));
        etiquetas1.add(new Etiqueta(null, "SPRING"));
        etiquetas1.add(new Etiqueta(null, "JAVA"));
        etiquetas1.add(new Etiqueta(null, "LARAVEL"));
        etiquetas2.add(new Etiqueta(null, "PYTHON"));

        Usuario u1 = new Usuario( null, "admin1@admin.com", passwordEncoder.encode("123456"));
        Usuario u2 = new Usuario( null, "admin2@admin.com", passwordEncoder.encode("123456"));

        usuarioRepository.save(u1);
        usuarioRepository.save(u2);

        alumnoRepository.save(new Alumno(1L, "sebastian@gmail.com", "Sebastian Zapata", "691888888", "Barcelona", "España", Presencialidad.PRESENCIAL,true, etiquetas1, u1));
        alumnoRepository.save(new Alumno(2L, "rodrigo@gmail.com", "Rodrigo Lucena", "691886655", "Lisboa", "Portugal", Presencialidad.REMOTO,false, etiquetas1, u1));
        alumnoRepository.save(new Alumno(3L, "ramiro@gmail.com", "Ramiro Lagaña", "694448888", "Zaragoza", "España", Presencialidad.PRESENCIAL,false, etiquetas1, u1));
        alumnoRepository.save(new Alumno(4L, "franc@gmail.com", "Franc Touche", "234886655", "Montpellier", "Francia", Presencialidad.MIXTO,false, etiquetas2, u1));
        alumnoRepository.save(new Alumno(5L, "robert@gmail.com", "Robert Lewis", "3455233113", "Londres", "Inglaterra", Presencialidad.REMOTO,true, etiquetas1, u1));
        alumnoRepository.save(new Alumno(6L, "carlos@gmail.com", "Carlos De la Vega", "645499232", "Oviedo", "España", Presencialidad.PRESENCIAL,false, etiquetas1, u2));
        alumnoRepository.save(new Alumno(7L, "anderson@gmail.com", "Samuel Andersen", "653420200", "Oslo", "Suecia", Presencialidad.MIXTO,false, etiquetas1, u2));
        alumnoRepository.save(new Alumno(8L, "juanpedro@gmail.com", "Juan Martin Lazaro", "33445523113", "San Pablo", "Brasil", Presencialidad.REMOTO,true, etiquetas2, u2));
    }
}
