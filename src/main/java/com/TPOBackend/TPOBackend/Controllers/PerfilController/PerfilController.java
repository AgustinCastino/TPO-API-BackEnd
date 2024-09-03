package com.TPOBackend.TPOBackend.Controllers.PerfilController;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.TPOBackend.TPOBackend.Service.CompraService;
import com.TPOBackend.TPOBackend.Service.UsuarioService;

@RestController
@RequestMapping("/mi-perfil")
public class PerfilController {
    
    @Autowired
    private UsuarioService usuarioService;
    //private CompraService compraService;  

    @PutMapping("/cambiar_nombre")
    public ResponseEntity changeName(String newName,int userId){
        boolean usuario = usuarioService.changeName(newName, userId);
        if (usuario){
            return ResponseEntity.ok(usuario);  

        } else {
            return ResponseEntity.badRequest().body(false);
        }
    }
        
    @PutMapping("/cambiar_mail")
    public ResponseEntity changeMail(String newMail,int userId){
        boolean usuario = usuarioService.changeMail(newMail, userId);
        if (usuario){
            return ResponseEntity.ok(usuario);  

        } else {
            return ResponseEntity.badRequest().body(false);
        }
    } 

    @PutMapping("/cambiar_apellido")
    public ResponseEntity changeSurname(String newSurname,int userId){
        boolean usuario = usuarioService.changeSurname(newSurname, userId);
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
