package com.TPOBackend.TPOBackend.Controllers.OrdenController;

import com.TPOBackend.TPOBackend.Repository.Entity.Orden;
import com.TPOBackend.TPOBackend.Repository.Entity.OrdenDTO;
import com.TPOBackend.TPOBackend.Service.OrdenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("orden")
public class OrdenController {
    @Autowired
    public OrdenService ordenService;

    @GetMapping("")
    public ResponseEntity verOrdenesUsuario() throws Exception{
        List<OrdenDTO> ordenes = ordenService.verOrdenesByUser();
        return ResponseEntity.ok(ordenes);
    }

    @GetMapping("/{ordenId}")
    public ResponseEntity verOrdenById(@PathVariable int ordenId) throws Exception{
        Orden orden = ordenService.verOrdenById(ordenId);
        return ResponseEntity.ok(orden);
    }
}
