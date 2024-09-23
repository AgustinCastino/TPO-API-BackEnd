package com.TPOBackend.TPOBackend.Service;

import com.TPOBackend.TPOBackend.Repository.Entity.CambioContraseñaDTO;
import com.TPOBackend.TPOBackend.Repository.UserRepository;
import com.TPOBackend.TPOBackend.Repository.Entity.Usuario;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.nio.file.attribute.UserPrincipalNotFoundException;


import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    public UsuarioService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    /*public Usuario actualizar(CambioContraseñaDTO cambioContraseñaDTO){


    }*/
    public List<Usuario> listarUsuarios(){
        return userRepository.findAll();
    }

    public Usuario eliminar(int id) throws Exception{
        Usuario usuarioExistente = userRepository.findById(id).orElseThrow(() -> new Exception("Usuario no encontrado en la base de datos"));
        this.userRepository.delete(usuarioExistente);
        return usuarioExistente;
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

    public boolean cambiarApellido(String apellidoNuevo, Usuario user) {
        boolean cambio = false;
        Optional<Usuario> usuarioExistente = userRepository.findByUser(user.getId());
    
        if (usuarioExistente.isPresent()) {
            Usuario usuario = usuarioExistente.get();
            usuario.setApellido(apellidoNuevo);
            this.userRepository.save(usuario);
            cambio = true;
        }
    
        return cambio;
    }
    

}