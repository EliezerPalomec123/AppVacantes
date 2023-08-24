package com.app.vacantes.controller;

import com.app.vacantes.modell.CategoriaModell;
import com.app.vacantes.service.InterfaceCategoriaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/categorias")
public class CategoriaController {

    //Inyección del servicio
    private InterfaceCategoriaService categoriaServi;

    public CategoriaController(InterfaceCategoriaService categoriaServi) {
        this.categoriaServi = categoriaServi;
    }

    @GetMapping("/index")
    public String listAllCategorias(Model model){
        List<CategoriaModell> listCategorias=categoriaServi.listAllCategorias();

        model.addAttribute("categorias",listCategorias);

        return "listCategorias";
    }

    @GetMapping("/new")
    public String newCategoria(Model model){
        CategoriaModell newCategoria=new CategoriaModell();

        model.addAttribute("categoria",newCategoria);

        return "formCategoria";
    }

    @PostMapping("/save")
    public String saveCategoria(@ModelAttribute("categoria") CategoriaModell categoria){
        categoriaServi.saveCategoria(categoria);

        return "redirect:/categorias/index";
    }

}
