package com.app.vacantes.service;

import com.app.vacantes.modell.UsuarioModell;

import java.util.List;

public interface InterfaceUsuarioService {

    List<UsuarioModell> listusuarios();

    UsuarioModell saveUsuario(UsuarioModell usuario);

    UsuarioModell findByIdUsuario(Integer id);

    void deleteByIdUsuario(Integer id);
}
