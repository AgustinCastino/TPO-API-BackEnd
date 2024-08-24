package com.TPOBackend.TPOBackend.Controllers.GestionUsuarioController;

import com.TPOBackend.TPOBackend.Service.Usuario;
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
    private Usuario usuarioService;

    @PostMapping("/registro")
    public ResponseEntity<String> registrarUsuario(Usuario usuario) throws Exception{

        // return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Los datos proporcionados no son válidos.");  posible return

        if(usuario.getNombreUsuario().isEmpty()){
            throw new Exception("No se ingreso ningun nombre de usuario");
        }
        if (usuario.getContrasena().length() < 8){
            throw new Exception("La contraseña debe tener una longitud mayor a 8");
        }
        if (usuario.getMail().isEmpty()){
            throw new Exception("No se ingreso ningun correo electronico");
        }

        Usuario usuarioRegistrado = usuarioService.registrarUsuario(usuario);
        return ResponseEntity.ok("Se registro con exito!");
    }

}
