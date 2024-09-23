package com.TPOBackend.TPOBackend.Repository.Entity;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Entity
@NoArgsConstructor
public class Carrito {

    public Carrito(Usuario user){
        this.usuario = user;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private double precioTotal = 0;

    @OneToMany(mappedBy = "carrito", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CarritoItem> items = new ArrayList<>();

    @OneToOne
    @JoinColumn(name = "user_id")
    private Usuario usuario;

    public void addItem(CarritoItem nuevoItem){
        this.items.add(nuevoItem);
        nuevoItem.setCarrito(this);
        this.actualizarPrecioTotal();
    }

    public void actualizarPrecioTotal(){
        double precioTotal = 0;
        for(CarritoItem item:items) {
            precioTotal = precioTotal + item.getPrecioTotal();
        }

        this.precioTotal = precioTotal;
    }


    public void borrarItemPorProducto(long idProducto) {
        this.items.removeIf(item -> item.getProducto().getId() == idProducto);
    }
}
