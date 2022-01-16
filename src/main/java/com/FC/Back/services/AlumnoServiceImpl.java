package com.FC.Back.services;
import com.FC.Back.entities.Alumno;
import com.FC.Back.repositories.AlumnoRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class AlumnoServiceImpl implements AlumnoService{

    private final AlumnoRepository alumnoRepository;

    public AlumnoServiceImpl(AlumnoRepository alumnoRepository) {
        this.alumnoRepository = alumnoRepository;
    }

    @Override
    public Alumno saveAlumno(Alumno alumno) {
      return alumnoRepository.save(alumno);
    }

    @Override
    public boolean deleteById(Long id) {
        if(id == null || !alumnoRepository.existsById(id))
            return false;
        alumnoRepository.deleteById(id);
        return true;
    }

    @Override
    public boolean deleteAll() {
        alumnoRepository.deleteAll();
        return true;
    }

    @Override
    public List<Alumno> findAll() {
      return alumnoRepository.findAll();
    }

    @Override
    public Optional<Alumno> findById(Long id) {
        if (id == null || id <= 0)
            return Optional.empty();
        return alumnoRepository.findById(id);
    }

    @Override
    public Alumno update(Alumno alumno) {
        return alumnoRepository.save(alumno);
    }
}