package com.TPOBackend.TPOBackend.Controllers.PerfilController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.TPOBackend.TPOBackend.Repository.Entity.ActualizarDatosDTO;
import com.TPOBackend.TPOBackend.Repository.Entity.Orden;
import com.TPOBackend.TPOBackend.Repository.Entity.Usuario;
import com.TPOBackend.TPOBackend.Service.AuthenticationService;
import com.TPOBackend.TPOBackend.Service.OrdenService;
import com.TPOBackend.TPOBackend.Service.UsuarioService;

@RestController
@RequestMapping("/mi-perfil")
public class PerfilController {
    
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private OrdenService ordenService;
    @Autowired  
    private AuthenticationService authenticationService;

    @PutMapping("/cambiar_nombre")
    public ResponseEntity cambiarNombre(@RequestBody ActualizarDatosDTO request){
        Usuario user = authenticationService.getUsuarioAutenticado();
        boolean usuario = usuarioService.cambiarDato("Nombre",request.getNombre(),user);
        if (usuario){
            return ResponseEntity.ok(usuario);  

        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario no encontrado");
        }
    }
        
    @PutMapping("/cambiar_mail")
    public ResponseEntity cambiarMail(@RequestBody ActualizarDatosDTO request){
        Usuario user = authenticationService.getUsuarioAutenticado();
        boolean usuario = usuarioService.cambiarDato("Mail",request.getMail(), user);
        if (usuario){
            return ResponseEntity.ok(usuario);  

        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario no encontrado");        }
    } 

    @PutMapping("/cambiar_apellido")
    public ResponseEntity cambiarApellido(@RequestBody ActualizarDatosDTO request){
        Usuario user = authenticationService.getUsuarioAutenticado();
        boolean usuario = usuarioService.cambiarDato("Apellido",request.getApellido(), user);
        if (usuario){
            return ResponseEntity.ok(usuario);  

        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario no encontrado");
        }
    }

    @GetMapping("/historial_compras")
    public ResponseEntity<List<Orden>> getCompras(){
        List<Orden> compras = ordenService.verOrdenesByUser();            
        return ResponseEntity.ok(compras);
    }
 }