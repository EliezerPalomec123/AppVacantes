package com.app.vacantes.repository;

import com.app.vacantes.modell.VacanteModell;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.net.Inet4Address;
@Repository
public interface VacanteRepository extends CrudRepository<VacanteModell, Integer> {
}
