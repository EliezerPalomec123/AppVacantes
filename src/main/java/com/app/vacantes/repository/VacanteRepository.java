package com.app.vacantes.repository;

import com.app.vacantes.modell.VacanteModell;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.net.Inet4Address;
import java.util.List;

@Repository
public interface VacanteRepository extends CrudRepository<VacanteModell, Integer> {

    //Consulta para obtener vacantes que son destacadas
    @Query("select v from VacanteModell v where v.destacado = 'si'")
    List<VacanteModell> listVacantesFeatures();

    //Consulta para buscar vacantes por nombre
    @Query("select v from VacanteModell v where v.nombre like %:nombre%")
    List<VacanteModell> listVacantesFound(@Param("nombre") String nombre);

    //Consulta para obtener las vacantes mediante el id de la categoría
    @Query("select v from VacanteModell v where v.categoria.id= :id and v.destacado = 'si'")
    List<VacanteModell> listVacantesByIdCategoria(Integer id);

    @Query("select v from VacanteModell v where v.nombre like %:nombre% and v.categoria.id = :id and v.destacado = 'si'")
    List<VacanteModell> listVacantesByNameAndIdCategoria(@Param("nombre") String nombre,Integer id);

}
