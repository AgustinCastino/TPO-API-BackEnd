package com.TPOBackend.TPOBackend.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.TPOBackend.TPOBackend.Repository.Entity.Compra;
import com.TPOBackend.TPOBackend.Repository.Entity.Usuario;


@Repository
public interface CompraRepository extends JpaRepository<Compra, Integer>{

    @Query("SELECT c FROM Compra c WHERE c.usuario = ?1")
    Optional<List<Compra>> findByUser(Usuario user);
}
