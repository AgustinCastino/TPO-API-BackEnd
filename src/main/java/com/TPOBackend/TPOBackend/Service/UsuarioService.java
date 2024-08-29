package com.TPOBackend.TPOBackend.Service;

import com.TPOBackend.TPOBackend.Repository.UserRepository;
import com.TPOBackend.TPOBackend.Repository.Entity.Usuario;
import org.springframework.stereotype.Service;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.Date;

@Service
public class UsuarioService {

    private UserRepository userRepository;

    public UsuarioService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserRepository getUserRepository() {
        return userRepository;
    }

    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean iniciarSesion(String nombreUsuario, String contrasena) throws UserPrincipalNotFoundException {
        boolean flag = false;
        Usuario usuarioExistente = userRepository.encontrarPorNombreUusario(nombreUsuario).orElseThrow(() -> new UserPrincipalNotFoundException("Usuario no econtrado"));

        if (contrasena.equals(usuarioExistente.getContrasena())){
            flag = true;
            usuarioExistente.setLogeado(true);
        }

        return flag;

    }

    public Usuario registrarUsuario(String nombreUsuario, String mail, String contrasena, Date fechaNacimiento, String nombre, String apellido) throws Exception {
        Usuario usuarioExistente = userRepository.encontrarPorNombreUusario(nombreUsuario).orElseThrow(() -> new Exception("Ocurrio un error"));
        Usuario mailExistente = userRepository.encontrarPorMail(mail).orElseThrow(() -> new Exception("Ocurrio un error"));

        Usuario nuevoUsuario = new Usuario(nombreUsuario, mail, contrasena, fechaNacimiento, nombre, apellido);
        this.userRepository.setUsuarios(nuevoUsuario);
        return nuevoUsuario;

    }
}
