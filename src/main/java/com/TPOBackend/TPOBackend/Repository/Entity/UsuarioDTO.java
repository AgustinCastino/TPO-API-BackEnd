package com.TPOBackend.TPOBackend.Repository.Entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDTO {
    private String nombre;
    private String apellido;
    private String nombreUsuario;
    private String mail;
    private String token;
}
