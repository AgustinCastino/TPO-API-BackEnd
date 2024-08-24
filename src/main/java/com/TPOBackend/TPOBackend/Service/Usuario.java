package com.TPOBackend.TPOBackend.Service;

import com.TPOBackend.TPOBackend.Repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;

@Service
public class Usuario {
    private int id = 0;
    private UserRepository userRepository;
    private String nombreUsuario;
    private String mail;
    private String contrasena;
    private Date fechaNacimiento;
    private String nombre;
    private String apellido;
    private ArrayList<Compra> comprasUsuario;
    private boolean logeado;

    public Usuario(String nombreUsuario, String mail, String contrasena, Date fechaNacimiento, String nombre, String apellido) {
        this.id++;
        this.nombreUsuario = nombreUsuario;
        this.mail = mail;
        this.contrasena = contrasena;
        this.fechaNacimiento = fechaNacimiento;
        this.nombre = nombre;
        this.apellido = apellido;
        this.logeado = false;
        this.comprasUsuario = new ArrayList<Compra>();
        this.userRepository = new UserRepository();
    }

    public ArrayList<Compra> getComprasUsuario(){
        return comprasUsuario;
    }

    public void mostrarCompras(){
        for (Compra compra : comprasUsuario) {
            System.out.println(compra);
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public UserRepository getUserRepository() {
        return userRepository;
    }

    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void setComprasUsuario(ArrayList<Compra> comprasUsuario) {
        this.comprasUsuario = comprasUsuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public boolean isLogeado() {
        return logeado;
    }

    public void setLogeado(boolean logeado) {
        this.logeado = logeado;
    }

    public boolean validarContrasena(String contrasena){
        return this.contrasena.equals(contrasena);
    }

   /* public boolean iniciarSesion(Usuario usuario){
        if (usuario.getMail().equals(identificador) || this.nombreUsuario.equals(identificador) && this.contrasena.equals(contrasena)) {
            this.logeado = true;
            return true;
        }
        return false;
    }*/

    public Usuario registrarUsuario(Usuario usuario) throws Exception {
        Usuario usuarioExistente = userRepository.encontrarPorNombreUusario(usuario.getNombreUsuario()).orElseThrow(() -> new Exception("Ocurrio un error"));
        Usuario mailExistente = userRepository.encontrarPorMail(usuario.getMail()).orElseThrow(() -> new Exception("Ocurrio un error"));

        Usuario nuevoUsuario = new Usuario(usuario.getNombreUsuario(),usuario.getMail(), usuario.getContrasena(), usuario.getFechaNacimiento(), usuario.getNombre(), usuario.getApellido());
        this.userRepository.setUsuarios(nuevoUsuario);
        return nuevoUsuario;

    }

    public void cerrarSesion(){
        this.logeado=false;
    }


}
