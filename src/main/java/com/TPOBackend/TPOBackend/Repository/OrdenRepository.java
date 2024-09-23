package com.TPOBackend.TPOBackend.Repository;

import com.TPOBackend.TPOBackend.Repository.Entity.Orden;
import com.TPOBackend.TPOBackend.Repository.Entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface OrdenRepository extends JpaRepository<Orden, Integer> {
    @Query("Select u from Orden u where u.usuario = ?1")
    List<Orden> findByUser(Usuario user);

}
