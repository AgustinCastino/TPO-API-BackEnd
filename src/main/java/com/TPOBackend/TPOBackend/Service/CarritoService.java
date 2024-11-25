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
import java.util.Date;
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

        if(cantidad > producto.getStock()){
            throw new Exception("El producto " + producto.getNombre() + " tiene stock insuficiente.");
        }

        Optional<CarritoItem> carritoItemOpt = carrito.getItems()
                .stream()
                .filter(item -> item.getProducto().getId() == idProducto)
                .findFirst();

        if (carritoItemOpt.isPresent()) {
            // Si el producto ya está, aumentar la cantidad y actualizar el precio total
            CarritoItem carritoItem = carritoItemOpt.get();
            carritoItem.setCantidad(carritoItem.getCantidad() + cantidad);
            carritoItem.setPrecioTotal(); // Recalcular precio total
        } else {
            // Si el producto no está, crear un nuevo CarritoItem
            CarritoItem nuevoItem = new CarritoItem();
            nuevoItem.setCarrito(carrito);
            nuevoItem.setProducto(producto);
            nuevoItem.setCantidad(cantidad);
            nuevoItem.setPrecioUnidad(producto.getPrecio());
            nuevoItem.setPrecioTotal(); // Calcular precio total
            carrito.addItem(nuevoItem); // Vincularlo al carrito
        }
        carrito.actualizarPrecioTotal();

        // Guardar cambios
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
        nuevaOrden.setFechaTransaccion(new Date());

        for(CarritoItem item: carrito.getItems()) {
            Producto producto = item.getProducto();

            if(item.getCantidad() > producto.getStock()){
                throw new Exception("El producto " + producto.getNombre() + " tiene stock insuficiente.");
            }

        }

        for(CarritoItem item: carrito.getItems()) {
            Producto producto = item.getProducto();

            OrdenItem nuevaOrdenItem = new OrdenItem();
            nuevaOrdenItem.setProducto(item.getProducto());
            nuevaOrdenItem.setPrecioUnidad(item.getPrecioUnidad());
            nuevaOrdenItem.setCantidad(item.getCantidad());
            nuevaOrdenItem.setPrecioTotal(item.getPrecioTotal());

            nuevaOrden.addItem(nuevaOrdenItem);
            ordenItemRepository.save(nuevaOrdenItem);
            producto.restarStock(item.getCantidad());
            productRepository.save(producto);

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
