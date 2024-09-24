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
        boolean isValido = valido(request.getUserName(), request.getPassword());
        if (isValido){
            return ResponseEntity.ok(service.register(request));
        } else {
            return ResponseEntity.badRequest().body("Campos no validos");
        }
    }

    @PostMapping("/authenticate")
    public ResponseEntity authenticate(@RequestBody UsuarioInicioSesion request) throws Exception{
        boolean isValido = valido(request.getEmail(), request.getPassword());
        if(isValido){
            return ResponseEntity.ok(service.authenticate(request));
        }else{
            return ResponseEntity.badRequest().body("Campos no validos");
        }

    }

    @DeleteMapping("/admin/eliminar/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity eliminar(@PathVariable int id) throws Exception{
        usuarioService.eliminar(id);
        return ResponseEntity.ok("Usuario eliminado");

    }

    @PutMapping("/actualizar")
    public ResponseEntity actualizar(@RequestBody CambioContrasenaDTO cambioContrasenaDTO) throws Exception {
        if(cambioContrasenaDTO.getContrasenaNueva().length()<8){
            return ResponseEntity.badRequest().body("Campos no validos");
        }
        usuarioService.cambiarPassword(cambioContrasenaDTO);
        return ResponseEntity.ok("Contraseña Actualizada");

    }


    private boolean valido(String nombreUsuario, String contrasena) {
        return !nombreUsuario.isEmpty() && contrasena.length() >= 8;
    }
}
