package com.FC.Back.repositories;

import com.FC.Back.entities.Etiqueta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface  EtiquetaRepository extends JpaRepository<Etiqueta, Long> {

}
