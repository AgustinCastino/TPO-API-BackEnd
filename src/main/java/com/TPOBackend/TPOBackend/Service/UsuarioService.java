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
  
    public boolean cambiarDato(String valorACambiar, String datoNuevo, Usuario user) {
        Optional<Usuario> usuarioExistente = userRepository.findByUser(user.getId());
    
        if (usuarioExistente.isPresent()) {
            Usuario usuario = usuarioExistente.get();
            switch (valorACambiar) {
                case "Nombre":
                    usuario.setNombre(datoNuevo);
                    break;
                case "Apellido":
                    usuario.setApellido(datoNuevo);
                    break;
                case "Mail":
                    usuario.setMail(datoNuevo);
                    break;

                }
            this.userRepository.save(usuario);
            return true;
        }
        return false;
    }
}