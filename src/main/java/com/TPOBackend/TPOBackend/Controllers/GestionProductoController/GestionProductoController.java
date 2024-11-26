package com.TPOBackend.TPOBackend.Controllers.GestionProductoController;

import com.TPOBackend.TPOBackend.Repository.Entity.Producto;
import com.TPOBackend.TPOBackend.Repository.Entity.ProductoDTO;
import com.TPOBackend.TPOBackend.Repository.ProductRepository;
import com.TPOBackend.TPOBackend.Service.OrdenItemService;
import com.TPOBackend.TPOBackend.Service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.AccessDeniedException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/Productos")
public class GestionProductoController {

    @Autowired
    private ProductoService ProductoService;

    @Autowired
    private ProductRepository productRepository;

    @Autowired  
    private OrdenItemService OrdenItemService;

    // Listar Productos
    @GetMapping
    public ResponseEntity<List<Producto>> listarProductos() {
        List<Producto> Productos = ProductoService.listarProductos();
        return new ResponseEntity<>(Productos, HttpStatus.OK);
    }

    // Obtener Producto por ID
    @GetMapping("/{id}")
    public ResponseEntity<Producto> obtenerProducto(@PathVariable int id) {
        Optional<Producto> Producto = ProductoService.obtenerProducto(id);
        if (Producto.isPresent()) {
            return new ResponseEntity<>(Producto.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Crear Producto
    @PostMapping("/management/crear")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Producto> crearProducto(@RequestBody Producto Producto) {
        Producto creado = ProductoService.crearProducto(Producto);
        return new ResponseEntity<>(creado, HttpStatus.CREATED);
    }

    // Listar Productos para management
    @GetMapping("/management")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<List<Producto>> gestionarProductos() {
        List<Producto> Productos = ProductoService.listarProductos();
        return new ResponseEntity<>(Productos, HttpStatus.OK);
    }

    // Eliminar Producto
    @DeleteMapping("/management/eliminar/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Void> eliminarProducto(@PathVariable int id) {
        ProductoService.eliminarProducto(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/management/update/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Producto> actualizarProducto(@PathVariable int id, @RequestBody ProductoDTO productoUpdateDTO) {
        Optional<Producto> optionalProducto = productRepository.findById(id); // Correct usage

        if (!optionalProducto.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        Producto producto = optionalProducto.get();

        // Actualizar solo los campos que no son nulos y no vacíos en el DTO
        if (productoUpdateDTO.getNombre() != null && !productoUpdateDTO.getNombre().trim().isEmpty()) {
            producto.setNombre(productoUpdateDTO.getNombre());
        }
        if (productoUpdateDTO.getPrecio() != 0.0) {
            producto.setPrecio(productoUpdateDTO.getPrecio());
        }
        if (productoUpdateDTO.getStock() != 0) {
            producto.setStock(productoUpdateDTO.getStock());
        }
        if (productoUpdateDTO.getLiga() != null && !productoUpdateDTO.getLiga().trim().isEmpty()) {
            producto.setLiga(productoUpdateDTO.getLiga());
        }
        if (productoUpdateDTO.getEquipo() != null && !productoUpdateDTO.getEquipo().trim().isEmpty()) {
            producto.setEquipo(productoUpdateDTO.getEquipo());
        }
        if (productoUpdateDTO.getMarca() != null && !productoUpdateDTO.getMarca().trim().isEmpty()) {
            producto.setMarca(productoUpdateDTO.getMarca());
        }
        if (productoUpdateDTO.getDescripcion() != null && !productoUpdateDTO.getDescripcion().trim().isEmpty()) {
            producto.setDescripcion(productoUpdateDTO.getDescripcion());
        }

        // Guardar los cambios en la base de datos
        ProductoService.actualizarProducto(producto);

        return ResponseEntity.ok(producto);
    }

    @GetMapping("/management/orden/{id}")
    public ResponseEntity<List<Producto>> productosPorOrden(@PathVariable int id) {
        List<Producto> productos = OrdenItemService.listarOrdenItems(id);
        return ResponseEntity.ok(productos);
    }
}
