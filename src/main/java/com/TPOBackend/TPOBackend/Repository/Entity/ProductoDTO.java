package com.TPOBackend.TPOBackend.Repository.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
public class ProductoDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nombre;
    private double precio;
    private int stock;
    private String liga;
    private String equipo;
    private String marca;
    /*@ManyToOne
    @JoinColumn(name = "categoria_id")
    private String categoria;*/
    private String descripcion;
    private boolean favorito;
    private boolean visto;
    private boolean destacado;
    /*@ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;*/

    public ProductoDTO(String nombre, String liga, String equipo, String marca, double precio, int stock, String categoria, String descripcion, boolean destacado) {
        this.nombre = nombre;
        this.liga = liga;
        this.equipo = equipo;
        this.marca = marca;
        this.precio = precio;
        this.stock = stock;
        /*this.categoria = categoria;*/
        this.descripcion = descripcion;
        this.favorito = false;
        this.visto = false;
        this.destacado = destacado;
        /*deberia ir la busqueda del usuario para asociarlo al producto*/

    }


    public boolean getDestacado() {
        return destacado;
    }

    public boolean getVisto() {
        return visto;
    }

    public boolean getFavorito() {
        return favorito;
    }
}