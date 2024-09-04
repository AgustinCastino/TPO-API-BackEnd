
package com.TPOBackend.TPOBackend.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.TPOBackend.TPOBackend.Repository.Entity.Producto;
import java.util.ArrayList;
import java.util.Optional;

@Repository
public interface CatalogoRepository  extends JpaRepository<Producto, Integer> {

    @Query("select p from Producto p")
    ArrayList<Producto> getProductos();

    @Query("select p from Producto p where p.nombre = ?1")
    Optional<Producto> findByNombre(String nombre);

    @Query("select p from Producto p where p.id = ?1")
    Optional<Producto> findById(int id);

    @Query("select p from Producto p where p.categoria = ?1")
    ArrayList<Producto> findByCategoria(String categoria);

    @Query("select p from Producto p where p.visto = ?1")
    ArrayList<Producto> findByVisto(boolean visto);

    // funcion para traer los destacados de los productos
    @Query("select p from Producto p where p.destacado = ?1")
    ArrayList<Producto> findByDestacado(boolean destacado);

    // funcion para traer los favoritos de los productos
    @Query("select p from Producto p where p.inFavorites = ?1")
    ArrayList<Producto> findByInFavorites(boolean inFavorites);

}
