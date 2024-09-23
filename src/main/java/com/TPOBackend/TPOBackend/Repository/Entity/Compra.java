package com.TPOBackend.TPOBackend.Repository.Entity;

import java.sql.Date;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Compra {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "fecha_compra", nullable = false)
    private Date fechaCompra;
    // @OneToOne
    // @JoinColumn(name = "carrito_id", nullable = false)
    // private Carrito carrito;
    private int carrito_id;
    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

}
