package com.TPOBackend.TPOBackend.Repository;

import com.TPOBackend.TPOBackend.Repository.Entity.Favorito;
import com.TPOBackend.TPOBackend.Repository.Entity.Producto;
import com.TPOBackend.TPOBackend.Repository.Entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface FavoritoRepository extends JpaRepository<Favorito, Integer> {

    @Transactional
    @Modifying
    @Query("DELETE FROM Favorito u WHERE u.usuario.id = ?1 AND u.producto.id = ?2")
    void deleteByUsuarioAndProducto(int id_usuario, int id_producto);



    Optional<Favorito> findByUsuarioAndProducto(Usuario usuario, Producto producto);

    List<Favorito> findByUsuario(Usuario usuario);

}