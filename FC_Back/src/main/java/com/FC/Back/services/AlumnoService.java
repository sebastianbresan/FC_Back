package com.FC.Back.services;

import com.FC.Back.entities.Alumno;
import java.util.List;
import java.util.Optional;

public interface AlumnoService {

    Alumno saveAlumno(Alumno alumno);

    Alumno deleteById(Long id);

    void deleteAllByUser(String email);

    void deleteByEmail(String email);

    boolean deleteAll();

    List<Alumno> findAll();

    List<Alumno> findAllWithoutUser();

    Optional<Alumno> findById(Long id);

    Alumno update(Alumno alumno);

    Alumno updateEmail(String email, Long id);
}
