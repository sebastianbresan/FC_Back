package com.FC.Back;

import com.FC.Back.entities.Alumno;
import com.FC.Back.entities.Etiqueta;
import com.FC.Back.entities.Presencialidad;
import com.FC.Back.entities.Usuario;
import com.FC.Back.repositories.AlumnoRepository;
import com.FC.Back.repositories.EtiquetaRepository;
import com.FC.Back.repositories.UsuarioRepository;
import com.FC.Back.services.UsuarioService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@SpringBootApplication
public class FC_Back {

    public static void main(String[] args) {

        ApplicationContext context = SpringApplication.run(FC_Back.class, args);

        AlumnoRepository alumnoRepository = context.getBean(AlumnoRepository.class);

        UsuarioRepository usuarioRepository = context.getBean(UsuarioRepository.class);

        EtiquetaRepository etiquetaRepository = context.getBean(EtiquetaRepository.class);

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        Etiqueta etiqueta1 = new Etiqueta(null,"REACT");
        Etiqueta etiqueta2 = new Etiqueta(null,"SPRING");
        Etiqueta etiqueta3 = new Etiqueta(null,"GITHUB");
        Etiqueta etiqueta4 = new Etiqueta(null,"JAVA");
        Etiqueta etiqueta5 = new Etiqueta(null,"TYPESCRIPT");
        Etiqueta etiqueta6 = new Etiqueta(null,"ANGULAR");

        List<Etiqueta> etiquetas1 = new ArrayList<>(List.of(etiqueta1, etiqueta3, etiqueta5));
        List<Etiqueta> etiquetas2 = new ArrayList<>(List.of(etiqueta3, etiqueta6, etiqueta4));
        List<Etiqueta> etiquetas3 = new ArrayList<>(List.of(etiqueta2, etiqueta3, etiqueta4, etiqueta6));
        List<Etiqueta> etiquetas4 = new ArrayList<>(List.of(etiqueta1, etiqueta2, etiqueta4, etiqueta5));

        etiquetaRepository.saveAll(etiquetas1);
        etiquetaRepository.saveAll(etiquetas2);
        etiquetaRepository.saveAll(etiquetas3);
        etiquetaRepository.saveAll(etiquetas4);

        Usuario u1 = new Usuario( null, "admin1@admin.com", passwordEncoder.encode("123456"));
        Usuario u2 = new Usuario( null, "admin2@admin.com", passwordEncoder.encode("123456"));
        Usuario u3 = new Usuario( null, "admin3@admin.com", passwordEncoder.encode("123456"));

        u1.setActivo(true);
        u2.setActivo(true);
        u3.setActivo(true);

        usuarioRepository.saveAll(List.of(u1,u2,u3));

        alumnoRepository.save(new Alumno(1L, "sebastian@gmail.com", "Sebastian Zapata", "691888888", "Barcelona", "España", Presencialidad.PRESENCIAL,true, etiquetas1, u1));
        alumnoRepository.save(new Alumno(2L, "rodrigo@gmail.com", "Rodrigo Lucena", "691886655", "Lisboa", "Portugal", Presencialidad.REMOTO,false, etiquetas4, u1));
        alumnoRepository.save(new Alumno(3L, "ramiro@gmail.com", "Ramiro Lagaña", "694448888", "Zaragoza", "España", Presencialidad.PRESENCIAL,false, etiquetas2, u1));
        alumnoRepository.save(new Alumno(4L, "franc@gmail.com", "Franc Touche", "234886655", "Montpellier", "Francia", Presencialidad.MIXTO,false, etiquetas3, u1));
        alumnoRepository.save(new Alumno(5L, "robert@gmail.com", "Robert Lewis", "3455233113", "Londres", "Inglaterra", Presencialidad.REMOTO,true, etiquetas1, u1));
        alumnoRepository.save(new Alumno(6L, "carlos@gmail.com", "Carlos De la Vega", "645499232", "Oviedo", "España", Presencialidad.PRESENCIAL,false, etiquetas3, u2));
        alumnoRepository.save(new Alumno(7L, "anderson@gmail.com", "Samuel Andersen", "653420200", "Oslo", "Suecia", Presencialidad.MIXTO,false, etiquetas4, u2));
        alumnoRepository.save(new Alumno(8L, "juanpedro@gmail.com", "Juan Martin Lazaro", "33445523113", "San Pablo", "Brasil", Presencialidad.REMOTO,true, etiquetas2, u2));

    }
}
