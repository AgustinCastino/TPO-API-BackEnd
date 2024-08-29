package com.TPOBackend.TPOBackend.Service;
import com.TPOBackend.TPOBackend.Repository.CarritoRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class Carrito {

    private ArrayList<Producto> productos;
    private int idUsuario;

    @Autowired
    private CarritoRepository carritoRepository;

    public Carrito(){
        productos = new ArrayList<>();
    }
    public void agregarProducto(int usuarioId, int productoId){

        // El carrito recibe un array con todos los productos
        // Validar que los productos esten disponibles


    }

    public ArrayList<Producto> getProductos(){
        return this.productos;
    }

    private boolean validarStock(Producto producto){
        return true;
    }

    public boolean EliminarProducto(int idProducto){
        boolean productoBorrado = productos.removeIf(producto -> producto.getId() == idProducto);

        return productoBorrado;
    }

    public boolean vaciarCarrito(){
        return true;
    }

    @Override
    public String toString() {
        return "Carrito [productos=" + productos + "]";
    }
}
