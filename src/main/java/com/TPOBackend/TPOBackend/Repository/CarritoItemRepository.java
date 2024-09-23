package com.TPOBackend.TPOBackend.Repository;

import com.TPOBackend.TPOBackend.Repository.Entity.Carrito;
import com.TPOBackend.TPOBackend.Repository.Entity.CarritoItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface CarritoItemRepository extends JpaRepository<CarritoItem, Integer> {
    @Modifying
    @Transactional
    @Query("delete from CarritoItem u where u.carrito.id = ?1")
     void deleteCarritoItemsPorId(int carritoId);

    @Modifying
    @Transactional
    @Query("delete from CarritoItem u where u.carrito.id = ?1 and u.producto.id = ?2")
    void deleteCarritoItemsPorProducto(int idCarrito, long idProducto);
}
