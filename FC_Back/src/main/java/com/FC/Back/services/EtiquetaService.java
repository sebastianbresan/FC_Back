package com.FC.Back.services;

import com.FC.Back.entities.Etiqueta;

public interface EtiquetaService {

    boolean findByLenguaje(String lenguaje);

    Etiqueta findByLenguajeString(String lenguaje);
}
