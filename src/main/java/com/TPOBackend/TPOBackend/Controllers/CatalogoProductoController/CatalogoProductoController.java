package com.TPOBackend.TPOBackend.Controllers.CatalogoProductoController;

import com.TPOBackend.TPOBackend.Repository.CatalogoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.TPOBackend.TPOBackend.Service.Producto;
import java.util.ArrayList;

@RestController
@RequestMapping("/catalogo")
public class CatalogoProductoController {
    @Autowired
    private CatalogoRepository catalogoRepository;

    @GetMapping("/productos")
    public ResponseEntity<ArrayList<Producto>> obtenerProductos(){
        return new ResponseEntity<>(catalogoRepository.getProductos(), HttpStatus.OK);
    }

    @PostMapping("/productos")
    public ResponseEntity<Producto> agregarProducto(Producto producto){
        catalogoRepository.setProductos(producto);
        return new ResponseEntity<>(producto, HttpStatus.CREATED);
    }

    @GetMapping("/productos/{id}")
    public ResponseEntity<Producto> obtenerProductoPorId(int id){
        return new ResponseEntity<>(catalogoRepository.encontrarPorId(id), HttpStatus.OK);
    }

    @PostMapping("/productos/{id}")
    public ResponseEntity<Producto> modificarProducto(int id, Producto producto){
        catalogoRepository.modificarProducto(id, producto);
        return new ResponseEntity<>(producto, HttpStatus.OK);
    }

    @PostMapping("/productos/{id}/favorito")
    public ResponseEntity<Producto> productoFavorito(int id){
        catalogoRepository.productoFavorito(id);
        return new ResponseEntity<>(catalogoRepository.encontrarPorId(id), HttpStatus.OK);
    }

    @PostMapping("/productos/{id}/eliminar")
    public ResponseEntity<String> eliminarProducto(int id){
        catalogoRepository.eliminarProducto(id);
        return new ResponseEntity<>("Producto eliminado", HttpStatus.OK);
    }

    @GetMapping("/productos/favoritos")
    public ResponseEntity<ArrayList<Producto>> obtenerProductosFavoritos(){
        ArrayList<Producto> productosFavoritos = new ArrayList<>();
        for (Producto producto : catalogoRepository.getProductos()) {
            if(producto.isInFavorites()){
                productosFavoritos.add(producto);
            }
        }
        return new ResponseEntity<>(productosFavoritos, HttpStatus.OK);
    }

    @GetMapping("/productos/vistos")
    public ResponseEntity<ArrayList<Producto>> obtenerProductosVistos(){
        ArrayList<Producto> productosVistos = new ArrayList<>();
        for (Producto producto : catalogoRepository.getProductos()) {
            if(producto.isVisto()){
                productosVistos.add(producto);
            }
        }
        return new ResponseEntity<>(productosVistos, HttpStatus.OK);
    }

    @GetMapping("/productos/destacados")
    public ResponseEntity<ArrayList<Producto>> obtenerProductosDestacados(){
        ArrayList<Producto> productosDestacados = new ArrayList<>();
        for (Producto producto : catalogoRepository.getProductos()) {
            if(producto.isDestacado()){
                productosDestacados.add(producto);
            }
        }
        return new ResponseEntity<>(productosDestacados, HttpStatus.OK);
    }
}
