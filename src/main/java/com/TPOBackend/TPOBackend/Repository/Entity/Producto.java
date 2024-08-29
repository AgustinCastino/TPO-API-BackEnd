package com.TPOBackend.TPOBackend.Repository.Entity;

import jakarta.persistence.*;

@Entity
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "carrito_id")
    private Carrito carrito;
}
