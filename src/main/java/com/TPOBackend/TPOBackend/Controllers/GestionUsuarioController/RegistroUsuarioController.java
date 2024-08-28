package com.TPOBackend.TPOBackend.Controllers.GestionUsuarioController;

import com.TPOBackend.TPOBackend.Service.UsuarioService;
import com.TPOBackend.TPOBackend.Entity.Usuario;
import com.TPOBackend.TPOBackend.Entity.UsuarioInicioSesion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuario")
public class RegistroUsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/registro")
    public String registro(){
        return "registro";
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @PostMapping("/registro")
    public ResponseEntity<String> registrarUsuario(Usuario usuario) {
        if (!esContrasenaValida(usuario.getContrasena())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("La contraseña no cumple con los requisitos.");
        }

        try {
            usuarioService.registrarUsuario(usuario.getNombreUsuario(), usuario.getMail(), usuario.getContrasena(),
                    usuario.getFechaNacimiento(), usuario.getNombre(), usuario.getApellido());
            return ResponseEntity.status(HttpStatus.CREATED).body("Usuario registrado exitosamente.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al registrar el usuario.");
        }
    }

    @PostMapping("/login")
    public ResponseEntity<String> iniciarSesion(UsuarioInicioSesion usuarioInicioSesion) throws Exception {
        boolean esValido = usuarioService.iniciarSesion(usuarioInicioSesion.getIdentificador(), usuarioInicioSesion.getContrasena());
        if (esValido) {
            return ResponseEntity.ok("Inicio de sesión exitoso.");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Correo o contraseña incorrectos.");
        }
    }

    private boolean esContrasenaValida(String contrasena) {
        return contrasena.length() >= 8;
    }

}
