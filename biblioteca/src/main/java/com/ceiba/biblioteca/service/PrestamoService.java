package com.ceiba.biblioteca.service;

import com.ceiba.biblioteca.entity.Libro;
import org.springframework.stereotype.Component;

@Component
public interface PrestamoService {

    public Libro save(Libro libro);
}
