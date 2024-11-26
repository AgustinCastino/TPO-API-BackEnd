package com.TPOBackend.TPOBackend.Service;

import com.TPOBackend.TPOBackend.Repository.FavoritoRepository;
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
    @Autowired
    private  PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationService authenticationService;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private ProductRepository productoRepository;
    @Autowired
    private FavoritoRepository favoritoRepository;


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

    public boolean agregarFavorito(Producto producto) {
        Usuario usuario = authenticationService.getUsuarioAutenticado();

        Optional<Producto> productoExistente = productoRepository.findById(producto.getId());
        if (productoExistente.isEmpty()) {
            System.out.println("El producto no existe en la base de datos");
            return false;
        }

        // Verifica si el producto ya está en favoritos
        Optional<Favorito> favoritoExistente = favoritoRepository.findByUsuarioAndProducto(usuario, productoExistente.get());
        if (favoritoExistente.isPresent()) {
            System.out.println("El producto ya está en favoritos");
            return false;
        }

        // Crear una nueva relación de favorito
        Favorito favorito = new Favorito();
        favorito.setUsuario(usuario);
        favorito.setProducto(productoExistente.get());

        favoritoRepository.save(favorito);

        System.out.println("Producto agregado a favoritos correctamente");
        return true;
    }


    public List<Producto> obtenerFavUser() {
        Usuario usuario = authenticationService.getUsuarioAutenticado();

        // Buscar todos los favoritos del usuario
        List<Favorito> favoritos = favoritoRepository.findByUsuario(usuario);

        // Extraer los productos de los favoritos
        List<Producto> productos = favoritos.stream()
                .map(Favorito::getProducto)
                .collect(Collectors.toList());

        return productos;
    }



    public boolean eliminarFavorito(int id) {
        Usuario usuario = authenticationService.getUsuarioAutenticado();

        // Verifica si el producto existe en la base de datos
        Optional<Producto> productoExistente = productoRepository.findById(id);
        if (productoExistente.isEmpty()) {
            System.out.println("El producto no existe en la base de datos");
            return false;
        }

        Producto productoToRemove = productoExistente.get();

        favoritoRepository.deleteByUsuarioAndProducto(usuario.getId(), productoToRemove.getId());
        return true;
    }





}