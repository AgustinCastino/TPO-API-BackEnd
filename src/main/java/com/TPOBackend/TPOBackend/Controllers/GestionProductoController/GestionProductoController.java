package com.TPOBackend.TPOBackend.Controllers.GestionProductoController;

import com.TPOBackend.TPOBackend.Repository.Entity.Producto;
import com.TPOBackend.TPOBackend.Repository.Entity.ProductoDTO;
import com.TPOBackend.TPOBackend.Repository.ProductRepository;
import com.TPOBackend.TPOBackend.Service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/Productos")
public class GestionProductoController {


        @Autowired
        private ProductoService ProductoService;
    @Autowired
    private ProductRepository productRepository;

    // Crear Producto
        @PostMapping
        public ResponseEntity<Producto> crearProducto(@RequestBody Producto Producto) {
            Producto creado = ProductoService.crearProducto(Producto);
            return new ResponseEntity<>(creado, HttpStatus.CREATED);
        }

        // Listar Productos
        @GetMapping
        public ResponseEntity<List<Producto>> listarProductos() {
            List<Producto> Productos = ProductoService.listarProductos();
            return new ResponseEntity<>(Productos, HttpStatus.OK);
        }

        // Obtener Producto por ID
        @GetMapping("/{id}")
        public ResponseEntity<Producto> obtenerProducto(@PathVariable Long id) {
            Optional<Producto> Producto = ProductoService.obtenerProducto(id);
            if (Producto.isPresent()) {
                return new ResponseEntity<>(Producto.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }

        // Eliminar Producto
        @DeleteMapping("/{id}")
        public ResponseEntity<Void> eliminarProducto(@PathVariable Long id) {
            ProductoService.eliminarProducto(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
