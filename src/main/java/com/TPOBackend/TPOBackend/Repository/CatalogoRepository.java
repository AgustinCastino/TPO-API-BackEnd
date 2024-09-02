
package com.TPOBackend.TPOBackend.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.TPOBackend.TPOBackend.Repository.Entity.Producto;
import com.TPOBackend.TPOBackend.Service.ProductoService;
import java.util.ArrayList;
import java.util.Optional;

@Repository
public interface CatalogoRepository  extends JpaRepository<Producto, Integer> {/*
    Optional<Producto> findById(int id);
    ArrayList<ProductoService> getProductos();*/
    

    
}


