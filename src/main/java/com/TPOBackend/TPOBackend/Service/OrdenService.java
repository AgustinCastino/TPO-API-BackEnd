package com.TPOBackend.TPOBackend.Service;

import com.TPOBackend.TPOBackend.Repository.Entity.Carrito;
import com.TPOBackend.TPOBackend.Repository.Entity.Orden;
import com.TPOBackend.TPOBackend.Repository.Entity.Usuario;
import com.TPOBackend.TPOBackend.Repository.OrdenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrdenService {
    private final AuthenticationService authenticationService;
    private final OrdenRepository ordenRepository;

    public Orden verOrdenById(int ordenId) throws Exception{
        Orden ordenBuscada = ordenRepository.findById(ordenId).orElseThrow(() -> new RuntimeException("Orden no encontrada"));
        return ordenBuscada;
    }

    public List<Orden> verOrdenesByUser() {
        Usuario user = authenticationService.getUsuarioAutenticado();
        List<Orden> ordenes = ordenRepository.findByUser(user);
        return ordenes.stream().toList();

    }
}
