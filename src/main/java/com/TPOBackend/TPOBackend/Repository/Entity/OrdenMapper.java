package com.TPOBackend.TPOBackend.Repository.Entity;

import org.springframework.stereotype.Component;

@Component
public class OrdenMapper {
    public OrdenDTO toDTO(Orden orden) {
        return OrdenDTO.builder()
                .items(orden.getItems())
                .fechaTransaccion(orden.getFechaTransaccion())
                .precio_total(orden.getPrecioTotal()).
                build();
    }
}
