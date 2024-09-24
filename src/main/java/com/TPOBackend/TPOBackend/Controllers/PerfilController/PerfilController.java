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
import com.TPOBackend.TPOBackend.Repository.Entity.OrdenDTO;
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
        String nombre = request.getNombre();

        if (nombre == null || nombre.trim().isEmpty()) {
            return ResponseEntity.badRequest().body("El nombre no puede ser nulo o vacío");
        }

        try {
            Integer.parseInt(nombre);
            return ResponseEntity.badRequest().body("El nombre no puede ser un número");
        } catch (NumberFormatException e) {
        }

        Usuario user = authenticationService.getUsuarioAutenticado();
        boolean usuario = usuarioService.cambiarDato("Nombre", nombre, user);
        
        if (usuario) {
            return ResponseEntity.ok(usuario);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario no encontrado");
        }
    }
        
    @PutMapping("/cambiar_mail")
    public ResponseEntity cambiarMail(@RequestBody ActualizarDatosDTO request){
        String mail = request.getMail();

        if (mail == null || mail.trim().isEmpty()) {
            return ResponseEntity.badRequest().body("El mail no puede ser nulo o vacío");
        }

        try {
            Integer.parseInt(mail);
            return ResponseEntity.badRequest().body("El mail no puede ser un número");
        } catch (NumberFormatException e) {
        }

        if (!mail.matches("^[A-Za-z0-9]+[A-Za-z0-9._%+-]*@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")) {
            return ResponseEntity.badRequest().body("El mail no tiene un formato válido");
        }

        Usuario user = authenticationService.getUsuarioAutenticado();
        boolean usuario = usuarioService.cambiarDato("Mail", mail, user);
        
        if (usuario) {
            return ResponseEntity.ok(usuario);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario no encontrado");
        }
    } 

    @PutMapping("/cambiar_apellido")
    public ResponseEntity cambiarApellido(@RequestBody ActualizarDatosDTO request) {
        String apellido = request.getApellido();

        if (apellido == null || apellido.trim().isEmpty()) {
            return ResponseEntity.badRequest().body("El apellido no puede ser nulo o vacío");
        }

        try {
            Integer.parseInt(apellido);
            return ResponseEntity.badRequest().body("El apellido no puede ser un número");
        } catch (NumberFormatException e) {
        }

        Usuario user = authenticationService.getUsuarioAutenticado();
        boolean usuario = usuarioService.cambiarDato("Apellido", apellido, user);
        
        if (usuario) {
            return ResponseEntity.ok(usuario);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario no encontrado");
        }
    }


    @GetMapping("/historial_compras")
    public ResponseEntity<List<OrdenDTO>> getCompras(){
        List<OrdenDTO> compras = ordenService.verOrdenesByUser();            
        return ResponseEntity.ok(compras);
    }
 }