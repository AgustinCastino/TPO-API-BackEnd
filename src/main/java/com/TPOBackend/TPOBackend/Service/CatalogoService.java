package com.TPOBackend.TPOBackend.Service;

import java.util.ArrayList;
import java.util.Optional;

import com.TPOBackend.TPOBackend.Repository.CatalogoRepository;
import com.TPOBackend.TPOBackend.Repository.Entity.Producto;

public class CatalogoService {

    private CatalogoRepository catalogoRepository;

    public CatalogoService(CatalogoRepository catalogoRepository) {
        this.catalogoRepository = catalogoRepository;
    }

    public CatalogoRepository getCatalogoRepository() {
        return catalogoRepository;
    }

    public void setCatalogoRepository(CatalogoRepository catalogoRepository) {
        this.catalogoRepository = catalogoRepository;
    }

    public ArrayList<Producto> getProductos() {
        return catalogoRepository.getProductos();
    }

    public Optional<Producto> findByNombre(String nombre) {
        return catalogoRepository.findByNombre(nombre);
    }

    public Optional<Producto> findById(int id) {
        return catalogoRepository.findById(id);
    }

    public ArrayList<Producto> findByCategoria(String categoria) {
        return catalogoRepository.findByCategoria(categoria);
    }

    public ArrayList<Producto> findByVisto(boolean visto) {
        return catalogoRepository.findByVisto(visto);
    }

    public ArrayList<Producto> findByDestacado(boolean destacado) {
        return catalogoRepository.findByDestacado(destacado);
    }

    public ArrayList<Producto> findByInFavorites(boolean inFavorites) {
        return catalogoRepository.findByInFavorites(inFavorites);
    }

    
    
}
