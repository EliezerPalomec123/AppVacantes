package com.app.vacantes.serviceImpl;

import com.app.vacantes.modell.UsuarioModell;
import com.app.vacantes.repository.UsuarioRepository;
import com.app.vacantes.service.InterfaceUsuarioService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImplUsuarioService implements InterfaceUsuarioService {

    //Inyección del repositorio
    private UsuarioRepository usuarioRepo;
    public ImplUsuarioService(UsuarioRepository usuarioRepo) {
        this.usuarioRepo = usuarioRepo;
    }

    @Override
    public List<UsuarioModell> listusuarios() {
        return (List<UsuarioModell>) usuarioRepo.findAll();
    }

    @Override
    public UsuarioModell saveUsuario(UsuarioModell usuario) {
        return usuarioRepo.save(usuario);
    }

    @Override
    public UsuarioModell findByIdUsuario(Integer id) {
        return usuarioRepo.findById(id).get();
    }

    @Override
    public void deleteByIdUsuario(Integer id) {
        usuarioRepo.deleteById(id);
    }
}
