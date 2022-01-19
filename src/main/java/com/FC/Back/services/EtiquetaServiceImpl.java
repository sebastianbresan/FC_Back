package com.FC.Back.services;

import com.FC.Back.entities.Etiqueta;
import com.FC.Back.repositories.EtiquetaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class EtiquetaServiceImpl implements EtiquetaService{

    @Autowired
    private final EtiquetaRepository etiquetaRepository;

    public EtiquetaServiceImpl(EtiquetaRepository etiquetaRepository) {
        this.etiquetaRepository = etiquetaRepository;
    }

    @Override
    public boolean findByLenguaje(String lenguaje) {
        for (Etiqueta e : etiquetaRepository.findAll()
        ) {
            if (Objects.equals(e.getLenguaje(), lenguaje)) {
                return true;
            }

        }return false;
    }

    @Override
    public Etiqueta findByLenguajeString(String lenguaje) {
        for (Etiqueta e : etiquetaRepository.findAll()
        ) {
            if (Objects.equals(e.getLenguaje(), lenguaje)) {
            return e;
            }
        }return  null;
    }
}
