package com.upc.comebien.entidades;

import javax.persistence.*;

@Entity //Agnóstica
@Table(name = "users")
public class Usuario {
    @Id
    @Column(name = "usuario", length = 40, nullable = false)
    private String usuario;
    @Column(name = "contrasenia",length = 30,nullable = false)
    private String contrasenia;

    public Usuario() {
    }

    public Usuario(String usuario, String contrasenia) {
        this.usuario = usuario;
        this.contrasenia = contrasenia;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }
}
