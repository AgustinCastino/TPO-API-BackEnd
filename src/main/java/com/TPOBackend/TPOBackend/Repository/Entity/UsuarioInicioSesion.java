package com.TPOBackend.TPOBackend.Repository.Entity;

public class UsuarioInicioSesion {
    private String identificador;
    private String contrasena;

    public UsuarioInicioSesion(String identificador, String contrsena) {
        this.identificador = identificador;
        this.contrasena = contrsena;
    }

    public String getIdentificador() {
        return identificador;
    }

    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }
}

