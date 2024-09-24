package com.TPOBackend.TPOBackend.Service;

import com.TPOBackend.TPOBackend.Repository.Entity.Carrito;
import com.TPOBackend.TPOBackend.Repository.Entity.Orden;
import com.TPOBackend.TPOBackend.Repository.Entity.OrdenDTO;
import com.TPOBackend.TPOBackend.Repository.Entity.OrdenMapper;
import com.TPOBackend.TPOBackend.Repository.Entity.Usuario;
import com.TPOBackend.TPOBackend.Repository.Entity.UsuarioDTO;
import com.TPOBackend.TPOBackend.Repository.OrdenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrdenService {
    private final AuthenticationService authenticationService;
    private final OrdenRepository ordenRepository;
    private final OrdenMapper ordenMapper;

    public Orden verOrdenById(int ordenId) throws Exception{
        Orden ordenBuscada = ordenRepository.findById(ordenId).orElseThrow(() -> new RuntimeException("Orden no encontrada"));
        return ordenBuscada;
    }

    public List<OrdenDTO> verOrdenesByUser() {
        
        Usuario user = authenticationService.getUsuarioAutenticado();
        List<Orden> ordenes = ordenRepository.findByUser(user);
        List<OrdenDTO> ordenesDTO = this.pasarDTO(ordenes);

        return ordenesDTO;
    }

        public List<OrdenDTO> pasarDTO(List<Orden> ordenes) {
        List<OrdenDTO> ordenDTOs = new ArrayList<>();

        for (Orden orden : ordenes) {
            OrdenDTO dto = ordenMapper.toDTO(orden);
            ordenDTOs.add(dto);
        }

        return ordenDTOs;
    }
}
