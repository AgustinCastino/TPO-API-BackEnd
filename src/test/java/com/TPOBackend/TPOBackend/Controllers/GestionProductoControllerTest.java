package com.TPOBackend.TPOBackend.Controllers;

import com.TPOBackend.TPOBackend.Controllers.GestionProductoController.GestionProductoController;
import com.TPOBackend.TPOBackend.Repository.Entity.Producto;
import com.TPOBackend.TPOBackend.Repository.Entity.ProductoDTO;
import com.TPOBackend.TPOBackend.Repository.ProductRepository;
import com.TPOBackend.TPOBackend.Service.ProductoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class GestionProductoControllerTest {

    @Mock
    private ProductoService productoService;

    @Mock
    private ProductRepository productRepository;  // Mock the repository

    @InjectMocks
    private GestionProductoController gestionProductoController;

    private Producto producto;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        producto = new Producto();
        producto.setId(1);
        producto.setNombre("Product 1");
        producto.setPrecio(100.0);
        producto.setStock(10);
    }

    @Test
    void testListarProductos() {
        List<Producto> productos = new ArrayList<>();
        productos.add(producto);

        when(productoService.listarProductos()).thenReturn(productos);

        ResponseEntity<List<Producto>> response = gestionProductoController.listarProductos();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().size());
        verify(productoService, times(1)).listarProductos();
    }

    @Test
    void testObtenerProducto_ProductoExistente() {
        when(productoService.obtenerProducto(6L)).thenReturn(Optional.of(producto));

        ResponseEntity<Producto> response = gestionProductoController.obtenerProducto(6L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(producto, response.getBody());
        verify(productoService, times(1)).obtenerProducto(6L);
    }

    @Test
    void testObtenerProducto_ProductoNoExistente() {
        when(productoService.obtenerProducto(6L)).thenReturn(Optional.empty());

        ResponseEntity<Producto> response = gestionProductoController.obtenerProducto(6L);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(productoService, times(1)).obtenerProducto(6L);
    }

    @Test
    void testCrearProducto() {
        when(productoService.crearProducto(any(Producto.class))).thenReturn(producto);

        ResponseEntity<Producto> response = gestionProductoController.crearProducto(producto);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(producto, response.getBody());
        verify(productoService, times(1)).crearProducto(any(Producto.class));
    }

    @Test
    void testEliminarProducto() {
        doNothing().when(productoService).eliminarProducto(6L);

        ResponseEntity<Void> response = gestionProductoController.eliminarProducto(6L);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(productoService, times(1)).eliminarProducto(6L);
    }

    @Test
    void testActualizarProducto() {
        ProductoDTO productoDTO = new ProductoDTO();
        productoDTO.setNombre("Updated Name");
        productoDTO.setPrecio(200.0);

        when(productRepository.findById(6L)).thenReturn(Optional.of(producto)); // Mocking the repository
        when(productoService.actualizarProducto(any(Producto.class))).thenReturn(producto);

        ResponseEntity<Producto> response = gestionProductoController.actualizarProducto(6L, productoDTO);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Updated Name", response.getBody().getNombre());
        verify(productRepository, times(1)).findById(6L);  // Verify repository interaction
        verify(productoService, times(1)).actualizarProducto(any(Producto.class));
    }
}
