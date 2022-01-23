package com.FC.Back.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "etiquetas")
public class Etiqueta implements Serializable {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String lenguaje;

    @JsonIgnore
    @ManyToMany(mappedBy = "etiquetas")
    public List<Alumno> alumnos = new ArrayList<>();

    public Etiqueta() {
    }

    public Etiqueta(Long id, String lenguaje) {
        this.id = id;
        this.lenguaje = lenguaje;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLenguaje() {
        return lenguaje;
    }

    public void setLenguaje(String lenguaje) {
        this.lenguaje = lenguaje;
    }

    public List<Alumno> getAlumnos() {
        return alumnos;
    }

    public void setAlumnos(List<Alumno> alumnos) {
        this.alumnos = alumnos;
    }

    @Override
    public String toString() {
        return "Etiqueta{" +
                "id=" + id +
                ", lenguaje='" + lenguaje + '\'' +
                '}';
    }

    @Serial
    private static final long serialVersionUID = 8799656478674716638L;
}
