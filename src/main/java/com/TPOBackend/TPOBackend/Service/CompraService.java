package com.TPOBackend.TPOBackend.Service;

import java.util.Date;

public class Compra {
    
    private int idCompra = 0;
    private Date fechaCompra;
    private CarritoService carritoCompra;

    
    
    public Compra(int idCompra, Date fechaCompra, CarritoService carritoCompra) {
        this.idCompra ++;
        this.fechaCompra = fechaCompra;
        this.carritoCompra = carritoCompra;
    }


/*
    public double calcularCostoCarrito(){
        int costoTotal = 0;
        for (Producto producto : carritoCompra.getProductos()){
            costoTotal += producto.getPrice();
        }
        return costoTotal;
    }*/



    @Override
    public String toString() {
        return "Compra [fechaCompra=" + fechaCompra + ", carritoCompra=" + carritoCompra + "]";
    }


}
