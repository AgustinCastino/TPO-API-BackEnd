package com.TPOBackend.TPOBackend.Service;

import java.time.LocalDate;
import java.util.List;


public class PublicacionService {
    private int idPublicacion;
    private String nombrePublicacion;
    private String descripcionPublicacion;
    private List <String> imagenPublicacion;
    private int stock;
    private float precioPublicacion;
    private String categoriaPublicacion;
    private LocalDate fechaPublicacion;

    private static int contadorID = 0;

    public PublicacionService(String nombrePublicacion, int stock , float precioPublicacion, String descripcionPublicacion, List <String> imagenPublicacion, String categoriaPublicacion) {
        this.nombrePublicacion = nombrePublicacion;
        this.stock = stock;
        this.precioPublicacion = precioPublicacion;
        this.descripcionPublicacion = descripcionPublicacion;
        this.imagenPublicacion = imagenPublicacion;
        this.categoriaPublicacion = categoriaPublicacion;
        this.fechaPublicacion = LocalDate.now();
        this.idPublicacion = generarID();
    }

    private static int generarID(){
        return contadorID++;
    }

    public int getIdPublicacion() {
        return idPublicacion;
    }

    public String getNombrePublicacion() {
        return nombrePublicacion;
    }

    public String getDescripcionPublicacion() {
        return descripcionPublicacion;
    }

    public List <String> getImagenPublicacion() {
        return imagenPublicacion;
    }

    public int getStock() {
        return stock;
    }

    public float getPrecioPublicacion() {
        return precioPublicacion;
    }

    public String getCategoriaPublicacion() {
        return categoriaPublicacion;
    }

    public LocalDate getFechaPublicacion() {
        return fechaPublicacion;
    }
}
