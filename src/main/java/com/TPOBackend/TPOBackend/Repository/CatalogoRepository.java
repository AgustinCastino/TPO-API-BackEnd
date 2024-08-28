package com.TPOBackend.TPOBackend.Repository;

import org.springframework.stereotype.Repository;
import com.TPOBackend.TPOBackend.Service.Producto;
import java.util.ArrayList;
import java.util.Optional;

@Repository
public class CatalogoRepository {

    private final ArrayList<Producto> productos = new ArrayList<>();

    public CatalogoRepository(){
        productos.add(new Producto(1, "Coca Cola", "Bebida", "Bebida gaseosa", 10, 100, false, false));
        productos.add(new Producto(2, "Fanta", "Bebida", "Bebida gaseosa", 10, 100, false, false));
        productos.add(new Producto(3, "Sprite", "Bebida", "Bebida gaseosa", 10, 100, false, false));
        productos.add(new Producto(4, "Pepsi", "Bebida", "Bebida gaseosa", 10, 100, false, false));
        productos.add(new Producto(5, "7up", "Bebida", "Bebida gaseosa", 10, 100, false, false));
        productos.add(new Producto(6, "Mirinda", "Bebida", "Bebida gaseosa", 10, 100, false, false));
        productos.add(new Producto(7, "Manaos", "Bebida", "Bebida gaseosa", 10, 100, false, false));
        productos.add(new Producto(8, "Villa del Sur", "Bebida", "Jugo de naranja", 10, 100, false, false));
        productos.add(new Producto(9, "Cepita", "Bebida", "Jugo de naranja", 10, 100, false, false));
        productos.add(new Producto(10, "Naranju", "Bebida", "Jugo de naranja", 10, 100, false, false));
        productos.add(new Producto(11, "Tang", "Bebida", "Jugo en polvo", 10, 100, false, false));
        productos.add(new Producto(12, "Clight", "Bebida", "Jugo en polvo", 10, 100, false, false));
        productos.add(new Producto(13, "Quilmes", "Bebida", "Cerveza", 10, 100, false, false));
        productos.add(new Producto(14, "Brahma", "Bebida", "Cerveza", 10, 100, false, false));
    }

    public ArrayList<Producto> getProductos() {
        return productos;
    }

    public void setProductos(Producto producto) {
        this.productos.add(producto);
    }

    public Producto encontrarPorId(int id){
        return productos.stream().filter(producto -> producto.getId() == id).findFirst().orElse(null);
    }

    public void eliminarProducto(int id){
        productos.removeIf(producto -> producto.getId() == id);
    }

    public void modificarProducto(int id, Producto producto){
        for (int i = 0; i < productos.size(); i++) {
            if(productos.get(i).getId() == id){
                productos.set(i, producto);
            }
        }
    }

    public void productoFavorito(int id){
        for (Producto producto : productos) {
            if(producto.getId() == id){
                producto.setInFavorites(true);
            }
        }
    }

    public void agregarProducto(Producto producto){
        productos.add(producto);
    }

    public Optional<Producto> encontrarPorCategoria(String categoria){
        return productos.stream().filter(producto -> producto.getCategory().equals(categoria)).findFirst();
    }

    public Optional<Producto> encontrarPorDestacado(boolean destacado){
        return productos.stream().filter(producto -> producto.isDestacado() == destacado).findFirst();
    }

    public Optional<Producto> encontrarPorVisto(boolean visto){
        return productos.stream().filter(producto -> producto.isVisto() == visto).findFirst();
    }

    
}
