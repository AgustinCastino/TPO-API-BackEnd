package com.TPOBackend.TPOBackend.Service;

import com.TPOBackend.TPOBackend.Repository.ProductRepository;
import com.TPOBackend.TPOBackend.Repository.Entity.*;
import com.TPOBackend.TPOBackend.Repository.UserRepository;
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
    private ProductRepository productoRepository;


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


    public boolean editarPerfil(String nombre, String mail, String apellido, Usuario user) {
        Optional<Usuario> usuarioExistente = userRepository.findByUser(user.getId());

        if (usuarioExistente.isPresent()) {
            Usuario usuario = usuarioExistente.get();

            usuario.setNombre(nombre);
            usuario.setApellido(apellido);
            usuario.setMail(mail);

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

    public boolean agregarFavorito(Producto producto) {
        Usuario usuario = authenticationService.getUsuarioAutenticado();

        Optional<Producto> productoExistente = productoRepository.findById(producto.getId());
        if (productoExistente.isEmpty()) {
            System.out.println("El producto no existe en la base de datos");
            return false;
        }

        if (usuario.getFavoritos().contains(productoExistente.get())) {
            System.out.println("El producto ya está en favoritos");
            return false;
        }

        usuario.getFavoritos().add(productoExistente.get());
        userRepository.save(usuario);
        System.out.println("Producto agregado a favoritos correctamente");
        return true;
    }

    public List<Producto> obtenerFavUser(){
        Usuario user = authenticationService.getUsuarioAutenticado();
        List<Producto> prods = user.getFavoritos();
        return prods;
    }


    // public boolean eliminarFavorito (int producto_id) {
    //     Usuario usuario = authenticationService.getUsuarioAutenticado();
    //     Optional<Usuario> usuarioExistente = userRepository.findByUser(usuario.getId());
    //     if (usuarioExistente.isPresent()) {
    //         Usuario user = usuarioExistente.get();
    //         List<Producto> favoritos = user.getFavoritos();
    //         favoritos.remove(producto);
    //         user.setFavoritos(favoritos);
    //         userRepository.save(user);
    //         return true;
    //     }
    //     return false;
    // }

}