package com.TPOBackend.TPOBackend.Repository;


import com.TPOBackend.TPOBackend.Repository.Entity.OrdenItem;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository


public interface OrdenItemRepository extends JpaRepository<OrdenItem, Integer> {

    @Query("SELECT oi FROM OrdenItem oi WHERE oi.orden.id = :ordenId")
    List<OrdenItem> findByOrden(int ordenId);
}
