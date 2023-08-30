package com.app.vacantes.controller;

import com.app.vacantes.modell.CategoriaModell;
import com.app.vacantes.service.InterfaceCategoriaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/categorias")
public class CategoriaController {

    //Inyección del servicio
    private InterfaceCategoriaService categoriaServi;

    public CategoriaController(InterfaceCategoriaService categoriaServi) {
        this.categoriaServi = categoriaServi;
    }

    //endpoint para listar todas las categorías exixtentes
    @GetMapping("/index")
    public String listAllCategorias(Model model){
        List<CategoriaModell> listCategorias=categoriaServi.listAllCategorias();

        model.addAttribute("categorias",listCategorias);

        return "listCategorias";
    }

    //endpoint para mostrar formulario de creación
    @GetMapping("/new")
    public String newCategoria(Model model){
        CategoriaModell newCategoria=new CategoriaModell();

        model.addAttribute("categoria",newCategoria);

        return "formCategoria";
    }

    //endpoint para guardar una nueva categoria
    @PostMapping("/save")
    public String saveCategoria(@ModelAttribute("categoria") CategoriaModell categoria){
        categoriaServi.saveCategoria(categoria);

        return "redirect:/categorias/index";
    }

    //endpoint para mostrar formulario de actualización
    @GetMapping("/update/{id}")
    public String updateCategoria(@PathVariable("id") int id,Model model){
        CategoriaModell categoriaFound=categoriaServi.findById(id);

        model.addAttribute("categoria",categoriaFound);

        return "formCategoria";
    }

    //endpoint para eliminar una categoría existente
    @GetMapping("/delete/{id}")
    public String deleteCategoria(@PathVariable("id") int id){
        categoriaServi.deleteCategoria(id);

        return "redirect:/categorias/index";
    }

}
