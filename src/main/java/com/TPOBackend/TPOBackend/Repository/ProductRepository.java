package com.TPOBackend.TPOBackend.Repository;

import com.TPOBackend.TPOBackend.Repository.Entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;


@Repository
public interface ProductRepository extends JpaRepository<Producto, Integer>, JpaSpecificationExecutor<Producto> {

}
