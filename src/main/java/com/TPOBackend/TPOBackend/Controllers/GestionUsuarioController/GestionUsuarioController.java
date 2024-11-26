package com.TPOBackend.TPOBackend.Controllers.GestionUsuarioController;

import com.TPOBackend.TPOBackend.Repository.Entity.*;
import com.TPOBackend.TPOBackend.Service.AuthenticationService;
import com.TPOBackend.TPOBackend.Service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/usuario")
public class GestionUsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private AuthenticationService service;




    @GetMapping("admin/listaUsers")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<List<UsuarioDTO>> listarUsuarios(){
        List<UsuarioDTO> usuarios = usuarioService.listarUsuarios();
        return new ResponseEntity<>(usuarios, HttpStatus.OK);
    }

    @PostMapping("/registro")
    public ResponseEntity register(@RequestBody RegisterRequest request) throws Exception {
        boolean isValido = validoRegistro(request.getEmail(), request.getPassword(), request.getUserName());

        if (isValido){
            return ResponseEntity.ok(service.register(request));
        } else {
            return ResponseEntity.badRequest().body("Campos no validos");
        }
    }

    @PostMapping("/authenticate")
    public ResponseEntity authenticate(@RequestBody UsuarioInicioSesion request) throws Exception{
        boolean isValido = validoInicioSesion(request.getEmail(), request.getPassword());

        if(isValido){
            return ResponseEntity.ok(service.authenticate(request));
        }else{
            return ResponseEntity.badRequest().body("Campos no validos");
        }

    }

    @GetMapping("/getUser")
    public ResponseEntity<Usuario> getUsuarioLogueado() {
        try {
            Usuario usuario = service.getUsuarioAutenticado();
            System.out.println("----------" + usuario.getId());
            if (usuario == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); // Retorna 404 si el usuario no está autenticado
            }
            return ResponseEntity.ok(usuario);
        } catch (Exception e) {
            // Maneja el error y devuelve una respuesta 500
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }




    @PutMapping("/actualizar")
    public ResponseEntity actualizar(@RequestBody CambioContrasenaDTO cambioContrasenaDTO) throws Exception {
        if(cambioContrasenaDTO.getContrasenaNueva().length()<8){
            return ResponseEntity.badRequest().body("Campos no validos");
        }
        usuarioService.cambiarPassword(cambioContrasenaDTO);
        return ResponseEntity.ok("Contraseña Actualizada");

    }

    @PostMapping("/agregar_fav")
    public ResponseEntity agregarFavorito(@RequestBody Producto producto) throws Exception {
        if (usuarioService.agregarFavorito(producto)){
            return ResponseEntity.ok("Producto agregado a favoritos");
        } else {
            return ResponseEntity.badRequest().body("Producto ya en favoritos o hubo un error");
        }
    }

    @GetMapping("/listaFavoritos")
    public ResponseEntity obtenerFavoritos() throws Exception {
        List<Producto> fav = usuarioService.obtenerFavUser();
        return ResponseEntity.ok(fav);
    }

    @DeleteMapping("/eliminarFavorito/{id}")
    public ResponseEntity eliminarFavorito(@PathVariable int id) throws Exception {
        boolean eliminado = usuarioService.eliminarFavorito(id);

        if (eliminado) {
            return ResponseEntity.ok("Producto eliminado de favoritos correctamente.");
        } else {
            return ResponseEntity.badRequest().body("El producto no se encuentra en favoritos.");
        }
    }


    private boolean validoRegistro(String mail, String contrasena, String nombreUsuario) {
        return mail.matches("^[A-Za-z0-9]+[A-Za-z0-9._%+-]*@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$") && contrasena.length() >= 8 && !nombreUsuario.isEmpty() ;
    }

    private boolean validoInicioSesion(String mail, String contrasena) {
        return mail.matches("^[A-Za-z0-9]+[A-Za-z0-9._%+-]*@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$") && contrasena.length() >= 8;
    }



}
