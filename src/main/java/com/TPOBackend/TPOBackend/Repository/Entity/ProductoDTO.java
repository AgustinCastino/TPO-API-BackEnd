package com.TPOBackend.TPOBackend.Repository.Entity;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ProductoDTO {

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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getLiga() {
        return liga;
    }

    public void setLiga(String liga) {
        this.liga = liga;
    }

    public String getEquipo() {
        return equipo;
    }

    public void setEquipo(String equipo) {
        this.equipo = equipo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
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