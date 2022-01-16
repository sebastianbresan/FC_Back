package com.FC.Back.repositories;

import com.FC.Back.entities.Alumno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface  AlumnoRepository extends JpaRepository<Alumno, Long> {

}
