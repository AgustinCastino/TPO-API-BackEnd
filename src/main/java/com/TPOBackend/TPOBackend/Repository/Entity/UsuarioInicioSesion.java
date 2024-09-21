package com.TPOBackend.TPOBackend.Repository.Entity;


import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioInicioSesion {
    private String email;
    private String password;

}

