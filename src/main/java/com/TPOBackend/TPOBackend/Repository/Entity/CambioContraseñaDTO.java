package com.TPOBackend.TPOBackend.Repository.Entity;

//import com.example.demo.repository.entities.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CambioContraseñaDTO {
    private String email;
    private String contrasenaActual;
    private String contrasenaNueva;
}
