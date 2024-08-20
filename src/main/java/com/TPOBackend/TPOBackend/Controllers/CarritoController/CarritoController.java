package com.TPOBackend.TPOBackend.Controllers.CarritoController;

import com.TPOBackend.TPOBackend.Model.Carrito;
import com.TPOBackend.TPOBackend.Model.Producto;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

public class CarritoController {

    // Endpoint que haría un POST del carrito
    @GetMapping("confirmarCompra")
    public boolean confirmarCompra(List<Producto> productos) {
        Carrito carrito = new Carrito();

        boolean compraConfirmada = carrito.confirmarCarrito(productos);

        return compraConfirmada;

    }
}
