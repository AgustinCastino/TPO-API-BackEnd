package com.TPOBackend.TPOBackend.Service;

import com.TPOBackend.TPOBackend.Repository.CarritoItemRepository;
import com.TPOBackend.TPOBackend.Repository.CarritoRepository;
import com.TPOBackend.TPOBackend.Repository.Entity.Carrito;
import com.TPOBackend.TPOBackend.Repository.Entity.CarritoItem;
import com.TPOBackend.TPOBackend.Repository.Entity.Producto;
import com.TPOBackend.TPOBackend.Repository.Entity.Usuario;
import com.TPOBackend.TPOBackend.Repository.ProductRepository;
import com.TPOBackend.TPOBackend.Repository.UserRepository;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CarritoService {


    private final CarritoRepository carritoRepository;
    private final CarritoItemRepository carritoItemRepository;
    private final ProductRepository productRepository;
    private final AuthenticationService authenticationService;


    public void agregarProducto(long idProducto, int cantidad) throws Exception{
        Carrito carrito = this.encontrarCarrito();
        Producto producto = productRepository.findById(idProducto).orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        // Me fijo si el producto ya está en el carrito
        CarritoItem carritoItem = carrito.getItems()
                .stream()
                .filter(item -> item.getProducto().getId() == idProducto)
                .findFirst().orElse(new CarritoItem());

        //Si ese producto no está, creo una línea para ese producto
        if (carritoItem.getProducto() == null) {
            carritoItem.setCarrito(carrito);
            carritoItem.setProducto(producto);
            carritoItem.setCantidad(cantidad);
            carritoItem.setPrecioUnidad(producto.getPrecio());
        }else { // Si el producto ya está, aumento su cantidad
            carritoItem.setCantidad(carritoItem.getCantidad() + cantidad);
        }

        carritoItem.setPrecioTotal();
        carrito.addItem(carritoItem);
        carritoItemRepository.save(carritoItem);
        carritoRepository.save(carrito);
    }

    public void eliminarProducto(long idProducto) throws Exception{
        Carrito carrito = this.encontrarCarrito();
        int idCarrito = carrito.getId();
        // Borro los items del carrito
        carritoItemRepository.deleteCarritoItemsPorProducto(idCarrito, idProducto);
    }

    public Carrito verCarrito()  throws Exception {
        return this.encontrarCarrito();
    }

    public void vaciarCarrito()  throws Exception {
        Carrito carrito = this.encontrarCarrito();
        // Borro los items del carrito
        carritoItemRepository.deleteCarritoItemsPorId(carrito.getId());
        carrito.getItems().clear();

        // Borro el carrito
        carritoRepository.deleteById(carrito.getId());

    }

    private Carrito encontrarCarrito(){
        Usuario user = authenticationService.getUsuarioAutenticado();
        Optional<Carrito> carrito = carritoRepository.findByUser(user);

        if (carrito.isEmpty()){
            Carrito nuevoCarrito = new Carrito(user);
            carritoRepository.save(nuevoCarrito);
            return nuevoCarrito;
        }

        return carrito.get();
    }


}
