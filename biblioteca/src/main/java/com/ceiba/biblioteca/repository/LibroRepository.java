package com.ceiba.biblioteca.repository;

import com.ceiba.biblioteca.entity.Libro;
import com.ceiba.biblioteca.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LibroRepository extends JpaRepository<Libro,Long> {

    @Query("SELECT l FROM Libro l WHERE l.usuario = :usuario")
    Libro findByUsuarioXLibro(@Param("usuario") String usuario);


}
