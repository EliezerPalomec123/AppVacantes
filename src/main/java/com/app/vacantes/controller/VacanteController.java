package com.app.vacantes.controller;

import com.app.vacantes.modell.CategoriaModell;
import com.app.vacantes.modell.VacanteModell;
import com.app.vacantes.service.InterfaceCategoriaService;
import com.app.vacantes.service.InterfaceVacanteService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.mock.web.MockMultipartFile;
import org.apache.commons.io.IOUtils;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.nio.file.Paths;

@Controller
@RequestMapping("/vacantes")
public class VacanteController {

    //Inyección de servicio de vacantes
    private InterfaceVacanteService vacanteService;

    //Inyección del sericio de categorias para obtener la lista
    private InterfaceCategoriaService categoriaService;

    //Constante que almacena la ruta en donde se guardaran las imagenes que subirá el usuario
    private static final String rutaImagenes="/home/eliezer/IdeaProjects/Vacantes/src/main/resources/static/images/";

    public VacanteController(InterfaceVacanteService vacanteService, InterfaceCategoriaService categoriaService) {
        this.vacanteService = vacanteService;
        this.categoriaService = categoriaService;
    }

    //endpoint para listar todas las vacantes
    @GetMapping("/index")
    public String listVacantes(Model model){

        List<VacanteModell> listVacantes=vacanteService.listAllVacantes();
        model.addAttribute("vacantes",listVacantes);

        return "listVacantes";
    }


    //endpoint para mostrar formulario de una nueva vacante
    @GetMapping("/new")
    public String addVacante(Model model){
        VacanteModell vacanteNew=new VacanteModell();

        List<CategoriaModell> listCategorias=categoriaService.listAllCategorias();

        model.addAttribute("vacante",vacanteNew);
        model.addAttribute("listCategorias",listCategorias);

        return "formVacante";
    }


    //endpoint para guardar la nueva vacante
    @PostMapping("/save")
    public String saveVacante(@ModelAttribute("vacante") VacanteModell vacante,
                              @RequestParam("archivoImagen")MultipartFile archivo,
                              @RequestParam("categoriaselect") String idcategoria){

        int id=Integer.parseInt(idcategoria);


        if(!archivo.isEmpty()){
            try{
                byte[] bytes=archivo.getBytes();
                Path rutaImagen= Paths.get(rutaImagenes,archivo.getOriginalFilename());
                Files.write(rutaImagen, bytes);

                //Se obtiene el nombre de la imagen para guardarla en la base de datos
                String nombreImagen=archivo.getOriginalFilename();
                vacante.setImagen(nombreImagen);
            } catch (Exception e) {
                System.out.println(e.getMessage());
                throw new RuntimeException(e);
            }
        }
        System.out.println("la imagen que traigo por defecto es: "+vacante.getImagen());


        //Se manda a buscar la categoria que selccionó el usuario
        CategoriaModell categoria=categoriaService.findById(id);
        //Se asigna la categoria a la vacante
        vacante.setCategoria(categoria);

        System.out.println("El id que me esta llegando antes de guardar es: "+vacante.getIdvacante());
        System.out.println("Y la categoria que llega es: "+vacante.getCategoria());

        //Se manda a guardar
        vacanteService.saveVacante(vacante);

        return "redirect:/vacantes/index";

    }


    //endpoint para mostrar formulario de actualización
    @GetMapping("/update/{id}")
    public String updateVacante(@PathVariable("id") int id,Model model){
        VacanteModell vacanteFound=vacanteService.findById(id);

        List<CategoriaModell> listCategorias=categoriaService.listAllCategorias();

        model.addAttribute("vacante",vacanteFound);
        model.addAttribute("listCategorias",listCategorias);

        System.out.println("El nombre de la vacante a actualizar es: " + vacanteFound.getNombre());
        System.out.println("Su categoria es :"+vacanteFound.getCategoria().getNombre());
        System.out.println("El nombre de la imagen que estoy mandando a la vista es: "+vacanteFound.getImagen());

        return "formVacante";
    }

    //endpoint para eliminar un registro existente
    @GetMapping("/delete/{id}")
    public String deleteVacante(@PathVariable("id") int id){
        vacanteService.deleteById(id);
        System.out.println("El registro se a eliminado correctamente");

        return "redirect:/vacantes/index";
    }

}