package com.app.vacantes.service;

import com.app.vacantes.modell.VacanteModell;

import java.util.List;

public interface InterfaceVacanteService {

    List<VacanteModell> listAllVacantes();

    VacanteModell saveVacante(VacanteModell vacante);

    VacanteModell findById(Integer id);

    void deleteById(Integer id);
}
