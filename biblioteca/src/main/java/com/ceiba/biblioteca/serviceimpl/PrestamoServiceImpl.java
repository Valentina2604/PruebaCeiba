package com.ceiba.biblioteca.serviceimpl;

import com.ceiba.biblioteca.entity.Libro;
import com.ceiba.biblioteca.repository.LibroRepository;
import com.ceiba.biblioteca.service.PrestamoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class PrestamoServiceImpl implements PrestamoService {

    @Autowired
    LibroRepository libroRepository;

    @Override
    public Libro save(Libro libro) {
        return libroRepository.save(libro);
    }
}
