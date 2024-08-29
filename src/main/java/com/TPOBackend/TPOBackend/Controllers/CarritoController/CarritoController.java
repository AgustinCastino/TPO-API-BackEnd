package com.TPOBackend.TPOBackend.Controllers.CarritoController;

import com.TPOBackend.TPOBackend.Repository.Entity.Carrito;
import com.TPOBackend.TPOBackend.Service.CarritoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("carrito")
public class CarritoController {

    @Autowired
    private CarritoService carritoService;

    @GetMapping("/prueba")
    public ResponseEntity prueba() throws Exception{
        return ResponseEntity.ok("carrito");
    }

    @PostMapping("/agregar")
    public ResponseEntity agregarProducto(int idUsuario, int idProducto) throws Exception{
        Carrito carrito = carritoService.agregarProducto(idUsuario, idProducto);
        return ResponseEntity.ok(carrito);
    }

    @PostMapping("/eliminar")
    public ResponseEntity eliminarProducto(int idUsuario, int idProducto) throws Exception{
        Carrito carrito = carritoService.eliminarProducto(idUsuario, idProducto);
        return ResponseEntity.ok(carrito);
    }

}
