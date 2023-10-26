package com.ceiba.biblioteca;


import com.ceiba.biblioteca.entity.Libro;
import com.ceiba.biblioteca.entity.Usuario;
import com.ceiba.biblioteca.repository.LibroRepository;
import com.ceiba.biblioteca.repository.UsuarioRepository;
import com.ceiba.biblioteca.service.PrestamoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("prestamo")
public class PrestamoControlador {

    @Autowired
    private PrestamoService prestamoService;

    @Autowired
    private LibroRepository librepo;
    @Autowired
    private  UsuarioRepository  usuarioRepo;

    @PostMapping("/crear")
    public ResponseEntity crearPrestamo(@RequestBody Libro libro){
        LocalDate fechaActual = LocalDate.now();
        Usuario usuariotype = usuarioRepo.findByUsuarioXLibro(libro.getUsuario().getIdentificadorUsuario());
        String identificador = usuariotype.getIdentificadorUsuario();
        List<Object[]> libxuser = usuarioRepo.findUsuarioYLibrosById(identificador);
        if(usuariotype.getTipoUsuario()!=0){
            if(usuariotype.getTipoUsuario() == 1){
                int dias = 10;
                libro.setFechaPrestamo(fechaActual);
                libro.setFechaDevolucion(fechaActual.plusDays(dias));
            }else if (usuariotype.getTipoUsuario()==2){
                int dias = 8;
                libro.setFechaPrestamo(fechaActual);
                libro.setFechaDevolucion(fechaActual.plusDays(dias));
            }else if(usuariotype.getTipoUsuario()==3 ){
                if(libxuser.get(0)!=null){
                    return ResponseEntity.badRequest().body("El usuario con identificación" + libro.getUsuario().getIdentificadorUsuario() +
                            "ya tiene un libro prestado por lo cual no se le puede realizar otro prestamo");
                }else{
                    int dias = 7;
                    libro.setFechaDevolucion(fechaActual.plusDays(dias));
                }
            }else {
                return ResponseEntity.badRequest().body("Tipo de usuario no permitido en la biblioteca");
            }
        }
        //int tipo = librepo.findTipyUser(libro.getUsuario().getIdentificadorUsuario());
        prestamoService.save(libro);
        return ResponseEntity.ok("Prestamo Creado Exitosamente");
    }


    @GetMapping("/{id-prestamo}")
    public ResponseEntity<Libro> getPrestamoPorId(@PathVariable("id-prestamo") Long idPrestamo) {
            Optional<Libro> prestamo = librepo.findById(idPrestamo);
            if (prestamo.isPresent()){
                Libro libro = prestamo.get();
                return ResponseEntity.ok(libro);
            }else{
                return ResponseEntity.notFound().build();
            }
        }
    }

