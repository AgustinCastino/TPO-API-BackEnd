package com.TPOBackend.TPOBackend.Service;

import com.TPOBackend.TPOBackend.Repository.*;
import com.TPOBackend.TPOBackend.Repository.Entity.*;
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
    private final OrdenRepository ordenRepository;
    private final OrdenItemRepository ordenItemRepository;
    private final ProductRepository productRepository;
    private final AuthenticationService authenticationService;


    public void agregarProducto(Integer idProducto, int cantidad) throws Exception{
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
        carrito.borrarItemPorProducto(idProducto);
        carrito.actualizarPrecioTotal();
        carritoRepository.save(carrito);
    }

    public Carrito verCarrito()  throws Exception {
        return this.encontrarCarrito();
    }

    public void vaciarCarrito()  throws Exception {
        Carrito carrito = this.encontrarCarrito();
        carrito.setPrecioTotal(0);
        carritoItemRepository.deleteCarritoItemsPorId(carrito.getId());
        carrito.getItems().clear();
    }

    @Transactional
    public void checkout() throws Exception {
        Carrito carrito = this.encontrarCarrito();

        Orden nuevaOrden = new Orden();
        nuevaOrden.setUsuario(carrito.getUsuario());
        nuevaOrden.setPrecioTotal(carrito.getPrecioTotal());



        for(CarritoItem item: carrito.getItems()) {
            Producto producto = item.getProducto();

            if(item.getCantidad() > producto.getStock()){
                throw new Exception("El producto " + producto.getNombre() + " tiene stock insuficiente.");
            }else{
                OrdenItem nuevaOrdenItem = new OrdenItem();
                nuevaOrdenItem.setProducto(item.getProducto());
                nuevaOrdenItem.setPrecioUnidad(item.getPrecioUnidad());
                nuevaOrdenItem.setCantidad(item.getCantidad());
                nuevaOrdenItem.setPrecioTotal(item.getPrecioTotal());

                nuevaOrden.addItem(nuevaOrdenItem);
                ordenItemRepository.save(nuevaOrdenItem);
            }
        }

        ordenRepository.save(nuevaOrden);

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
