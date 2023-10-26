package com.ceiba.biblioteca.repository;

import com.ceiba.biblioteca.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario,Long> {

    @Query("SELECT u FROM Usuario u WHERE u.identificadorUsuario = :identificadorUsuario")
    Usuario findByUsuarioXLibro(@Param("identificadorUsuario") String identificadorUsuario);

    @Query("SELECT u.identificadorUsuario, l.isbn, l.nombre FROM Usuario u " +
            "LEFT JOIN Libro l ON u.identificadorUsuario = l.usuario.identificadorUsuario " +
            "WHERE u.identificadorUsuario = :identificadorUsuario")
    List<Object[]> findUsuarioYLibrosById(@Param("identificadorUsuario") String identificadorUsuario);
}
