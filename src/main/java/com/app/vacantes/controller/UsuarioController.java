package com.app.vacantes.controller;

import com.app.vacantes.modell.UsuarioModell;
import com.app.vacantes.modell.VacanteModell;
import com.app.vacantes.service.InterfaceUsuarioService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Calendar;
import java.util.GregorianCalendar;

import java.util.List;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {

    //Inyección del servicio
    private InterfaceUsuarioService usuarioService;

    public UsuarioController(InterfaceUsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    //endpoint para listar todos los usuarios
    @GetMapping("/index")
    public String listUsuarios(Model model){
        List<UsuarioModell> listUsuarios=usuarioService.listusuarios();

        model.addAttribute("listusuarios",listUsuarios);

        return "listUsuarios";
    }

    //endpoint para mostrar formulario de la creación de usuarios
    @GetMapping("/new")
    public String addUsuario(Model model){

        UsuarioModell userNew=new UsuarioModell();

        model.addAttribute("usuario",userNew);

        return "formRegistro";
    }

    //endpoint para mandar a guardar el usuario
    @PostMapping("/save")
    public String saveUsuario(@ModelAttribute("usuario")UsuarioModell usuario){

        //Se le asigna el estado activo al nuevo usuario
        usuario.setStatus("Activo");

        //Se consulta la fecha actual del sistema para posteriormente añadirla al registro
        Calendar fecha = new GregorianCalendar();

        usuario.setFecharegistro(fecha.getTime());

        usuarioService.saveUsuario(usuario);

        return "redirect:/usuarios/index";
    }

    //endpoint para mostrar formulario de actualización
    @GetMapping("/update/{id}")
    public String updateUsuario(@PathVariable("id") int id,Model model){
        UsuarioModell usuarioFound=usuarioService.findByIdUsuario(id);

        model.addAttribute("usuario",usuarioFound);

        return "formRegistro";
    }

    //endpoint para eliminar un regsitro existente
    @GetMapping("/delete/{id}")
    public void deleteUsuario(@PathVariable("id") int id){
        usuarioService.deleteByIdUsuario(id);
    }
}
