package com.FC.Back.services;

import com.FC.Back.entities.Alumno;
import com.FC.Back.entities.Etiqueta;
import com.FC.Back.repositories.AlumnoRepository;
import com.FC.Back.repositories.EtiquetaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class AlumnoServiceImpl implements AlumnoService {

    @Autowired
    private final AlumnoRepository alumnoRepository;
    @Autowired
    private final EtiquetaRepository etiquetaRepository;
    @Autowired
    private final EtiquetaService etiquetaService;


    public AlumnoServiceImpl(AlumnoRepository alumnoRepository, EtiquetaRepository etiquetaRepository, EtiquetaService etiquetaService) {
        this.alumnoRepository = alumnoRepository;
        this.etiquetaRepository = etiquetaRepository;
        this.etiquetaService = etiquetaService;
    }

    @Override
    public Alumno saveAlumno(Alumno alumno) {

        List<Long> indice = new ArrayList<>();

        List<Etiqueta> etiquetas = new ArrayList<>();

        for (Etiqueta ea : alumno.getEtiquetas()
        ) {
            if (etiquetaService.findByLenguaje(ea.getLenguaje())) {
                indice.add(etiquetaService.findByLenguajeString(ea.getLenguaje()).getId());
            }
            else etiquetas.add(ea);
        }
        alumno.getEtiquetas().clear();

        for (Long in : indice
        ) {
            alumno.getEtiquetas().add(etiquetaRepository.getById(in));
        }
        etiquetaRepository.saveAll(etiquetas);
        alumno.getEtiquetas().addAll(etiquetas);

        return alumnoRepository.save(alumno);
    }

    @Override
    public boolean deleteById(Long id) {
        if (id == null || !alumnoRepository.existsById(id))
            return false;
        alumnoRepository.deleteById(id);
        return true;
    }

    @Override
    public void deleteAllByUser(String email) {
        for (Alumno alumno : alumnoRepository.findAll()
        ) {
            if (alumno.getUsuario() != null && alumno.getUsuario().getEmail().equals(email)) {
                alumno.setUsuario(null);
                alumnoRepository.save(alumno);
            }
        }
    }

    @Override
    public void deleteByEmail(String email) {
        for (Alumno alumno : alumnoRepository.findAll()
        ) {
            if (((char) 34 + alumno.getEmail() + (char) 34).equals(email)) {
                System.out.println("alumno");
                alumnoRepository.delete(alumno);

            }
        }
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
