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

    /*@PutMapping("/{id}")
    public ResponseEntity<Producto> actualizarProducto(@PathVariable Long id, @RequestBody ProductoDTO productoUpdateDTO) {
        Optional<Producto> optionalProducto = productRepository.findById(id); // Correct usage

        if (!optionalProducto.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        Producto producto = optionalProducto.get();

        // Actualizar solo los campos que no son nulos en el DTO
        if (productoUpdateDTO.getNombre() != "") {
            producto.setNombre(productoUpdateDTO.getNombre());
        }
        if (productoUpdateDTO.getPrecio() != 0.0) {
            producto.setPrecio(productoUpdateDTO.getPrecio());
        }
        if (productoUpdateDTO.getStock() != 0) {
            producto.setStock(productoUpdateDTO.getStock());
        }
        if (productoUpdateDTO.getLiga() != "") {
            producto.setLiga(productoUpdateDTO.getLiga());
        }
        if (productoUpdateDTO.getEquipo() != "") {
            producto.setEquipo(productoUpdateDTO.getEquipo());
        }
        if (productoUpdateDTO.getMarca() != "") {
            producto.setMarca(productoUpdateDTO.getMarca());
        }
        if (productoUpdateDTO.getDescripcion() != "") {
            producto.setDescripcion(productoUpdateDTO.getDescripcion());
        }

        // Guardar los cambios en la base de datos
        ProductoService.actualizarProducto(producto);

        return ResponseEntity.ok(producto);
    }*/
}
