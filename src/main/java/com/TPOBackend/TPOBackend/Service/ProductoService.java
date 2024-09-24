package com.TPOBackend.TPOBackend.Service;

import com.TPOBackend.TPOBackend.Repository.Entity.Producto;
import com.TPOBackend.TPOBackend.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;


@Service
public class ProductoService {

    @Autowired
    private ProductRepository productRepository;

    public Producto crearProducto(Producto product) {
        return productRepository.save(product);
    }

    public List<Producto> listarProductos() {
        return productRepository.findAll();
    }

    public Optional<Producto> obtenerProducto(Integer id) {
        return productRepository.findById(id);
    }

    public void eliminarProducto(Integer id) {
        productRepository.deleteById(id);
    }

    public Producto actualizarProducto(Producto product) {
        return productRepository.save(product);
    }

    public List<Producto> getProductosFavoritos() {
        return productRepository.findAll();
    }

    //funcion para traer todos los productos destacados
    public List<Producto> getProductosDestacados() {
        return productRepository.findAll();
    }

    public List<Producto> getProductosVistos() {
        return productRepository.findAll();
    }

    public List<Producto> findAllProducts(Specification<Producto> spec, Sort sortOrder) {
        return productRepository.findAll(spec,sortOrder);
    }
    
}


