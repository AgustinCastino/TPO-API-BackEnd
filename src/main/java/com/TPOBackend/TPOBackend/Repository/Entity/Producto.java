package com.TPOBackend.TPOBackend.Repository.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Data
@Entity
@NoArgsConstructor
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nombre;
    private double precio;
    private int stock;
    private String liga;
    private String equipo;
    private String marca;
    private Categoria categoria;
    private String descripcion;
    private boolean favorito;
    private boolean visto;
    private boolean destacado;

    @JsonIgnore
    @OneToMany(mappedBy = "producto", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Favorito> favoritos = new ArrayList<>();


    public Producto(String nombre,String liga, String equipo,String marca, double precio, int stock, Categoria categoria, String descripcion, boolean destacado) {
        this.nombre = nombre;
        this.liga = liga;
        this.equipo = equipo;
        this.marca = marca;
        this.precio = precio;
        this.stock = stock;
        this.categoria = categoria;
        this.descripcion = descripcion;
        this.favorito = false;
        this.visto = false;
        this.destacado = destacado;
        
     /*deberia ir la busqueda del usuario para asociarlo al producto*/

    }


    public void restarStock(int cantidad) {
        this.stock = this.stock - cantidad;
    }
}
