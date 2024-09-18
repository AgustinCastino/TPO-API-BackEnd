package com.TPOBackend.TPOBackend.Controllers.GestionUsuarioController;

import com.TPOBackend.TPOBackend.Repository.Entity.AuthenticationResponse;
import com.TPOBackend.TPOBackend.Repository.Entity.RegisterRequest;
import com.TPOBackend.TPOBackend.Service.AuthenticationService;
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



@RestController
@RequestMapping("/usuario")
public class GestionUsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private AuthenticationService service;  // Agrega @Autowired aquí

    @PostMapping("/registro")
    public ResponseEntity register(@RequestBody RegisterRequest request) {
        boolean isValido = valido(request.getUserName(), request.getPassword());
        if (isValido){
            return ResponseEntity.ok(service.register(request));
        } else {
            return ResponseEntity.badRequest().body("No se pudo crear");
        }
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody UsuarioInicioSesion request) {
        return ResponseEntity.ok(service.authenticate(request));
    }

    private boolean valido(String nombreUsuario, String contrasena) {
        return !nombreUsuario.isEmpty() && contrasena.length() >= 8;
    }
}
