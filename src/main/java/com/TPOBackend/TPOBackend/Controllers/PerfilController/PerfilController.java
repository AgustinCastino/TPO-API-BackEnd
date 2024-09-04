package com.TPOBackend.TPOBackend.Controllers.PerfilController;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.TPOBackend.TPOBackend.Repository.Entity.ActualizarDatosDTO;
import com.TPOBackend.TPOBackend.Service.CompraService;
import com.TPOBackend.TPOBackend.Service.UsuarioService;

@RestController
@RequestMapping("/mi-perfil")
public class PerfilController {
    
    @Autowired
    private UsuarioService usuarioService;
    //private CompraService compraService;  

    @PutMapping("/cambiar_nombre")
    public ResponseEntity changeName(@RequestBody ActualizarDatosDTO request){
        boolean usuario = usuarioService.cambiarNombre(request.getNombre(), request.getId());
        if (usuario){
            return ResponseEntity.ok(usuario);  

        } else {
            return ResponseEntity.badRequest().body(false);
        }
    }
        
    @PutMapping("/cambiar_mail")
    public ResponseEntity changeMail(@RequestBody ActualizarDatosDTO request){
        boolean usuario = usuarioService.cambiarMail(request.getMail(), request.getId());
        if (usuario){
            return ResponseEntity.ok(usuario);  

        } else {
            return ResponseEntity.badRequest().body(false);
        }
    } 

    @PutMapping("/cambiar_apellido")
    public ResponseEntity changeSurname(@RequestBody ActualizarDatosDTO request){
        boolean usuario = usuarioService.cambiarApellido(request.getApellido(), request.getId());
        if (usuario){
            return ResponseEntity.ok(usuario);  

        } else {
            return ResponseEntity.badRequest().body(false);
        }
    }

    // @GetMapping("/historial_compras")
    // public ResponseEntity<Compra> getCompras(int userId){
    //     ArrayList<Compra> compras = compraService.getCompras(userId);
    //     return null;
    // }
}
