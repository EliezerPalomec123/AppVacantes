package com.app.vacantes.controller;

import com.app.vacantes.modell.CategoriaModell;
import com.app.vacantes.modell.VacanteModell;
import com.app.vacantes.service.InterfaceCategoriaService;
import com.app.vacantes.service.InterfaceVacanteService;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/home")
public class HomeController {

    //Como se necesitan listas las categoria se inyecta el servicio de estas
    private InterfaceCategoriaService categoriaService;

    //Al igual se debe de inyectar el servicio de vacantes, para obtener las destacadas
    private InterfaceVacanteService vacanteService;

    public HomeController(InterfaceCategoriaService categoriaService, InterfaceVacanteService vacanteService) {
        this.categoriaService = categoriaService;
        this.vacanteService = vacanteService;
    }

    //endpoint que contiene toda la parte de listar vacantes destacadas
    //Al igual contiene toda la lógica del filtro
    @GetMapping("/index")
    public String viewHome(Model model, @Param("clave") String clave,@Param("id") Integer id){

        if (clave == null || clave.trim().isEmpty())
            clave = null;

        System.out.println("El valor del id es: "+id);
        System.out.println("El valor de la clave es: "+clave);
        //Esto se hace porque en algunas ocasiones clave viene como null y aveces viene como cadena vacía


        if(clave != null || id!=null){
            //Si el id viene el null se realiza la busqueda por medio de la clave
            if(id==null){
                System.out.println("La clave no viene nula");

                System.out.println("El id que me esta llegando de la categoria es: "+ id);

                List<CategoriaModell> listCategorias = categoriaService.listAllCategorias();
                List<VacanteModell> listVacantesFound = vacanteService.listVacantesFound(clave);

                model.addAttribute("listCategorias",listCategorias);
                model.addAttribute("clave",clave);
                model.addAttribute("listVacantesFeatures",listVacantesFound);
            } else if (clave == null) {

                //Si la clave viene nula, se buscan todos las vacantes con el id de la categoría especificado
                int idCategoria = id;

                List<CategoriaModell> listCategorias = categoriaService.listAllCategorias();
                List<VacanteModell> listVacantesByIdCategoria = vacanteService.listVacantesByIdCategoria(idCategoria);

                model.addAttribute("listVacantesFeatures",listVacantesByIdCategoria);
                model.addAttribute("listCategorias",listCategorias);
            }else{
                List<CategoriaModell> listCategorias = categoriaService.listAllCategorias();
                List<VacanteModell> listVacantesByNameAndIdCategoria = vacanteService.listVacantesByNameAndIdCategoria(clave,id);

                model.addAttribute("listVacantesFeatures",listVacantesByNameAndIdCategoria);
                model.addAttribute("listCategorias",listCategorias);
            }

        }
        else{
            System.out.println("La clave está nula");
            //Se obtienen todas las categorías existentes
            List<CategoriaModell> listCategorias = categoriaService.listAllCategorias();

            //Se obtienen las vacantes destacadas
            List<VacanteModell> listVacantesFeatures = vacanteService.listVacantesFeatures();

            //Se mandan a la plantilla Home
            model.addAttribute("listCategorias",listCategorias);
            model.addAttribute("listVacantesFeatures",listVacantesFeatures);
        }

        return "home";
    }

    //endpoint para consultar detalles de una vacante
    @GetMapping("/vacante/details/{idVacante}")
    public String viewDetailsVacante(@PathVariable("idVacante") int idVacante, Model model){

        VacanteModell vacanteFound = vacanteService.findById(idVacante);

        model.addAttribute("vacante",vacanteFound);

        return "detalles";
    }


}
