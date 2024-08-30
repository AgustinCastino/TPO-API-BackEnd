package com.TPOBackend.TPOBackend.Service;

import com.TPOBackend.TPOBackend.Repository.UserRepository;
import com.TPOBackend.TPOBackend.Repository.Entity.Usuario;
import org.springframework.stereotype.Service;
import java.nio.file.attribute.UserPrincipalNotFoundException;


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

    public Usuario iniciarSesion(String identificador, String contrasena) throws Exception {
        Usuario usuarioExistente = userRepository.findByIdentificadorYContrasena(identificador, contrasena).orElseThrow(() -> new Exception("El usuario o la contraseña son incorrectos"));
        return usuarioExistente;
    }

    
    public Usuario registrarUsuario(String nombreUsuario, String mail, String contrasena, Date fechaNacimiento, String nombre, String apellido) throws Exception {
        Usuario usuarioExistente = userRepository.findByNombreUsuario(nombreUsuario).orElseThrow(() -> new Exception("El nombre de usuario ya esta ocupado"));
        Usuario mailExistente = userRepository.findByMail(mail).orElseThrow(() -> new Exception("El mail que ingreso ya esta en uso"));
        Usuario nuevoUsuario = new Usuario(nombreUsuario, mail, contrasena, fechaNacimiento, nombre, apellido);
        this.userRepository.save(nuevoUsuario);
        return nuevoUsuario;

    }
}