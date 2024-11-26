package com.TPOBackend.TPOBackend.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.TPOBackend.TPOBackend.Repository.OrdenItemRepository;
import com.TPOBackend.TPOBackend.Repository.ProductRepository;
import com.TPOBackend.TPOBackend.Repository.Entity.OrdenItem;
import com.TPOBackend.TPOBackend.Repository.Entity.Producto;
import com.TPOBackend.TPOBackend.Repository.Entity.Usuario;

@Service
public class OrdenItemService {

    @Autowired
    private OrdenItemRepository ordenItemRepository;
    @Autowired
    private ProductoService productoService;

    public List<Producto> listarOrdenItems(int ordenId) {
        List<OrdenItem> items = ordenItemRepository.findByOrden(ordenId);
        List<Producto> productos = new ArrayList<>();
        for (OrdenItem item : items) {
            Producto producto = item.getProducto();
            productos.add(productoService.obtenerProducto(producto.getId()).get());
        }
        return productos;
    }
}
