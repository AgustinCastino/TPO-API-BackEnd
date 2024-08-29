package com.TPOBackend.TPOBackend.Service;

import org.springframework.stereotype.Service;

@Service
public class Producto {
    private int id;
    private String name;
    private String category;
    private String description;
    private int stock;
    private double price;
    private boolean inFavorites;
    private boolean visto;
    private boolean destacado;

    public Producto(int id, String name, String category, String description, int stock, double price, boolean inFavorites, boolean visto) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.description = description;
        this.stock = stock;
        this.price = price;
        this.inFavorites = inFavorites;
        this.visto = visto;
        this.destacado = false;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getCategory() {
        return category;
    }

    public int getStock() {
        return stock;
    }

    public double getPrice() {
        return price;
    }

    public boolean isInFavorites() {
        return inFavorites;
    }

    public boolean isVisto() {
        return visto;
    }

    public int getId() {
        return id;
    }

    public boolean isDestacado() {
        return destacado;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setInFavorites(boolean inFavorites) {
        this.inFavorites = inFavorites;
    }

    public void setVisto(boolean visto) {
        this.visto = visto;
    }

    public void setDestacado(boolean destacado) {
        this.destacado = destacado;
    }

    @Override
    public String toString() {
        return "Producto [name=" + name + ", category=" + category + ", description=" + description + ", stock=" + stock
                + ", price=" + price + "]";
    }

    
}
