package com.TPOBackend.TPOBackend.Repository.Entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioInicioSesion {
    private String identificador;
    private String contrasena;

}

