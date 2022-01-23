package com.FC.Back.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "alumnos")
public class Alumno implements Serializable {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String email;
    private String nombre;
    @Column(unique = true)
    private String telefono;
    private String ciudad;
    private String pais;
    private boolean traslado;

    @Enumerated(value = EnumType.STRING)
    private Presencialidad presencialidad;

    @JsonIgnore
    @ManyToOne(cascade = {CascadeType.PERSIST
    }, fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @ManyToMany(cascade = {CascadeType.PERSIST
    })
    @JoinTable(name = "alumnos_etiquetas",
            joinColumns = @JoinColumn(name = "alumno_id"),
            inverseJoinColumns = @JoinColumn(name = "etiqueta_id")
    )
    private List<Etiqueta> etiquetas = new ArrayList<>();

    public Alumno() {
    }

    public Alumno(Long id, String email, String nombre, String telefono, String ciudad, String pais, Presencialidad presencialidad, boolean traslado, List
<Etiqueta> etiquetas, Usuario usuario) {
        this.id = id;
        this.email = email;
        this.nombre = nombre;
        this.telefono = telefono;
        this.ciudad = ciudad;
        this.pais = pais;
        this.presencialidad = presencialidad;
        this.traslado = traslado;
        this.etiquetas = etiquetas;
        this.usuario = usuario;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public Presencialidad getPresencialidad() {
        return presencialidad;
    }

    public void setPresencialidad(Presencialidad presencialidad) {
        this.presencialidad = presencialidad;
    }

    public boolean isTraslado() {
        return traslado;
    }

    public void setTraslado(boolean traslado) {
        this.traslado = traslado;
    }

    public List
<Etiqueta> getEtiquetas() {
        return etiquetas;
    }

    public void setEtiquetas(List
<Etiqueta> etiquetas) {
        this.etiquetas = etiquetas;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public String toString() {
        String tras = "";
        if (traslado) {
            tras = "Si";
        } else {
            tras = "No";
        }
        return "Alumno{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", nombre='" + nombre + '\'' +
                ", telefono='" + telefono + '\'' +
                ", ciudad='" + ciudad + '\'' +
                ", pais='" + pais + '\'' +
                ", presencialidad=" + presencialidad +
                ", traslado=" + tras +
                '}';
    }

    @Serial
    private static final long serialVersionUID = 8799656478674716638L;

}