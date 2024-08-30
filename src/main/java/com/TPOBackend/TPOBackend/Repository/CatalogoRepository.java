
package com.TPOBackend.TPOBackend.Repository;

import org.springframework.stereotype.Repository;
import com.TPOBackend.TPOBackend.Service.ProductoService;
import java.util.ArrayList;
import java.util.Optional;

@Repository
public class CatalogoRepository {

    private final ArrayList<ProductoService> productos = new ArrayList<>();

    public CatalogoRepository(){
        productos.add(new ProductoService(1, "Coca Cola", "Bebida", "Bebida gaseosa", 10, 100, false, false));
        productos.add(new ProductoService(2, "Fanta", "Bebida", "Bebida gaseosa", 10, 100, false, false));
        productos.add(new ProductoService(3, "Sprite", "Bebida", "Bebida gaseosa", 10, 100, false, false));
        productos.add(new ProductoService(4, "Pepsi", "Bebida", "Bebida gaseosa", 10, 100, false, false));
        productos.add(new ProductoService(5, "7up", "Bebida", "Bebida gaseosa", 10, 100, false, false));
        productos.add(new ProductoService(6, "Mirinda", "Bebida", "Bebida gaseosa", 10, 100, false, false));
        productos.add(new ProductoService(7, "Manaos", "Bebida", "Bebida gaseosa", 10, 100, false, false));
        productos.add(new ProductoService(8, "Villa del Sur", "Bebida", "Jugo de naranja", 10, 100, false, false));
        productos.add(new ProductoService(9, "Cepita", "Bebida", "Jugo de naranja", 10, 100, false, false));
        productos.add(new ProductoService(10, "Naranju", "Bebida", "Jugo de naranja", 10, 100, false, false));
        productos.add(new ProductoService(11, "Tang", "Bebida", "Jugo en polvo", 10, 100, false, false));
        productos.add(new ProductoService(12, "Clight", "Bebida", "Jugo en polvo", 10, 100, false, false));
        productos.add(new ProductoService(13, "Quilmes", "Bebida", "Cerveza", 10, 100, false, false));
        productos.add(new ProductoService(14, "Brahma", "Bebida", "Cerveza", 10, 100, false, false));
    }

    public ArrayList<ProductoService> getProductos() {
        return productos;
    }

    public void setProductos(ProductoService producto) {
        this.productos.add(producto);
    }

    public ProductoService encontrarPorId(int id){
        return productos.stream().filter(producto -> producto.getId() == id).findFirst().orElse(null);
    }

    public void eliminarProducto(int id){
        productos.removeIf(producto -> producto.getId() == id);
    }

    public void modificarProducto(int id, ProductoService producto){
        for (int i = 0; i < productos.size(); i++) {
            if(productos.get(i).getId() == id){
                productos.set(i, producto);
            }
        }
    }

    public void productoFavorito(int id){
        for (ProductoService producto : productos) {
            if(producto.getId() == id){
                producto.setInFavorites(true);
            }
        }
    }

    public void agregarProducto(ProductoService producto){
        productos.add(producto);
    }

    public Optional<ProductoService> encontrarPorCategoria(String categoria){
        return productos.stream().filter(producto -> producto.getCategory().equals(categoria)).findFirst();
    }

    public Optional<ProductoService> encontrarPorDestacado(boolean destacado){
        return productos.stream().filter(producto -> producto.isDestacado() == destacado).findFirst();
    }

    public Optional<ProductoService> encontrarPorVisto(boolean visto){
        return productos.stream().filter(producto -> producto.isVisto() == visto).findFirst();
    }

    
}


