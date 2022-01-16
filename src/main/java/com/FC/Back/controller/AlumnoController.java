package com.FC.Back.controller;

import com.FC.Back.entities.Alumno;
import com.FC.Back.services.AlumnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/alumno/")
@CrossOrigin(origins = "*")
public class AlumnoController {

    @Autowired
    private final AlumnoService alumnoService;

    public AlumnoController(AlumnoService alumnoService) {
        this.alumnoService = alumnoService;
    }

    @GetMapping("find/findbyid/{id}")
    public Optional<Alumno> findAlumno(@PathVariable("id") Long id) {
        return alumnoService.findById(id);
    }

    @GetMapping("find/findall")
    public List<Alumno> findAlumnos() {
        return alumnoService.findAll();
    }

    @PostMapping("save")
    public Alumno saveAlumno(@RequestBody Alumno alumno) {
        alumnoService.saveAlumno(alumno);
        return alumno; }

    @PutMapping("update")
    public Alumno updateAlumno(@RequestBody Alumno alumno) {
        alumnoService.update(alumno);
        return alumno; }

    @DeleteMapping("/delete/deletebyid/{id}")
    public void deleteAlumno(@PathVariable("id") Long id) {
        alumnoService.deleteById(id);
    }

    @DeleteMapping("/delete/deleteall")
    public void deleteAlumnos() {
        alumnoService.deleteAll();
    }
}