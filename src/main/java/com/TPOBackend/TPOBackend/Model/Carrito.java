package com.TPOBackend.TPOBackend.Model;
import java.util.ArrayList;
import java.util.List;

public class Carrito {

    private ArrayList<Producto> productos;

    public Carrito(){
        productos = new ArrayList<>();
    }
    public boolean confirmarCarrito(List<Producto> productos){

        // El carrito recibe un array con todos los productos
        // Validar que los productos esten disponibles

        return true;
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
