package com.TPOBackend.TPOBackend.Service;

import java.util.Date;

public class CompraService {
    
    private int idCompra = 0;
    private Date fechaCompra;
    private CarritoService carritoCompra;

    
    
    public CompraService(int idCompra, Date fechaCompra, CarritoService carritoCompra) {
        this.idCompra ++;
        this.fechaCompra = fechaCompra;
        this.carritoCompra = carritoCompra;
    }



    @Override
    public String toString() {
        return "Compra [fechaCompra=" + fechaCompra + ", carritoCompra=" + carritoCompra + "]";
    }


}
