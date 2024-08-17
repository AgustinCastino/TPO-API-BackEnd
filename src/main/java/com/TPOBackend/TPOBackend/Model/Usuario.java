package com.TPOBackend.TPOBackend.Model;

import java.util.Date;

public class Usuario {
    private String nombreUsuario;
    private String mail;
    private String contrasena;
    private Date fechaNacimiento;
    private String nombre;
    private String apellido;
    private boolean logeado;

    public Usuario(String nombreUsuario, String mail, String contrasena, Date fechaNacimiento, String nombre, String apellido) {
        this.nombreUsuario = nombreUsuario;
        this.mail = mail;
        this.contrasena = contrasena;
        this.fechaNacimiento = fechaNacimiento;
        this.nombre = nombre;
        this.apellido = apellido;
        this.logeado = false;
    }


    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public boolean isLogeado() {
        return logeado;
    }

    public void setLogeado(boolean logeado) {
        this.logeado = logeado;
    }

    public boolean validarContrasena(String contrasena){
        return this.contrasena.equals(contrasena);
    }

    public boolean iniciarSesion(String identificador, String contrasena){
        if (this.mail.equals(identificador) || this.nombreUsuario.equals(identificador) && this.contrasena.equals(contrasena)) {
            this.logeado = true;
            return true;
        }
        return false;
    }

    public void cerrarSesion(){
        this.logeado=false;
    }


}
