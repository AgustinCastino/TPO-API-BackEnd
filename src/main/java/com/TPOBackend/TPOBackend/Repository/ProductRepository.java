package com.TPOBackend.TPOBackend.Repository;

import com.TPOBackend.TPOBackend.Repository.Entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Producto, Long> {

}
