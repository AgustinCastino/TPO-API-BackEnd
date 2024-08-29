package com.TPOBackend.TPOBackend.Service;
import com.TPOBackend.TPOBackend.Repository.CarritoRepository;
import com.TPOBackend.TPOBackend.Repository.Entity.Carrito;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
@Service
public class CarritoService {

    @Autowired
    private CarritoRepository carritoRepository;

    public Carrito agregarProducto(int idUsuario, int idProducto) throws Exception{
        int idCarrito = 1; // Se hardcodea pero deberia buscar el id del carrito del user que llega por parametro
        Carrito carrito = carritoRepository.findById(idCarrito).orElseThrow(()-> new Exception("No se encontró el carrito"));
        return carrito;
    }

    public Carrito eliminarProducto(int idUsuario, int idProducto) throws Exception{
        int idCarrito = 1; // Se hardcodea pero deberia buscar el id del carrito del user que llega por parametro
        Carrito carrito = carritoRepository.findById(idCarrito).orElseThrow(()-> new Exception("No se encontró el carrito"));
        return carrito;
    }

}
