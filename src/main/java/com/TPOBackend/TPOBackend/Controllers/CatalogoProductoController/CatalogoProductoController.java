
package com.TPOBackend.TPOBackend.Controllers.CatalogoProductoController;

import com.TPOBackend.TPOBackend.Repository.Entity.Categoria;
import com.TPOBackend.TPOBackend.Repository.Entity.Producto;
import com.TPOBackend.TPOBackend.Repository.EspecificacionProducto;
import com.TPOBackend.TPOBackend.Repository.ProductRepository;
import com.TPOBackend.TPOBackend.Service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.data.jpa.domain.Specification;


import java.util.List;


@RestController
@RequestMapping("/catalogo")
public class CatalogoProductoController {

        @Autowired
    private ProductoService productoService;

    

    //funcion para traer todos los productos
    @GetMapping("/productos")
    public ResponseEntity<List<Producto>> getProductos() {
        return new ResponseEntity<>(productoService.listarProductos(), HttpStatus.OK);
    }

    @GetMapping("/productos/{id}")
    public ResponseEntity<Producto> getProducto(@PathVariable int id) {
        return new ResponseEntity<>(productoService.obtenerProducto(id).get(), HttpStatus.OK);
    }

    //funcion para traer todos los productos favoritos
    @GetMapping("/productos/favoritos")
    public ResponseEntity<List<Producto>> getProductosFavoritos() {
        return new ResponseEntity<>(productoService.getProductosFavoritos(), HttpStatus.OK);
    }


    //funcion para traer todos los productos por vistos
    @GetMapping("/productos/vistos")
    public ResponseEntity<List<Producto>> getProductosVistos() {
        return new ResponseEntity<>(productoService.getProductosVistos(), HttpStatus.OK);
    }

    //funcion para traer todos los productos destacados
    @GetMapping("/productos/destacados")
    public ResponseEntity<List<Producto>> getProductosDestacados() {
        return new ResponseEntity<>(productoService.getProductosDestacados(), HttpStatus.OK);
    }
    @GetMapping("/productos/filter")
    public List<Producto> filterProducts(
            @RequestParam(required = false) String nombre,
            @RequestParam(required = false) String liga,
            @RequestParam(required = false) String equipo,
            @RequestParam(required = false) String marca,
            @RequestParam(required = false) Double minPrecio,
            @RequestParam(required = false) Double maxPrecio,
            @RequestParam(required = false) Integer stock,
            @RequestParam(required = false) Categoria categoria,
            @RequestParam(required = false) Boolean destacado,
            @RequestParam(required = false, defaultValue = "asc") String sort // Sorting by price, default is ascending
    ) {
        Specification<Producto> spec = Specification.where(EspecificacionProducto.hasNombre(nombre))
                .and(EspecificacionProducto.hasLiga(liga))
                .and(EspecificacionProducto.hasEquipo(equipo))
                .and(EspecificacionProducto.hasMarca(marca))
                .and(EspecificacionProducto.hasPrecioBetween(minPrecio, maxPrecio))
                .and(EspecificacionProducto.hasStock(stock))
                .and(EspecificacionProducto.hasCategoria(categoria))
                .and(EspecificacionProducto.isDestacado(destacado));

        // Determine the sorting direction for price
        Sort sortOrder = "desc".equalsIgnoreCase(sort) ? Sort.by("precio").descending() : Sort.by("precio").ascending();

        return productoService.findAllProducts(spec,sortOrder);
    }


}


