package com.app.vacantes.serviceImpl;

import com.app.vacantes.modell.VacanteModell;
import com.app.vacantes.repository.VacanteRepository;
import com.app.vacantes.service.InterfaceVacanteService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ImplVacanteService implements InterfaceVacanteService {

    //Inyección del repositorio
    private VacanteRepository vacanteRepo;

    public ImplVacanteService(VacanteRepository vacanteRepo) {
        this.vacanteRepo = vacanteRepo;
    }

    @Override
    public List<VacanteModell> listAllVacantes() {
        return (List<VacanteModell>) vacanteRepo.findAll();
    }

    @Override
    public VacanteModell saveVacante(VacanteModell vacante) {
       return vacanteRepo.save(vacante);
    }

    @Override
    public VacanteModell findById(Integer id) {
        return vacanteRepo.findById(id).get();
    }

    @Override
    public void deleteById(Integer id) {
        vacanteRepo.deleteById(id);
    }
}
