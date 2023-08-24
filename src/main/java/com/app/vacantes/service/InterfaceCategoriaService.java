package com.app.vacantes.service;

import com.app.vacantes.modell.CategoriaModell;

import java.util.List;

public interface InterfaceCategoriaService {

    List<CategoriaModell> listAllCategorias();

    CategoriaModell saveCategoria(CategoriaModell categoria);

    CategoriaModell findById(Integer id);
}
