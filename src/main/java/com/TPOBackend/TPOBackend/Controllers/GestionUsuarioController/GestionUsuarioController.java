package com.TPOBackend.TPOBackend.Controllers.GestionUsuarioController;

import com.TPOBackend.TPOBackend.Service.UsuarioService;
import com.TPOBackend.TPOBackend.Repository.Entity.Usuario;
import com.TPOBackend.TPOBackend.Repository.Entity.UsuarioInicioSesion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/usuario")
public class GestionUsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/registro")
    public ResponseEntity registrarUsuario(@RequestBody Usuario usuario) {
        try {
            if (!esContrasenaValida(usuario.getContrasena())) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("La contraseña no cumple con los requisitos.");
            }
            Usuario usuarioCrear = usuarioService.registrarUsuario(usuario.getNombreUsuario(), usuario.getMail(), usuario.getContrasena(),
                    usuario.getFechaNacimiento(), usuario.getNombre(), usuario.getApellido());
            return ResponseEntity.ok(usuarioCrear);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }


    @PostMapping("/login")
    public ResponseEntity iniciarSesion(@RequestBody UsuarioInicioSesion usuarioInicioSesion) throws Exception {
        Usuario usuario = usuarioService.iniciarSesion(usuarioInicioSesion.getIdentificador(), usuarioInicioSesion.getContrasena());
        return ResponseEntity.ok(usuario);
    }

    private boolean esContrasenaValida(String contrasena) {
        return contrasena.length() >= 8;
    }

}
