package com.TPOBackend.TPOBackend.Model;

import java.util.ArrayList;
import java.util.Date;

public class Autenticacion {
    private ArrayList<Usuario> usuarios;

    public Autenticacion(){
        usuarios = new ArrayList<Usuario>();
    }

    public Usuario iniciarSesion(String identificador, String contrasena){
        for (Usuario usuario : usuarios) {
            if(usuario.iniciarSesion(identificador, contrasena)){
                return usuario;
            }
        }
        return null;
    }

    public Usuario registrarUsuario(String nombreUsuario, String mail, String contrasena, Date fechaNacimiento, String nombre, String apellido) {
        for (Usuario usuario : usuarios) {
            if (usuario.getNombreUsuario().equals(nombreUsuario) || usuario.getMail().equals(mail)) {
                return null;
            }
        }
        Usuario nuevoUsuario = new Usuario(nombreUsuario, mail, contrasena, fechaNacimiento, nombre, apellido);
        usuarios.add(nuevoUsuario);
        return nuevoUsuario;
        
    }

    public void cerrarSesion(Usuario usuario){
        usuario.cerrarSesion();
    }

    public ArrayList<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(ArrayList<Usuario> usuarios) {
        this.usuarios = usuarios;
    }
}
