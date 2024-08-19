package com.TPOBackend.TPOBackend.Model;

import java.util.Date;

public class Compra {
    
    private int idCompra = 0;
    private Date fechaCompra;
    private Carrito carritoCompra;

    
    
    public Compra(int idCompra, Date fechaCompra, Carrito carritoCompra) {
        this.idCompra ++;
        this.fechaCompra = fechaCompra;
        this.carritoCompra = carritoCompra;
    }



    public double calcularCostoCarrito(){
        int costoTotal = 0;
        for (Producto producto : carritoCompra.getProductos()){
            costoTotal += producto.getPrice();
        }
        return costoTotal;
    }



    @Override
    public String toString() {
        return "Compra [fechaCompra=" + fechaCompra + ", carritoCompra=" + carritoCompra + "]";
    }


}
