package com.TPOBackend.TPOBackend.Repository.Entity;

import com.TPOBackend.TPOBackend.Service.CompraService;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;


import java.util.ArrayList;
import java.util.Date;

@Entity
@Data
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nombreUsuario;
    private String mail;
    private String contrasena;
    private Date fechaNacimiento;
    private String nombre;
    private String apellido;
    private ArrayList<CompraService> comprasUsuario;
    private boolean logeado;

    public Usuario (){}
    

    public Usuario(String nombreUsuario, String mail, String contrasena, Date fechaNacimiento, String nombre, String apellido) {
        this.nombreUsuario = nombreUsuario;
        this.mail = mail;
        this.contrasena = contrasena;
        this.fechaNacimiento = fechaNacimiento;
        this.nombre = nombre;
        this.apellido = apellido;
        this.logeado = false;
        this.comprasUsuario = new ArrayList<CompraService>();
    }
}
