package com.TPOBackend.TPOBackend.Service;

import com.TPOBackend.TPOBackend.Repository.Entity.CambioContrasenaDTO;
import com.TPOBackend.TPOBackend.Repository.UserRepository;
import com.TPOBackend.TPOBackend.Repository.Entity.Usuario;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
@AllArgsConstructor
public class UsuarioService {

    @Autowired
    private  UserRepository userRepository;
    private  PasswordEncoder passwordEncoder;
    private AuthenticationService authenticationService;


    public Usuario cambiarPassword(CambioContrasenaDTO request) throws Exception {

        Usuario usuarioAut = authenticationService.getUsuarioAutenticado();
        usuarioAut.setContrasena(passwordEncoder.encode(request.getContrasenaNueva()));
        userRepository.save(usuarioAut);

        return usuarioAut;
    }

    public List<Usuario> listarUsuarios(){
        return userRepository.findAll();
    }

    public void eliminar(int id) throws Exception{
        Usuario usuarioExistente = userRepository.findById(id).orElseThrow(() -> new Exception("Usuario no encontrado en la base de datos"));
        this.userRepository.delete(usuarioExistente);
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