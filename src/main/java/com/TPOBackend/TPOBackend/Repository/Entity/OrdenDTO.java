package com.TPOBackend.TPOBackend.Repository.Entity;

import java.util.*;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrdenDTO {
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private List<OrdenItem> items;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date fechaTransaccion;
    @JsonFormat(shape = JsonFormat.Shape.NUMBER)
    private Double precio_total;
}
