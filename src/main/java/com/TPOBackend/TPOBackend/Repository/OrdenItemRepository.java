package com.TPOBackend.TPOBackend.Repository;


import com.TPOBackend.TPOBackend.Repository.Entity.OrdenItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository


public interface OrdenItemRepository extends JpaRepository<OrdenItem, Integer> {
}
