package com.app.vacantes.repository;

import com.app.vacantes.modell.CategoriaModell;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoriaRepository extends CrudRepository<CategoriaModell,Integer> {


}
