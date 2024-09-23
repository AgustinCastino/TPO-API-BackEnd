package com.TPOBackend.TPOBackend.Repository.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
public class OrdenItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int cantidad;
    private double precioUnidad;
    private double precioTotal;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Producto producto;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "orden_id")
    private Orden orden;


}
