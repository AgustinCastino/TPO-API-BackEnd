package com.TPOBackend.TPOBackend.Service;

import com.TPOBackend.TPOBackend.Controllers.GestionUsuarioController.config.JwtService;
import com.TPOBackend.TPOBackend.Repository.Entity.AuthenticationResponse;
import com.TPOBackend.TPOBackend.Repository.Entity.RegisterRequest;
import com.TPOBackend.TPOBackend.Repository.Entity.Usuario;
import com.TPOBackend.TPOBackend.Repository.Entity.UsuarioInicioSesion;
import com.TPOBackend.TPOBackend.Repository.UserRepository;




import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
        @Autowired
        private UserRepository repository;
        @Autowired
        private PasswordEncoder passwordEncoder;
        @Autowired
        private JwtService jwtService;
        @Autowired
        private AuthenticationManager authenticationManager;

        public AuthenticationResponse register(RegisterRequest request) {
                var user = Usuario.builder()
                        .nombreUsuario(request.getUserName())
                        .nombre(request.getFirstname())
                        .apellido(request.getLastname())
                        .mail(request.getEmail())
                        .contrasena(passwordEncoder.encode(request.getPassword()))
                        .rol(request.getRole())
                        .build();

                repository.save(user);
                var jwtToken = jwtService.generateToken(user);
                return AuthenticationResponse.builder()
                                .accessToken(jwtToken)
                                .build();
        }

        public AuthenticationResponse authenticate(UsuarioInicioSesion request) {
                authenticationManager.authenticate(
                                new UsernamePasswordAuthenticationToken(
                                                request.getIdentificador(),
                                                request.getContrasena()));
                var user = repository.findByIdentificador(request.getIdentificador())
                                .orElseThrow();
                var jwtToken = jwtService.generateToken(user);
                return AuthenticationResponse.builder()
                                .accessToken(jwtToken)
                                .build();
        }
}
