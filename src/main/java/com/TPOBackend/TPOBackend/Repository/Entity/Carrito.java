package com.TPOBackend.TPOBackend.Repository.Entity;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Carrito {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    // Crea uma tabla intermedia entre Carrito y Productos
    // Esto para evitar que los productos tengan un atributo carritoId
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinTable(
            name = "carrito_productos",
            joinColumns = @JoinColumn(name = "carrito_id"),
            inverseJoinColumns = @JoinColumn(name = "producto_id")
    )
    private List<Producto> productos;
}
