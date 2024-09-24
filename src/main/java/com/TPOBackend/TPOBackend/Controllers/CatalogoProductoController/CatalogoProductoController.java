
package com.TPOBackend.TPOBackend.Controllers.CatalogoProductoController;

import com.TPOBackend.TPOBackend.Repository.CatalogoRepository;
import com.TPOBackend.TPOBackend.Service.ProductoService;
import com.TPOBackend.TPOBackend.Repository.Entity.Producto;

import org.hibernate.mapping.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}


