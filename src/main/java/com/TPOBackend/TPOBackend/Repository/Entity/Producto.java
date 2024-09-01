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

    private String nombre;
    private double precio;
    private int stock;
    private String liga;
    private String equipo;
    private String marca;
    private String categoria;
    private String descripcion;
    private boolean favorito;
    private boolean visto;
    private boolean destacaddo;

    public Producto(String nombre,String liga, String equipo,String marca, double precio, int stock, String categoria, String descripcion, boolean destacaddo) {
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
        this.destacaddo = destacaddo;

    }

    public int getId() {
        return id;
    }


    public Carrito getCarrito() {
        return carrito;
    }

    public void setCarrito(Carrito carrito) {
        this.carrito = carrito;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public boolean isFavorito() {
        return favorito;
    }

    public void setFavorito(boolean favorito) {
        this.favorito = favorito;
    }

    public boolean isVisto() {
        return visto;
    }

    public void setVisto(boolean visto) {
        this.visto = visto;
    }

    public boolean isDestacaddo() {
        return destacaddo;
    }

    public void setDestacaddo(boolean destacaddo) {
        this.destacaddo = destacaddo;
    }

}
