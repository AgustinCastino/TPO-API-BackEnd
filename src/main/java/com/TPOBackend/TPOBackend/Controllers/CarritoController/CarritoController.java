package com.TPOBackend.TPOBackend.Controllers.CarritoController;

import com.TPOBackend.TPOBackend.Repository.Entity.Carrito;
import com.TPOBackend.TPOBackend.Repository.Entity.Usuario;
import com.TPOBackend.TPOBackend.Service.AuthenticationService;
import com.TPOBackend.TPOBackend.Service.CarritoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("carrito")
public class CarritoController {

    @Autowired
    public CarritoService carritoService;

    
    @GetMapping("")
    public ResponseEntity verCarrito() throws Exception{
        Carrito carrito = carritoService.verCarrito();
        return ResponseEntity.ok(carrito);
    }


    @PostMapping("/agregar/{idProducto}/{cantidad}")
    public ResponseEntity agregarProducto(@PathVariable("idProducto") int idProducto,
                                          @PathVariable("cantidad") int cantidad)
            throws Exception{
        carritoService.agregarProducto(idProducto, cantidad);
        return ResponseEntity.ok("Agregado");
    }

    @PostMapping("/eliminar/{idProducto}")
    public ResponseEntity eliminarProducto(@PathVariable("idProducto") int idProducto) throws Exception{
        carritoService.eliminarProducto(idProducto);
        return ResponseEntity.ok("eliminar");
    }


    @PostMapping("/vaciar")
    public ResponseEntity vaciarCarrito() throws Exception{
        carritoService.vaciarCarrito();
        return ResponseEntity.ok("Vaciado");
    }

    @PostMapping("/checkout")
    public ResponseEntity confirmarCarrito() throws Exception{
        carritoService.checkout();
        carritoService.vaciarCarrito();
        return ResponseEntity.ok("Orden Creada");
    }


}
