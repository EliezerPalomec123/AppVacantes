package com.app.vacantes.repository;

import com.app.vacantes.modell.UsuarioModell;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends CrudRepository<UsuarioModell,Integer> {
}
