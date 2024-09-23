package com.TPOBackend.TPOBackend.Repository.Entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class CarritoItem {

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
    @JoinColumn(name = "carrito_id")
    private Carrito carrito;

    public void setPrecioTotal() {
        this.precioTotal = this.precioUnidad * this.cantidad;
    }

}
