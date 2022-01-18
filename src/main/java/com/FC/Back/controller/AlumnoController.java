package com.FC.Back.controller;

import com.FC.Back.entities.Alumno;
import com.FC.Back.services.AlumnoService;
import com.FC.Back.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/api/alumno/")
@CrossOrigin(origins = "*")
public class AlumnoController {

    @Autowired
    private final AlumnoService alumnoService;

    @Autowired
    private final UsuarioService usuarioService;

    public AlumnoController(AlumnoService alumnoService, UsuarioService usuarioService) {
        this.alumnoService = alumnoService;
        this.usuarioService = usuarioService;
    }

    @GetMapping("find/findbyid/{id}")
    public Optional<Alumno> findAlumno(@PathVariable("id") Long id) {
        return alumnoService.findById(id);
    }

    @GetMapping("find/findall")
    public List<Alumno> findAlumnos() {
        return alumnoService.findAll();
    }

    @PostMapping("/save/{email}")
    public Alumno saveUsuario(@PathVariable("email") String email, @RequestBody Alumno alumno) {
        alumno.setUsuario(usuarioService.findByEmail(email));
        return alumnoService.saveAlumno(alumno);
    }

    @PutMapping("update")
    public Alumno updateAlumno(@RequestBody Alumno alumno) {
        alumnoService.update(alumno);
        return alumno;
    }

    @DeleteMapping("/delete/deletebyid/{id}")
    public void deleteAlumno(@PathVariable("id") Long id) {
        alumnoService.deleteById(id);
    }

    @DeleteMapping("/delete/deleteall")
    public void deleteAlumnos() {
        alumnoService.deleteAll();
    }

    @PostMapping("/delete/deleteallbyuser")
    public void deleteallbyuser(@RequestBody List<String> email) {
        System.out.println(email);
        alumnoService.deleteAllByUser(email);
    }
}