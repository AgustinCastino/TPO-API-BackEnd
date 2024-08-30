package com.TPOBackend.TPOBackend.Controllers.GestionUsuarioController;

import com.TPOBackend.TPOBackend.Service.UsuarioService;
import com.TPOBackend.TPOBackend.Repository.Entity.Usuario;
import com.TPOBackend.TPOBackend.Repository.Entity.UsuarioInicioSesion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/usuario")
public class GesstionUsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/registro")
    public ResponseEntity registrarUsuario(String nombreUsuario, String mail, String contrasena, Date fechaNacimiento, String nombre, String apellido) throws Exception {
        if (!esContrasenaValida(contrasena)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("La contraseña no cumple con los requisitos.");
        }
        Usuario usuario = usuarioService.registrarUsuario(nombreUsuario, mail, contrasena, fechaNacimiento, nombre, apellido);
        return ResponseEntity.ok(usuario);
    }

    @PostMapping("/login")
    public ResponseEntity iniciarSesion(UsuarioInicioSesion usuarioInicioSesion) throws Exception {
        Usuario usuario = usuarioService.iniciarSesion(usuarioInicioSesion.getIdentificador(), usuarioInicioSesion.getContrasena());
        return ResponseEntity.ok(usuario);
    }

    private boolean esContrasenaValida(String contrasena) {
        return contrasena.length() >= 8;
    }

}
