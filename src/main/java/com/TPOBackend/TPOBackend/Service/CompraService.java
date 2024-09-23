package com.TPOBackend.TPOBackend.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.TPOBackend.TPOBackend.Repository.CompraRepository;
import com.TPOBackend.TPOBackend.Repository.UserRepository;
import com.TPOBackend.TPOBackend.Repository.Entity.Carrito;
import com.TPOBackend.TPOBackend.Repository.Entity.Compra;
import com.TPOBackend.TPOBackend.Repository.Entity.Usuario;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CompraService {
    
    @Autowired
    private CompraRepository compraRepository;
    private final AuthenticationService authenticationService;
    
    public ArrayList<Compra> getHistorial(){
        Usuario user = authenticationService.getUsuarioAutenticado();
        Optional<List<Compra>> historial = compraRepository.findByUser(user);

        if (historial.isEmpty()){
            return new ArrayList<Compra>();
        } else {
            return new ArrayList<Compra>(historial.get());
        }
    }

}
