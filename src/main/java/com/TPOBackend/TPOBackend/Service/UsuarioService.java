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
import java.util.Optional;
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

    public UsuarioDTO getDatosUsuario() throws Exception {
        Usuario usuarioAut = authenticationService.getUsuarioAutenticado();
        Usuario usuario = userRepository.findByUser(usuarioAut.getId()).orElseThrow(() -> new Exception("Usuario no encontrado en la base de datos"));
        return userMapper.toDTO(usuario);
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