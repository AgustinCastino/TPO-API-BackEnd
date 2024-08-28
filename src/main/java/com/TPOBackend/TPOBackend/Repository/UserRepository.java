package com.TPOBackend.TPOBackend.Repository;


import com.TPOBackend.TPOBackend.Entity.Usuario;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

@Repository
public class UserRepository {
    private final ArrayList<Usuario> usuarios = new ArrayList<>();

    public UserRepository(){
        usuarios.add(new Usuario("Fanta", "santino@gmail.com", "1234", new Date("2002-2-2"), "Santino", "Floridia"));
        usuarios.add(new Usuario("Fanta123", "gonzalo@gmail.com", "", new Date("2002-2-2"), "Santino", "Floridia"));

    }

    public ArrayList<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(Usuario usuario) {
        this.usuarios.add(usuario);
    }

    public Optional<Usuario> encontrarPorNombreUusario(String nombreUsuario){
        return usuarios.stream().filter(usuario -> usuario.getNombreUsuario().equals(nombreUsuario)).findFirst();
    }
    public Optional<Usuario> encontrarPorMail(String mail){
        return usuarios.stream().filter(usuario -> usuario.getMail().equals(mail)).findFirst();
    }

    public String encontrarContrasenaPorUsuario(String nombreUsuario){
        for (Usuario usuario : usuarios) {
            if (usuario.getNombreUsuario().equals(nombreUsuario)){
                return usuario.getContrasena();
            }
        }
        return null;

    }

}
