package com.TPOBackend.TPOBackend.Service;

import com.TPOBackend.TPOBackend.Repository.Entity.CambioContrasenaDTO;
import com.TPOBackend.TPOBackend.Repository.Entity.UserMapper;
import com.TPOBackend.TPOBackend.Repository.Entity.UsuarioDTO;
import com.TPOBackend.TPOBackend.Repository.UserRepository;
import com.TPOBackend.TPOBackend.Repository.Entity.Usuario;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UsuarioService {

    @Autowired
    private  UserRepository userRepository;
    private  PasswordEncoder passwordEncoder;
    private AuthenticationService authenticationService;
    private UserMapper userMapper;


    public void cambiarPassword(CambioContrasenaDTO request) throws Exception {

        Usuario usuarioAut = authenticationService.getUsuarioAutenticado();
        usuarioAut.setContrasena(passwordEncoder.encode(request.getContrasenaNueva()));
        userRepository.save(usuarioAut);
    }


    public List<UsuarioDTO> listarUsuarios(){
        List<Usuario> users = userRepository.findAll();
        List<UsuarioDTO> listaUsuarios = this.pasarDTO(users);
        return listaUsuarios;
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

    public List<UsuarioDTO> pasarDTO(List<Usuario> usuarios) {
        List<UsuarioDTO> usuarioDTOs = new ArrayList<>();

        for (Usuario usuario : usuarios) {
            UsuarioDTO dto = userMapper.toDTO(usuario);
            usuarioDTOs.add(dto);
        }

        return usuarioDTOs;
    }


}