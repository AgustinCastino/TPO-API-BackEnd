package com.TPOBackend.TPOBackend.Controllers.PerfilController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.TPOBackend.TPOBackend.Repository.Entity.ActualizarDatosDTO;
import com.TPOBackend.TPOBackend.Repository.Entity.OrdenDTO;
import com.TPOBackend.TPOBackend.Repository.Entity.Usuario;
import com.TPOBackend.TPOBackend.Repository.Entity.UsuarioDTO;
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

    @PutMapping("/editar_datos")
    public ResponseEntity editarDatos(@RequestBody ActualizarDatosDTO request) {

        ResponseEntity validacion = request.validarDatos();
        if (validacion != null) {
            return validacion;
        }else{
            String nombre = request.getNombre();
            String mail = request.getMail();
            String apellido = request.getApellido();
            Usuario user = authenticationService.getUsuarioAutenticado();

            usuarioService.editarPerfil(nombre, mail, apellido, user);

            return ResponseEntity.ok("Datos actualizados con éxito");
        }
    }

    @GetMapping("/historial_compras")
    public ResponseEntity<List<OrdenDTO>> getCompras(){
        List<OrdenDTO> compras = ordenService.verOrdenesByUser();            
        return ResponseEntity.ok(compras);
    }

    @GetMapping("")
    public ResponseEntity<UsuarioDTO> verDatosPersonales() throws Exception{
        UsuarioDTO user = usuarioService.getDatosUsuario();            
        return ResponseEntity.ok(user);
    }

 }