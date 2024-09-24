package com.TPOBackend.TPOBackend.Repository;


import com.TPOBackend.TPOBackend.Repository.Entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.jta.UserTransactionAdapter;

import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Usuario, Integer> {

    @Query("select u from Usuario u where u.nombreUsuario = ?1")
    Optional<Usuario> findByNombreUsuario(String nombreUsuario);

    //@Query("select u from Usuario u where u.mail = ?1")
    Optional<Usuario> findByMail(String mail);

    @Query("select u from Usuario u where (u.nombreUsuario = ?1 OR u.mail = ?1)")
    Optional<Usuario> findByIdentificador(String identificador);






}
