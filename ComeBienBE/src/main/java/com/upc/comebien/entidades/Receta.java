package com.upc.comebien.entidades;

import javax.persistence.*;

@Entity
@Table(name = "recetas")
public class Receta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "nombre", length = 40, nullable = false)
    private String nombre;
    @Column(name = "descripcion",length = 30,nullable = false)
    private String descripcion;
    @Column(name = "valoracion", nullable = false)
    private Integer valoracion;
    @Column(name = "calorias", nullable = false)
    private Integer calorias;
    @Column(name = "link",nullable = false)
    private String link;

    public Receta() {
    }

    public Receta(String nombre, String descripcion, Integer valoracion, Integer calorias, String link) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.valoracion = valoracion;
        this.calorias = calorias;
        this.link = link;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getValoracion() {
        return valoracion;
    }

    public void setValoracion(Integer valoracion) {
        this.valoracion = valoracion;
    }

    public Integer getCalorias() {
        return calorias;
    }

    public void setCalorias(Integer calorias) {
        this.calorias = calorias;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
