package com.FC.Back.services;

import com.FC.Back.entities.Alumno;
import java.util.List;
import java.util.Optional;

public interface AlumnoService {

    Alumno saveAlumno(Alumno alumno);

    boolean deleteById(Long id);

    boolean deleteAll();

    List<Alumno> findAll();

    Optional<Alumno> findById(Long id);

    Alumno update(Alumno alumno);
}
