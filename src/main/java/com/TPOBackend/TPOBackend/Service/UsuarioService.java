package com.TPOBackend.TPOBackend.Service;

import com.TPOBackend.TPOBackend.Repository.UserRepository;
import com.TPOBackend.TPOBackend.Repository.Entity.Usuario;
import org.springframework.stereotype.Service;
import java.nio.file.attribute.UserPrincipalNotFoundException;


import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.Date;
import java.util.Optional;

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
        /*Usuario usuarioExistente = userRepository.findByNombreUsuario(nombreUsuario).orElseThrow(() -> new Exception("El nombre de usuario ya esta ocupado"));
        Usuario mailExistente = userRepository.findByMail(mail).orElseThrow(() -> new Exception("El mail que ingreso ya esta en uso"));*/
        Optional<Usuario> usuarioExistente = userRepository.findByNombreUsuario(nombreUsuario);
        Optional<Usuario> usuarioExistente2 = userRepository.findByMail(mail);
        if(usuarioExistente2.isPresent() || usuarioExistente.isPresent()){
            throw new Exception("Usuario ya existe en la base de datos");
        }else{
            Usuario nuevoUsuario = new Usuario(nombreUsuario, mail, contrasena, fechaNacimiento, nombre, apellido);
            this.userRepository.save(nuevoUsuario);
            return nuevoUsuario;
        }

    }

    public boolean cambiarNombre(String nombre, int id){
        boolean cambio = false;
        Usuario usuarioExistente = userRepository.findById(id).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        if(usuarioExistente.getId() < 0){
            return cambio;
        }
        usuarioExistente.setNombre(nombre);
        this.userRepository.save(usuarioExistente);
        return !cambio;
    
    }

    public boolean cambiarMail(String mailNuevo, int id){
        boolean cambio = false;
        Usuario usuarioExistente = userRepository.findById(id).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        if(usuarioExistente.getId() < 0){
            return cambio;
        }
        usuarioExistente.setMail(mailNuevo);
        this.userRepository.save(usuarioExistente);
        return !cambio;
    }

    public boolean cambiarApellido(String apellidoNuevo, int id){
        boolean cambio = false;
        Usuario usuarioExistente = userRepository.findById(id).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        if(usuarioExistente.getId() < 0){
            return cambio;
        }
        usuarioExistente.setApellido(apellidoNuevo);
        this.userRepository.save(usuarioExistente);
        return !cambio;
    }

}