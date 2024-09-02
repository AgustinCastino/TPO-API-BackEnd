package com.TPOBackend.TPOBackend.Service;

import com.TPOBackend.TPOBackend.Repository.Entity.Producto;
import com.TPOBackend.TPOBackend.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

    public Optional<Producto> obtenerProducto(Long id) {
        return productRepository.findById(id);
    }

    public void eliminarProducto(Long id) {
        productRepository.deleteById(id);
    }

    public Producto actualizarProducto(Producto product) {
        return productRepository.save(product);
    }

    
}


