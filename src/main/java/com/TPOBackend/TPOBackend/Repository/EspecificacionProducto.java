package com.TPOBackend.TPOBackend.Repository;

import com.TPOBackend.TPOBackend.Repository.Entity.Categoria;
import com.TPOBackend.TPOBackend.Repository.Entity.Producto;
import org.springframework.data.jpa.domain.Specification;

public class EspecificacionProducto {

    public static Specification<Producto> hasNombre(String nombre) {
        return (root, query, criteriaBuilder) ->
                nombre == null ? criteriaBuilder.conjunction() : criteriaBuilder.equal(root.get("nombre"), nombre);
    }

    public static Specification<Producto> hasLiga(String liga) {
        return (root, query, criteriaBuilder) ->
                liga == null ? criteriaBuilder.conjunction() : criteriaBuilder.equal(root.get("liga"), liga);
    }

    public static Specification<Producto> hasEquipo(String equipo) {
        return (root, query, criteriaBuilder) ->
                equipo == null ? criteriaBuilder.conjunction() : criteriaBuilder.equal(root.get("equipo"), equipo);
    }

    public static Specification<Producto> hasMarca(String marca) {
        return (root, query, criteriaBuilder) ->
                marca == null ? criteriaBuilder.conjunction() : criteriaBuilder.equal(root.get("marca"), marca);
    }

    public static Specification<Producto> hasPrecioBetween(Double minPrecio, Double maxPrecio) {
        return (root, query, criteriaBuilder) -> {
            if (minPrecio == null && maxPrecio == null) {
                return criteriaBuilder.conjunction();
            } else if (minPrecio != null && maxPrecio != null) {
                return criteriaBuilder.between(root.get("precio"), minPrecio, maxPrecio);
            } else if (minPrecio != null) {
                return criteriaBuilder.greaterThanOrEqualTo(root.get("precio"), minPrecio);
            } else {
                return criteriaBuilder.lessThanOrEqualTo(root.get("precio"), maxPrecio);
            }
        };
    }

    public static Specification<Producto> hasStock(Integer stock) {
        return (root, query, criteriaBuilder) ->
                stock == null ? criteriaBuilder.conjunction() : criteriaBuilder.equal(root.get("stock"), stock);
    }

    public static Specification<Producto> isDestacado(Boolean destacado) {
        return (root, query, criteriaBuilder) ->
                destacado == null ? criteriaBuilder.conjunction() : criteriaBuilder.equal(root.get("destacado"), destacado);
    }

    public static Specification<Producto> hasCategoria(Categoria categoria) {
        return (root, query, criteriaBuilder) ->
                categoria == null ? criteriaBuilder.conjunction() : criteriaBuilder.equal(root.get("categoria"), categoria);
    }
}
