
package com.TPOBackend.TPOBackend.Controllers.CatalogoProductoController;

import com.TPOBackend.TPOBackend.Repository.CatalogoRepository;
import com.TPOBackend.TPOBackend.Service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("/catalogo")
public class CatalogoProductoController {/*
    @Autowired
    private CatalogoRepository catalogoRepository;

    @GetMapping("/productos")
    public ResponseEntity<ArrayList<ProductoService>> obtenerProductos(){
        return new ResponseEntity<>(catalogoRepository.getProductos(), HttpStatus.OK);
    }
/*
    @GetMapping("/productos/{id}")
    public ResponseEntity<ProductoService> obtenerProductoPorId(int id){
        return new ResponseEntity<>(catalogoRepository.findById(id), HttpStatus.OK);
    }*/

/*
    @GetMapping("/productos/favoritos")
    public ResponseEntity<ArrayList<ProductoService>> obtenerProductosFavoritos(){
        ArrayList<ProductoService> productosFavoritos = new ArrayList<>();
        for (ProductoService producto : catalogoRepository.getProductos()) {
            if(producto.isInFavorites()){
                productosFavoritos.add(producto);
            }
        }
        return new ResponseEntity<>(productosFavoritos, HttpStatus.OK);
    }

    @GetMapping("/productos/vistos")
    public ResponseEntity<ArrayList<ProductoService>> obtenerProductosVistos(){
        ArrayList<ProductoService> productosVistos = new ArrayList<>();
        for (ProductoService producto : catalogoRepository.getProductos()) {
            if(producto.isVisto()){
                productosVistos.add(producto);
            }
        }
        return new ResponseEntity<>(productosVistos, HttpStatus.OK);
    }

    @GetMapping("/productos/destacados")
    public ResponseEntity<ArrayList<ProductoService>> obtenerProductosDestacados(){
        ArrayList<ProductoService> productosDestacados = new ArrayList<>();
        for (ProductoService producto : catalogoRepository.getProductos()) {
            if(producto.isDestacado()){
                productosDestacados.add(producto);
            }
        }
        return new ResponseEntity<>(productosDestacados, HttpStatus.OK);
    }*/
}


