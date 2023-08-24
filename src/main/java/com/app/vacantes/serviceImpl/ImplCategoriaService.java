package com.app.vacantes.serviceImpl;

import com.app.vacantes.modell.CategoriaModell;
import com.app.vacantes.repository.CategoriaRepository;
import com.app.vacantes.service.InterfaceCategoriaService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImplCategoriaService implements InterfaceCategoriaService {

    //Inyección del repositorio
    private CategoriaRepository categoriaRepo;

    public ImplCategoriaService(CategoriaRepository categoriaRepo) {
        this.categoriaRepo = categoriaRepo;
    }


    @Override
    public List<CategoriaModell> listAllCategorias() {
        return (List<CategoriaModell>) categoriaRepo.findAll();
    }

    @Override
    public CategoriaModell saveCategoria(CategoriaModell categoria) {
        return categoriaRepo.save(categoria);
    }

    @Override
    public CategoriaModell findById(Integer id) {
        return categoriaRepo.findById(id).get();
    }
}
